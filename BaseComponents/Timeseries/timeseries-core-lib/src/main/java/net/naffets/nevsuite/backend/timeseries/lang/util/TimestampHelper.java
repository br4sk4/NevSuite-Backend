package net.naffets.nevsuite.backend.timeseries.lang.util;

import oracle.sql.TIMESTAMPTZ;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author br4sk4
 * created on 12.03.2016
 */
public class TimestampHelper {

    private static byte intToPseudoUnsignedByte(int n) {
        return (byte) (n & 0xFF);
    }

    private static int pseudoUnsignedByteToInt(byte n) {
        return n & 0xFF;
    }

    public static TIMESTAMPTZ toTimestampTZ(ZonedDateTime timestamp) {
        byte[] arr = new byte[13];
        ZonedDateTime timestampUTC = timestamp.toInstant().atZone(ZoneId.of("UTC"));

        arr[0] = intToPseudoUnsignedByte(timestampUTC.getYear() / 100 + 100);
        arr[1] = intToPseudoUnsignedByte((timestampUTC.getYear() % 100) + 100);
        arr[2] = intToPseudoUnsignedByte(timestampUTC.getMonthValue());
        arr[3] = intToPseudoUnsignedByte(timestampUTC.getDayOfMonth());
        arr[4] = intToPseudoUnsignedByte(timestampUTC.getHour() + 1);
        arr[5] = intToPseudoUnsignedByte(timestampUTC.getMinute() + 1);
        arr[6] = intToPseudoUnsignedByte(timestampUTC.getSecond() + 1);

        int nanos = timestampUTC.getNano();
        arr[7] = intToPseudoUnsignedByte((nanos & 0xFF000000) >> 24);
        arr[8] = intToPseudoUnsignedByte((nanos & 0x00FF0000) >> 16);
        arr[9] = intToPseudoUnsignedByte((nanos & 0x0000FF00) >> 16);
        arr[10] = intToPseudoUnsignedByte(nanos & 0x000000FF);

        arr[11] = intToPseudoUnsignedByte(133);
        arr[12] = intToPseudoUnsignedByte(184);

        return new TIMESTAMPTZ(arr);
    }

    public static Instant toInstant(TIMESTAMPTZ timestamp) {
        byte[] arr = timestamp.toBytes();

        ZonedDateTime instant =
                ZonedDateTime.of(
                        ((pseudoUnsignedByteToInt(arr[0]) - 100) * 100) + (pseudoUnsignedByteToInt(arr[1]) - 100),
                        pseudoUnsignedByteToInt(arr[2]),
                        pseudoUnsignedByteToInt(arr[3]),
                        pseudoUnsignedByteToInt(arr[4]) - 1,
                        pseudoUnsignedByteToInt(arr[5]) - 1,
                        pseudoUnsignedByteToInt(arr[6]) - 1,
                        (int) ((long) pseudoUnsignedByteToInt(arr[7]) << 24 |
                                (long) pseudoUnsignedByteToInt(arr[8]) << 16 |
                                (long) pseudoUnsignedByteToInt(arr[9]) << 8 |
                                (long) pseudoUnsignedByteToInt(arr[10])),
                        ZoneId.of("UTC")
                );

        return instant.toInstant();
    }

}
