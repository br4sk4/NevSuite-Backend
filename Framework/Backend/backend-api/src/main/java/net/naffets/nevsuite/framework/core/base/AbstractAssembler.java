package net.naffets.nevsuite.framework.core.base;

import net.naffets.nevsuite.framework.core.api.Assembler;
import net.naffets.nevsuite.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.framework.domain.dto.ReferenceDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author br4sk4 / created on 30.03.2018
 */
public abstract class AbstractAssembler<ENTITY, DTO extends DataTransferObject> implements Assembler<ENTITY, DTO> {

    private Boolean prettyPrintingEnabled = false;
    private Boolean compressionEnabled = false;
    private final String jsonMediaType = "application/json";
    private final String xmlMediaType = "application/xml";

    public AbstractAssembler<ENTITY, DTO> setPrettyPrintingEnabled(Boolean prettyPrintingEnabled) {
        this.prettyPrintingEnabled = prettyPrintingEnabled;
        return this;
    }

    public AbstractAssembler<ENTITY, DTO> setCompressionEnabled(Boolean compressionEnabled) {
        this.compressionEnabled = compressionEnabled;
        return this;
    }

    @Override
    public List<DTO> toDTO(List<ENTITY> entities) {
        return entities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public String toJson(ENTITY entity) {
        return this.marshal(entity, jsonMediaType);
    }

    public DTO fromJson(String json, Class<DTO> dtoClass) {
        return this.unmarshal(json, dtoClass, jsonMediaType);
    }

    public String toXml(ENTITY entity) {
        return this.marshal(entity, xmlMediaType);
    }

    public DTO fromXml(String xml, Class<DTO> dtoClass) {
        return this.unmarshal(xml, dtoClass, xmlMediaType);
    }

    private String marshal(ENTITY entity, String mediaType) {
        DTO dto = this.toDTO(entity);
        try {
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{dto.getClass()}, new Properties());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty("eclipselink.media-type", mediaType);
            StringWriter writer = new StringWriter();
            if (this.prettyPrintingEnabled) jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(dto, writer);
            return this.compressionEnabled ? gzip(writer.toString()) : writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return "";
    }

    private DTO unmarshal(String data, Class<DTO> dtoClass, String mediaType) {
        try {
            Source xmlSource = new StreamSource(new StringReader(this.compressionEnabled ? gunzip(data) : data));
            JAXBContext jaxbContext = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[]{dtoClass}, new Properties());
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.setProperty("eclipselink.media-type", mediaType);
            return jaxbUnmarshaller.unmarshal(xmlSource, dtoClass).getValue();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String gzip(String str) {
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

    private String gunzip(String str) {
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

}
