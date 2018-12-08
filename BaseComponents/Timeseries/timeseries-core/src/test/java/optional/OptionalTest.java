package optional;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author br4sk4
 * created on 07.12.18
 */
public class OptionalTest {

    @Test
    public void testNullEntity() {
        assertTrue(validateBeginOfContract(null));
    }

    @Test
    public void testNullAttributes() {
        assertTrue(validateBeginOfContract(new Wrapper()));
    }

    @Test
    public void testNullBegin() {
        assertTrue(validateBeginOfContract(
                new Wrapper().setExpiration(LocalDate.parse("2018-01-01"))
        ));
    }

    @Test
    public void testNullExpiration() {
        assertTrue(validateBeginOfContract(
                new Wrapper().setBegin(LocalDate.parse("2018-01-01"))
        ));
    }

    @Test
    public void testBothValuesValid() {
        assertTrue(validateBeginOfContract(
                new Wrapper()
                        .setBegin(LocalDate.parse("2018-01-01"))
                        .setExpiration(LocalDate.parse("2019-01-01"))
        ));
    }

    @Test
    public void testBothValuesValidEqualDates() {
        assertTrue(validateBeginOfContract(
                new Wrapper()
                        .setBegin(LocalDate.parse("2018-01-01"))
                        .setExpiration(LocalDate.parse("2018-01-01"))
        ));
    }

    @Test
    public void testBothValuesNotValid() {
        assertFalse(validateBeginOfContract(
                new Wrapper()
                        .setBegin(LocalDate.parse("2019-01-01"))
                        .setExpiration(LocalDate.parse("2018-01-01"))
        ));
    }

    private boolean validateBeginOfContract(Wrapper wrapper) {
        LocalDate beginOfContract = Optional.ofNullable(wrapper)
                .map(object -> object.beginOfContract)
                .orElse(LocalDate.MIN);

        LocalDate expirationOfContract = Optional.ofNullable(wrapper)
                .map(object -> object.expirationOfContract)
                .orElse(LocalDate.MAX);

        return !beginOfContract.isAfter(expirationOfContract);
    }

    private class Wrapper {
        public LocalDate beginOfContract;
        public LocalDate expirationOfContract;

        public Wrapper setBegin(LocalDate begin) {
            this.beginOfContract = begin;
            return this;
        }

        public Wrapper setExpiration(LocalDate expiration) {
            this.expirationOfContract = expiration;
            return this;
        }
    }

}
