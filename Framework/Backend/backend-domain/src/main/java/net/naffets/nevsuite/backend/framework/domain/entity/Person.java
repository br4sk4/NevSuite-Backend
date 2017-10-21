package net.naffets.nevsuite.backend.framework.domain.entity;

import net.naffets.nevsuite.backend.framework.core.api.DataTransferObject;
import net.naffets.nevsuite.backend.framework.core.api.Reference;
import net.naffets.nevsuite.backend.framework.core.base.AbstractEntityBean;

import javax.persistence.*;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@Entity
@Table(name = "T_PERSON")
@AttributeOverride(name = "primaryKey", column = @Column(name = "pers_id"))
public class Person<DTO extends DataTransferObject> extends AbstractEntityBean<DTO> {

    @Column(name = "pers_firstname")
    private String firstName;

    @Column(name = "pers_lastname")
    private String lastName;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public DTO asDTO() {
        return null;
    }

    @Override
    public Reference asReference() {
        return null;
    }
}
