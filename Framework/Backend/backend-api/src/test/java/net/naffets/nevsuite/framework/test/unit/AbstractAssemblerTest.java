package net.naffets.nevsuite.framework.test.unit;

import net.naffets.nevsuite.framework.domain.assembler.ReferenceAssembler;
import net.naffets.nevsuite.framework.domain.dto.ReferenceDTO;
import net.naffets.nevsuite.framework.test.context.SampleEntity;
import net.naffets.nevsuite.framework.test.context.SampleEntityAssembler;
import net.naffets.nevsuite.framework.test.context.SampleEntityFullDTO;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author br4sk4 / created on 30.03.2018
 */
public class AbstractAssemblerTest {

    private SampleEntityAssembler sut = new SampleEntityAssembler();

    private static String sampleEntityIdentification;
    private static SampleEntity sampleEntity;

    @BeforeClass
    public static void initialize() {
        sampleEntityIdentification = UUID.randomUUID().toString();
        sampleEntity = SampleEntity.builder()
                .primaryKey(sampleEntityIdentification)
                .firstName("Sven")
                .lastName("Steffan")
                .build();
    }

    @Test
    public void testFullDTOAssembling() {
        SampleEntityFullDTO dto = sut.toDTO(sampleEntity);

        String expectedFirstName = "Sven";
        String expectedLastName = "Steffan";

        String actualPrimaryKey = dto.primaryKey;
        String actualFirstName = dto.firstName;
        String actualLastName = dto.lastName;

        assertThat(actualPrimaryKey).isEqualTo(sampleEntityIdentification);
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
        assertThat(actualLastName).isEqualTo(expectedLastName);
    }

    @Test
    public void testReferenceDTOAssembling() {
        ReferenceAssembler sut = new ReferenceAssembler();
        ReferenceDTO referenceDTO = sut.toDTO(sampleEntity);

        String expectedTypeDiscriminator = "SAMPLEENTITY";
        String expectedRepresentableName = "Sven Steffan";

        String actualPrimaryKey = referenceDTO.primaryKey;
        String actualTypeDiscriminator  = referenceDTO.typeDiscriminator;
        String actualRepresentableName = referenceDTO.representableName;

        assertThat(actualPrimaryKey).isEqualTo(sampleEntityIdentification);
        assertThat(actualTypeDiscriminator).isEqualTo(expectedTypeDiscriminator);
        assertThat(actualRepresentableName).isEqualTo(expectedRepresentableName);
    }

    @Test
    public void testJsonRoundtrip() {
        String json = sut.setPrettyPrintingEnabled(true).toJson(sampleEntity);

        SampleEntityFullDTO dto = sut.fromJson(json, SampleEntityFullDTO.class);

        String expectedFirstName = "Sven";
        String expectedLastName = "Steffan";

        String actualPrimaryKey = dto.primaryKey;
        String actualFirstName = dto.firstName;
        String actualLastName = dto.lastName;

        assertThat(actualPrimaryKey).isEqualTo(sampleEntityIdentification);
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
        assertThat(actualLastName).isEqualTo(expectedLastName);
    }

    @Test
    public void testXmlRoundtrip() {
        String xml = sut.setPrettyPrintingEnabled(true).toXml(sampleEntity);
        SampleEntityFullDTO dto = sut.fromXml(xml, SampleEntityFullDTO.class);

        System.out.println(xml);

        String expectedFirstName = "Sven";
        String expectedLastName = "Steffan";

        String actualPrimaryKey = dto.primaryKey;
        String actualFirstName = dto.firstName;
        String actualLastName = dto.lastName;

        assertThat(actualPrimaryKey).isEqualTo(sampleEntityIdentification);
        assertThat(actualFirstName).isEqualTo(expectedFirstName);
        assertThat(actualLastName).isEqualTo(expectedLastName);
    }

}
