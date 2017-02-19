package net.naffets.nevsuite.backend.framework.core.base;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author br4sk4
 * created on 06.10.2016
 */
public abstract class AbstractDataTransferObjectBuilder<DTO extends DataTransferObject> {

    protected DTO dto;
    private Boolean prettyPrintingEnabled = false;
    private Boolean compressionEnabled = false;

    protected AbstractDataTransferObjectBuilder(DTO dto) {
        this.dto = dto;
    }

    public static String gzip(String str) {
        try {
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
                    gzip.write(str.getBytes(StandardCharsets.UTF_8));
                }
                return out.toString(StandardCharsets.ISO_8859_1.name());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String gunzip(String str) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(str.getBytes(StandardCharsets.ISO_8859_1)))) {
                int b;
                while ((b = gis.read()) != -1) {
                    baos.write((byte) b);
                }
            }
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public abstract AbstractDataTransferObjectBuilder<DTO> fromDTO(DTO dto);

    public AbstractDataTransferObjectBuilder<DTO> setPrettyPrintingEnabled(Boolean prettyPrintingEnabled) {
        this.prettyPrintingEnabled = prettyPrintingEnabled;
        return this;
    }

    public AbstractDataTransferObjectBuilder<DTO> setCompressionEnabled(Boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
        return this;
    }

    public Boolean isPrettyPrinting() {
        return this.prettyPrintingEnabled;
    }

    public Boolean isCompressionEnabled() {
        return this.compressionEnabled;
    }

    public DTO fromJson(Source json) {
        try {
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{dto.getClass()}, new Properties());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setProperty("eclipselink.media-type", "application/json");
            return this.fromDTO((DTO) jaxbUnmarshaller.unmarshal(json, dto.getClass()).getValue()).build();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String toJson() {
        try {
            JAXBContext jaxbContext = JAXBContextFactory.createContext(new Class[]{dto.getClass()}, new Properties());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty("eclipselink.media-type", "application/json");
            if (isPrettyPrinting()) jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            jaxbMarshaller.marshal(dto, writer);
            return this.compressionEnabled ? gzip(writer.toString()) : writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "";
    }

    public DTO fromXml(Source xml) {
        try {
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{dto.getClass()}, new Properties());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setProperty("eclipselink.media-type", "application/xml");
            return this.fromDTO((DTO) jaxbUnmarshaller.unmarshal(xml, dto.getClass()).getValue()).build();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String toXml() {
        try {
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{dto.getClass()}, new Properties());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty("eclipselink.media-type", "application/xml");
            StringWriter writer = new StringWriter();
            if (isPrettyPrinting()) jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dto, writer);
            return this.compressionEnabled ? gzip(writer.toString()) : writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "";
    }

    public DTO build() {
        return dto;
    }

}
