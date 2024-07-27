package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.LeadAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Lead;
import com.mycompany.myapp.domain.enumeration.rating;
import com.mycompany.myapp.domain.enumeration.status;
import com.mycompany.myapp.repository.LeadRepository;
import com.mycompany.myapp.service.dto.LeadDTO;
import com.mycompany.myapp.service.mapper.LeadMapper;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Integration tests for the {@link LeadResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class LeadResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final Integer DEFAULT_PHONE_NO = 1;
    private static final Integer UPDATED_PHONE_NO = 2;

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_SOURCE = "BBBBBBBBBB";

    private static final status DEFAULT_LEAD_STATUS = status.JUNKLEAD;
    private static final status UPDATED_LEAD_STATUS = status.LOSTLEAD;

    private static final String DEFAULT_INDUSTRY = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY = "BBBBBBBBBB";

    private static final Integer DEFAULT_NO_OF_EMP = 1;
    private static final Integer UPDATED_NO_OF_EMP = 2;

    private static final Float DEFAULT_ANNUAL_REVENUE = 1F;
    private static final Float UPDATED_ANNUAL_REVENUE = 2F;

    private static final rating DEFAULT_RATING = rating.ACTIVE;
    private static final rating UPDATED_RATING = rating.MARKETFAILED;

    private static final String DEFAULT_STREET = "AAAAAAAAAA";
    private static final String UPDATED_STREET = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ZIPCODE = 1;
    private static final Integer UPDATED_ZIPCODE = 2;

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/leads";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadMapper leadMapper;

    @Autowired
    private WebTestClient webTestClient;

    private Lead lead;

    private Lead insertedLead;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lead createEntity() {
        Lead lead = new Lead()
            .first_name(DEFAULT_FIRST_NAME)
            .last_name(DEFAULT_LAST_NAME)
            .title(DEFAULT_TITLE)
            .phone_no(DEFAULT_PHONE_NO)
            .fax(DEFAULT_FAX)
            .lead_source(DEFAULT_LEAD_SOURCE)
            .lead_status(DEFAULT_LEAD_STATUS)
            .industry(DEFAULT_INDUSTRY)
            .no_of_Emp(DEFAULT_NO_OF_EMP)
            .annual_revenue(DEFAULT_ANNUAL_REVENUE)
            .rating(DEFAULT_RATING)
            .street(DEFAULT_STREET)
            .state(DEFAULT_STATE)
            .city(DEFAULT_CITY)
            .zipcode(DEFAULT_ZIPCODE)
            .country(DEFAULT_COUNTRY)
            .description(DEFAULT_DESCRIPTION);
        return lead;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lead createUpdatedEntity() {
        Lead lead = new Lead()
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .phone_no(UPDATED_PHONE_NO)
            .fax(UPDATED_FAX)
            .lead_source(UPDATED_LEAD_SOURCE)
            .lead_status(UPDATED_LEAD_STATUS)
            .industry(UPDATED_INDUSTRY)
            .no_of_Emp(UPDATED_NO_OF_EMP)
            .annual_revenue(UPDATED_ANNUAL_REVENUE)
            .rating(UPDATED_RATING)
            .street(UPDATED_STREET)
            .state(UPDATED_STATE)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);
        return lead;
    }

    @BeforeEach
    public void initTest() {
        lead = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLead != null) {
            leadRepository.delete(insertedLead).block();
            insertedLead = null;
        }
    }

    @Test
    void createLead() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);
        var returnedLeadDTO = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(LeadDTO.class)
            .returnResult()
            .getResponseBody();

        // Validate the Lead in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedLead = leadMapper.toEntity(returnedLeadDTO);
        assertLeadUpdatableFieldsEquals(returnedLead, getPersistedLead(returnedLead));

        insertedLead = returnedLead;
    }

    @Test
    void createLeadWithExistingId() throws Exception {
        // Create the Lead with an existing ID
        lead.setId("existing_id");
        LeadDTO leadDTO = leadMapper.toDto(lead);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkLast_nameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setLast_name(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkTitleIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setTitle(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkPhone_noIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setPhone_no(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLead_sourceIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setLead_source(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkLead_statusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setLead_status(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkIndustryIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setIndustry(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkNo_of_EmpIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setNo_of_Emp(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkAnnual_revenueIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setAnnual_revenue(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkRatingIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setRating(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStreetIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setStreet(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkStateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setState(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCityIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setCity(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkZipcodeIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setZipcode(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkCountryIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        lead.setCountry(null);

        // Create the Lead, which fails.
        LeadDTO leadDTO = leadMapper.toDto(lead);

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllLeads() {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        // Get all the leadList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(lead.getId()))
            .jsonPath("$.[*].first_name")
            .value(hasItem(DEFAULT_FIRST_NAME))
            .jsonPath("$.[*].last_name")
            .value(hasItem(DEFAULT_LAST_NAME))
            .jsonPath("$.[*].title")
            .value(hasItem(DEFAULT_TITLE))
            .jsonPath("$.[*].phone_no")
            .value(hasItem(DEFAULT_PHONE_NO))
            .jsonPath("$.[*].fax")
            .value(hasItem(DEFAULT_FAX))
            .jsonPath("$.[*].lead_source")
            .value(hasItem(DEFAULT_LEAD_SOURCE))
            .jsonPath("$.[*].lead_status")
            .value(hasItem(DEFAULT_LEAD_STATUS.toString()))
            .jsonPath("$.[*].industry")
            .value(hasItem(DEFAULT_INDUSTRY))
            .jsonPath("$.[*].no_of_Emp")
            .value(hasItem(DEFAULT_NO_OF_EMP))
            .jsonPath("$.[*].annual_revenue")
            .value(hasItem(DEFAULT_ANNUAL_REVENUE.doubleValue()))
            .jsonPath("$.[*].rating")
            .value(hasItem(DEFAULT_RATING.toString()))
            .jsonPath("$.[*].street")
            .value(hasItem(DEFAULT_STREET))
            .jsonPath("$.[*].state")
            .value(hasItem(DEFAULT_STATE))
            .jsonPath("$.[*].city")
            .value(hasItem(DEFAULT_CITY))
            .jsonPath("$.[*].zipcode")
            .value(hasItem(DEFAULT_ZIPCODE))
            .jsonPath("$.[*].country")
            .value(hasItem(DEFAULT_COUNTRY))
            .jsonPath("$.[*].description")
            .value(hasItem(DEFAULT_DESCRIPTION));
    }

    @Test
    void getLead() {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        // Get the lead
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, lead.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(lead.getId()))
            .jsonPath("$.first_name")
            .value(is(DEFAULT_FIRST_NAME))
            .jsonPath("$.last_name")
            .value(is(DEFAULT_LAST_NAME))
            .jsonPath("$.title")
            .value(is(DEFAULT_TITLE))
            .jsonPath("$.phone_no")
            .value(is(DEFAULT_PHONE_NO))
            .jsonPath("$.fax")
            .value(is(DEFAULT_FAX))
            .jsonPath("$.lead_source")
            .value(is(DEFAULT_LEAD_SOURCE))
            .jsonPath("$.lead_status")
            .value(is(DEFAULT_LEAD_STATUS.toString()))
            .jsonPath("$.industry")
            .value(is(DEFAULT_INDUSTRY))
            .jsonPath("$.no_of_Emp")
            .value(is(DEFAULT_NO_OF_EMP))
            .jsonPath("$.annual_revenue")
            .value(is(DEFAULT_ANNUAL_REVENUE.doubleValue()))
            .jsonPath("$.rating")
            .value(is(DEFAULT_RATING.toString()))
            .jsonPath("$.street")
            .value(is(DEFAULT_STREET))
            .jsonPath("$.state")
            .value(is(DEFAULT_STATE))
            .jsonPath("$.city")
            .value(is(DEFAULT_CITY))
            .jsonPath("$.zipcode")
            .value(is(DEFAULT_ZIPCODE))
            .jsonPath("$.country")
            .value(is(DEFAULT_COUNTRY))
            .jsonPath("$.description")
            .value(is(DEFAULT_DESCRIPTION));
    }

    @Test
    void getNonExistingLead() {
        // Get the lead
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingLead() throws Exception {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lead
        Lead updatedLead = leadRepository.findById(lead.getId()).block();
        updatedLead
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .phone_no(UPDATED_PHONE_NO)
            .fax(UPDATED_FAX)
            .lead_source(UPDATED_LEAD_SOURCE)
            .lead_status(UPDATED_LEAD_STATUS)
            .industry(UPDATED_INDUSTRY)
            .no_of_Emp(UPDATED_NO_OF_EMP)
            .annual_revenue(UPDATED_ANNUAL_REVENUE)
            .rating(UPDATED_RATING)
            .street(UPDATED_STREET)
            .state(UPDATED_STATE)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);
        LeadDTO leadDTO = leadMapper.toDto(updatedLead);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, leadDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLeadToMatchAllProperties(updatedLead);
    }

    @Test
    void putNonExistingLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, leadDTO.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateLeadWithPatch() throws Exception {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lead using partial update
        Lead partialUpdatedLead = new Lead();
        partialUpdatedLead.setId(lead.getId());

        partialUpdatedLead
            .title(UPDATED_TITLE)
            .phone_no(UPDATED_PHONE_NO)
            .fax(UPDATED_FAX)
            .lead_source(UPDATED_LEAD_SOURCE)
            .annual_revenue(UPDATED_ANNUAL_REVENUE)
            .street(UPDATED_STREET)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLead.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedLead))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Lead in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeadUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedLead, lead), getPersistedLead(lead));
    }

    @Test
    void fullUpdateLeadWithPatch() throws Exception {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the lead using partial update
        Lead partialUpdatedLead = new Lead();
        partialUpdatedLead.setId(lead.getId());

        partialUpdatedLead
            .first_name(UPDATED_FIRST_NAME)
            .last_name(UPDATED_LAST_NAME)
            .title(UPDATED_TITLE)
            .phone_no(UPDATED_PHONE_NO)
            .fax(UPDATED_FAX)
            .lead_source(UPDATED_LEAD_SOURCE)
            .lead_status(UPDATED_LEAD_STATUS)
            .industry(UPDATED_INDUSTRY)
            .no_of_Emp(UPDATED_NO_OF_EMP)
            .annual_revenue(UPDATED_ANNUAL_REVENUE)
            .rating(UPDATED_RATING)
            .street(UPDATED_STREET)
            .state(UPDATED_STATE)
            .city(UPDATED_CITY)
            .zipcode(UPDATED_ZIPCODE)
            .country(UPDATED_COUNTRY)
            .description(UPDATED_DESCRIPTION);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedLead.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedLead))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the Lead in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLeadUpdatableFieldsEquals(partialUpdatedLead, getPersistedLead(partialUpdatedLead));
    }

    @Test
    void patchNonExistingLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, leadDTO.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamLead() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        lead.setId(UUID.randomUUID().toString());

        // Create the Lead
        LeadDTO leadDTO = leadMapper.toDto(lead);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(leadDTO))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the Lead in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteLead() {
        // Initialize the database
        insertedLead = leadRepository.save(lead).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the lead
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, lead.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return leadRepository.count().block();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Lead getPersistedLead(Lead lead) {
        return leadRepository.findById(lead.getId()).block();
    }

    protected void assertPersistedLeadToMatchAllProperties(Lead expectedLead) {
        assertLeadAllPropertiesEquals(expectedLead, getPersistedLead(expectedLead));
    }

    protected void assertPersistedLeadToMatchUpdatableProperties(Lead expectedLead) {
        assertLeadAllUpdatablePropertiesEquals(expectedLead, getPersistedLead(expectedLead));
    }
}
