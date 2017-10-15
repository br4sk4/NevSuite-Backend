package net.naffets.nevsuite.backend.framework.domain.entity;

import javax.persistence.*;

/**
 * @author br4sk4 / created on 12.10.2017
 */
@Entity
@Table(name = "T_PERSON")
public class Person {

    @Id
    @Basic(optional = false)
    @Column(name = "pers_id")
    private String primaryKey;

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

}
