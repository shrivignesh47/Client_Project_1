package com.mycompany.myapp.domain;

import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.status;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Lead.
 */
@Document(collection = "lead")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Lead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("first_name")
    private String first_name;

    @NotNull(message = "must not be null")
    @Field("last_name")
    private String last_name;

    @NotNull(message = "must not be null")
    @Field("title")
    private String title;

    @NotNull(message = "must not be null")
    @Field("phone_no")
    private Integer phone_no;

    @Field("fax")
    private String fax;

    @NotNull(message = "must not be null")
    @Field("lead_source")
    private String lead_source;

    @NotNull(message = "must not be null")
    @Field("lead_status")
    private status lead_status;

    @NotNull(message = "must not be null")
    @Field("industry")
    private String industry;

    @NotNull(message = "must not be null")
    @Field("no_of_emp")
    private Integer no_of_Emp;

    @NotNull(message = "must not be null")
    @Field("annual_revenue")
    private Float annual_revenue;

    @NotNull(message = "must not be null")
    @Field("rating")
    private rating rating;

    @NotNull(message = "must not be null")
    @Field("street")
    private String street;

    @NotNull(message = "must not be null")
    @Field("state")
    private String state;

    @NotNull(message = "must not be null")
    @Field("city")
    private String city;

    @NotNull(message = "must not be null")
    @Field("zipcode")
    private Integer zipcode;

    @NotNull(message = "must not be null")
    @Field("country")
    private String country;

    @Field("description")
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public String getId() {
        return this.id;
    }

    public Lead id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public Lead first_name(String first_name) {
        this.setFirst_name(first_name);
        return this;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public Lead last_name(String last_name) {
        this.setLast_name(last_name);
        return this;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getTitle() {
        return this.title;
    }

    public Lead title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPhone_no() {
        return this.phone_no;
    }

    public Lead phone_no(Integer phone_no) {
        this.setPhone_no(phone_no);
        return this;
    }

    public void setPhone_no(Integer phone_no) {
        this.phone_no = phone_no;
    }

    public String getFax() {
        return this.fax;
    }

    public Lead fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getLead_source() {
        return this.lead_source;
    }

    public Lead lead_source(String lead_source) {
        this.setLead_source(lead_source);
        return this;
    }

    public void setLead_source(String lead_source) {
        this.lead_source = lead_source;
    }

    public status getLead_status() {
        return this.lead_status;
    }

    public Lead lead_status(status lead_status) {
        this.setLead_status(lead_status);
        return this;
    }

    public void setLead_status(status lead_status) {
        this.lead_status = lead_status;
    }

    public String getIndustry() {
        return this.industry;
    }

    public Lead industry(String industry) {
        this.setIndustry(industry);
        return this;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Integer getNo_of_Emp() {
        return this.no_of_Emp;
    }

    public Lead no_of_Emp(Integer no_of_Emp) {
        this.setNo_of_Emp(no_of_Emp);
        return this;
    }

    public void setNo_of_Emp(Integer no_of_Emp) {
        this.no_of_Emp = no_of_Emp;
    }

    public Float getAnnual_revenue() {
        return this.annual_revenue;
    }

    public Lead annual_revenue(Float annual_revenue) {
        this.setAnnual_revenue(annual_revenue);
        return this;
    }

    public void setAnnual_revenue(Float annual_revenue) {
        this.annual_revenue = annual_revenue;
    }

    public rating getRating() {
        return this.rating;
    }

    public Lead rating(rating rating) {
        this.setRating(rating);
        return this;
    }

    public void setRating(rating rating) {
        this.rating = rating;
    }

    public String getStreet() {
        return this.street;
    }

    public Lead street(String street) {
        this.setStreet(street);
        return this;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return this.state;
    }

    public Lead state(String state) {
        this.setState(state);
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public Lead city(String city) {
        this.setCity(city);
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZipcode() {
        return this.zipcode;
    }

    public Lead zipcode(Integer zipcode) {
        this.setZipcode(zipcode);
        return this;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return this.country;
    }

    public Lead country(String country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDescription() {
        return this.description;
    }

    public Lead description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lead)) {
            return false;
        }
        return getId() != null && getId().equals(((Lead) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Lead{" +
            "id=" + getId() +
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
