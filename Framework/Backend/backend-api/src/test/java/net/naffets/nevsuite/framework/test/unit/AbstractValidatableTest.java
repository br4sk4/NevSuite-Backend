package net.naffets.nevsuite.framework.test.unit;

import net.naffets.nevsuite.framework.lang.validation.EntityValidationException;
import net.naffets.nevsuite.framework.test.context.SampleEntity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.UUID;

import static org.hamcrest.Matchers.*;

/**
 * @author br4sk4 / created on 16.11.2017
 */
public class AbstractValidatableTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private String errorMessage = "darf nicht null sein";
    private String sampleEntityIdentification = UUID.randomUUID().toString();
    private SampleEntity sampleEntity;

    @Before
    public void setUp() {
        sampleEntity = SampleEntity.builder()
                .primaryKey(sampleEntityIdentification)
                .firstName("Sven")
                .lastName("Steffan")
                .build();
    }

    @Test
    public void testSuccessfullValidation() {
        sampleEntity.validate();
    }

    @Test
    public void testValidationErrorOnMissingPrimaryKey() {
        thrown.expect(EntityValidationException.class);
        thrown.expect(hasProperty("message", is("validation.error")));
        thrown.expect(hasProperty("validationErrors", hasSize(1)));
        thrown.expect(hasProperty("validationErrors", hasItem(allOf(
                hasProperty("fieldName", is("primaryKey")),
                hasProperty("errorMessage", is(errorMessage))
        ))));

        sampleEntity.setPrimaryKey(null);
        sampleEntity.validate();
    }

    @Test
    public void testValidationErrorOnMissingFirstName() {
        thrown.expect(EntityValidationException.class);
        thrown.expect(hasProperty("message", is("validation.error")));
        thrown.expect(hasProperty("validationErrors", hasSize(1)));
        thrown.expect(hasProperty("validationErrors", hasItem(allOf(
                hasProperty("fieldName", is("firstName")),
                hasProperty("errorMessage", is(errorMessage))
        ))));

        sampleEntity.setFirstName(null);
        sampleEntity.validate();
    }

    @Test
    public void testValidationErrorOnMissingLastName() {
        thrown.expect(EntityValidationException.class);
        thrown.expect(hasProperty("message", is("validation.error")));
        thrown.expect(hasProperty("validationErrors", hasSize(1)));
        thrown.expect(hasProperty("validationErrors", hasItem(allOf(
                hasProperty("fieldName", is("firstName")),
                hasProperty("errorMessage", is(errorMessage))
        ))));

        sampleEntity.setLastName(null);
        sampleEntity.validate();
    }

    @Test
    public void testValidationErrorOnMissingMultipleProperties() {
        thrown.expect(EntityValidationException.class);
        thrown.expect(hasProperty("message", is("validation.error")));
        thrown.expect(hasProperty("validationErrors", hasSize(3)));
        thrown.expect(hasProperty("fieldErrors", hasItem(allOf(
                hasItem(allOf(hasProperty("fieldName", is("primaryKey")),
                        hasProperty("errorMessage", is(errorMessage)))),
                hasItem(allOf(hasProperty("fieldName", is("firstName")),
                        hasProperty("errorMessage", is(errorMessage)))),
                hasItem(allOf(hasProperty("fieldName", is("lastName")),
                        hasProperty("errorMessage", is(errorMessage))))
        ))));

        sampleEntity.setPrimaryKey(null);
        sampleEntity.setFirstName(null);
        sampleEntity.setLastName(null);
        sampleEntity.validate();
    }

}
