package com.mycompany.myapp.service.dto;

import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Lead} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class LeadDTO implements Serializable {

    private String id;

    private String first_name;

    @NotNull(message = "must not be null")
    private String last_name;

    @NotNull(message = "must not be null")
    private String title;

    @NotNull(message = "must not be null")
    private Integer phone_no;

    private String fax;

    @NotNull(message = "must not be null")
    private String lead_source;

    @NotNull(message = "must not be null")
    private status lead_status;

    @NotNull(message = "must not be null")
    private String industry;

    @NotNull(message = "must not be null")
    private Integer no_of_Emp;

    @NotNull(message = "must not be null")
    private Float annual_revenue;

    @NotNull(message = "must not be null")
    private rating rating;

    @NotNull(message = "must not be null")
    private String street;

    @NotNull(message = "must not be null")
    private String state;

    @NotNull(message = "must not be null")
    private String city;

    @NotNull(message = "must not be null")
    private Integer zipcode;

    @NotNull(message = "must not be null")
    private String country;

    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(Integer phone_no) {
        this.phone_no = phone_no;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLead_source() {
        return lead_source;
    }

    public void setLead_source(String lead_source) {
        this.lead_source = lead_source;
    }

    public status getLead_status() {
        return lead_status;
    }

    public void setLead_status(status lead_status) {
        this.lead_status = lead_status;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getNo_of_Emp() {
        return no_of_Emp;
    }

    public void setNo_of_Emp(Integer no_of_Emp) {
        this.no_of_Emp = no_of_Emp;
    }

    public Float getAnnual_revenue() {
        return annual_revenue;
    }

    public void setAnnual_revenue(Float annual_revenue) {
        this.annual_revenue = annual_revenue;
    }

    public rating getRating() {
        return rating;
    }

    public void setRating(rating rating) {
        this.rating = rating;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LeadDTO)) {
            return false;
        }

        LeadDTO leadDTO = (LeadDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, leadDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LeadDTO{" +
            "id='" + getId() + "'" +
            ", first_name='" + getFirst_name() + "'" +
            ", last_name='" + getLast_name() + "'" +
            ", title='" + getTitle() + "'" +
            ", phone_no=" + getPhone_no() +
            ", fax='" + getFax() + "'" +
            ", lead_source='" + getLead_source() + "'" +
            ", lead_status='" + getLead_status() + "'" +
            ", industry='" + getIndustry() + "'" +
            ", no_of_Emp=" + getNo_of_Emp() +
            ", annual_revenue=" + getAnnual_revenue() +
            ", rating='" + getRating() + "'" +
            ", street='" + getStreet() + "'" +
            ", state='" + getState() + "'" +
            ", city='" + getCity() + "'" +
            ", zipcode=" + getZipcode() +
            ", country='" + getCountry() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
