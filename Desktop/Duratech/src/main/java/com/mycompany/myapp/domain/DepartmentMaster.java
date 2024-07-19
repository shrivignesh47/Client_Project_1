package com.mycompany.myapp.domain;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A DepartmentMaster.
 */
@Document(collection = "department_master")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DepartmentMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("dept_name")
    private String dept_Name;

    @Field("dept_shname")
    private String dept_shname;

    @NotNull(message = "must not be null")
    @Field("status")
    private String status;

    @NotNull(message = "must not be null")
    @Field("date")
    private Instant date;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public DepartmentMaster id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDept_Name() {
        return this.dept_Name;
    }

    public DepartmentMaster dept_Name(String dept_Name) {
        this.setDept_Name(dept_Name);
        return this;
    }

    public void setDept_Name(String dept_Name) {
        this.dept_Name = dept_Name;
    }

    public String getDept_shname() {
        return this.dept_shname;
    }

    public DepartmentMaster dept_shname(String dept_shname) {
        this.setDept_shname(dept_shname);
        return this;
    }

    public void setDept_shname(String dept_shname) {
        this.dept_shname = dept_shname;
    }

    public String getStatus() {
        return this.status;
    }

    public DepartmentMaster status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Instant getDate() {
        return this.date;
    }

    public DepartmentMaster date(Instant date) {
        this.setDate(date);
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepartmentMaster)) {
            return false;
        }
        return getId() != null && getId().equals(((DepartmentMaster) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepartmentMaster{" +
            "id=" + getId() +
            ", dept_Name='" + getDept_Name() + "'" +
            ", dept_shname='" + getDept_shname() + "'" +
            ", status='" + getStatus() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }
}
