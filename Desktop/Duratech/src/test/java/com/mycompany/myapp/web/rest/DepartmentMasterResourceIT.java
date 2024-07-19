package com.mycompany.myapp.web.rest;

import static com.mycompany.myapp.domain.DepartmentMasterAsserts.*;
import static com.mycompany.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.DepartmentMaster;
import com.mycompany.myapp.repository.DepartmentMasterRepository;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
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
 * Integration tests for the {@link DepartmentMasterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class DepartmentMasterResourceIT {

    private static final String DEFAULT_DEPT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPT_SHNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPT_SHNAME = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final Instant DEFAULT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String ENTITY_API_URL = "/api/department-masters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DepartmentMasterRepository departmentMasterRepository;

    @Autowired
    private WebTestClient webTestClient;

    private DepartmentMaster departmentMaster;

    private DepartmentMaster insertedDepartmentMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartmentMaster createEntity() {
        DepartmentMaster departmentMaster = new DepartmentMaster()
            .dept_Name(DEFAULT_DEPT_NAME)
            .dept_shname(DEFAULT_DEPT_SHNAME)
            .status(DEFAULT_STATUS)
            .date(DEFAULT_DATE);
        return departmentMaster;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DepartmentMaster createUpdatedEntity() {
        DepartmentMaster departmentMaster = new DepartmentMaster()
            .dept_Name(UPDATED_DEPT_NAME)
            .dept_shname(UPDATED_DEPT_SHNAME)
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE);
        return departmentMaster;
    }

    @BeforeEach
    public void initTest() {
        departmentMaster = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedDepartmentMaster != null) {
            departmentMasterRepository.delete(insertedDepartmentMaster).block();
            insertedDepartmentMaster = null;
        }
    }

    @Test
    void createDepartmentMaster() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DepartmentMaster
        var returnedDepartmentMaster = webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(DepartmentMaster.class)
            .returnResult()
            .getResponseBody();

        // Validate the DepartmentMaster in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDepartmentMasterUpdatableFieldsEquals(returnedDepartmentMaster, getPersistedDepartmentMaster(returnedDepartmentMaster));

        insertedDepartmentMaster = returnedDepartmentMaster;
    }

    @Test
    void createDepartmentMasterWithExistingId() throws Exception {
        // Create the DepartmentMaster with an existing ID
        departmentMaster.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    void checkStatusIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        departmentMaster.setStatus(null);

        // Create the DepartmentMaster, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void checkDateIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        departmentMaster.setDate(null);

        // Create the DepartmentMaster, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    void getAllDepartmentMasters() {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        // Get all the departmentMasterList
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
            .value(hasItem(departmentMaster.getId()))
            .jsonPath("$.[*].dept_Name")
            .value(hasItem(DEFAULT_DEPT_NAME))
            .jsonPath("$.[*].dept_shname")
            .value(hasItem(DEFAULT_DEPT_SHNAME))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS))
            .jsonPath("$.[*].date")
            .value(hasItem(DEFAULT_DATE.toString()));
    }

    @Test
    void getDepartmentMaster() {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        // Get the departmentMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, departmentMaster.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(departmentMaster.getId()))
            .jsonPath("$.dept_Name")
            .value(is(DEFAULT_DEPT_NAME))
            .jsonPath("$.dept_shname")
            .value(is(DEFAULT_DEPT_SHNAME))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS))
            .jsonPath("$.date")
            .value(is(DEFAULT_DATE.toString()));
    }

    @Test
    void getNonExistingDepartmentMaster() {
        // Get the departmentMaster
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_PROBLEM_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingDepartmentMaster() throws Exception {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departmentMaster
        DepartmentMaster updatedDepartmentMaster = departmentMasterRepository.findById(departmentMaster.getId()).block();
        updatedDepartmentMaster.dept_Name(UPDATED_DEPT_NAME).dept_shname(UPDATED_DEPT_SHNAME).status(UPDATED_STATUS).date(UPDATED_DATE);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedDepartmentMaster.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(updatedDepartmentMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDepartmentMasterToMatchAllProperties(updatedDepartmentMaster);
    }

    @Test
    void putNonExistingDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, departmentMaster.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithIdMismatchDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void putWithMissingIdPathParamDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void partialUpdateDepartmentMasterWithPatch() throws Exception {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departmentMaster using partial update
        DepartmentMaster partialUpdatedDepartmentMaster = new DepartmentMaster();
        partialUpdatedDepartmentMaster.setId(departmentMaster.getId());

        partialUpdatedDepartmentMaster.status(UPDATED_STATUS).date(UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDepartmentMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedDepartmentMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DepartmentMaster in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDepartmentMasterUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDepartmentMaster, departmentMaster),
            getPersistedDepartmentMaster(departmentMaster)
        );
    }

    @Test
    void fullUpdateDepartmentMasterWithPatch() throws Exception {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the departmentMaster using partial update
        DepartmentMaster partialUpdatedDepartmentMaster = new DepartmentMaster();
        partialUpdatedDepartmentMaster.setId(departmentMaster.getId());

        partialUpdatedDepartmentMaster
            .dept_Name(UPDATED_DEPT_NAME)
            .dept_shname(UPDATED_DEPT_SHNAME)
            .status(UPDATED_STATUS)
            .date(UPDATED_DATE);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedDepartmentMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(partialUpdatedDepartmentMaster))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the DepartmentMaster in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDepartmentMasterUpdatableFieldsEquals(
            partialUpdatedDepartmentMaster,
            getPersistedDepartmentMaster(partialUpdatedDepartmentMaster)
        );
    }

    @Test
    void patchNonExistingDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, departmentMaster.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithIdMismatchDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, UUID.randomUUID().toString())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void patchWithMissingIdPathParamDepartmentMaster() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        departmentMaster.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(om.writeValueAsBytes(departmentMaster))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the DepartmentMaster in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    void deleteDepartmentMaster() {
        // Initialize the database
        insertedDepartmentMaster = departmentMasterRepository.save(departmentMaster).block();

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the departmentMaster
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, departmentMaster.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return departmentMasterRepository.count().block();
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

    protected DepartmentMaster getPersistedDepartmentMaster(DepartmentMaster departmentMaster) {
        return departmentMasterRepository.findById(departmentMaster.getId()).block();
    }

    protected void assertPersistedDepartmentMasterToMatchAllProperties(DepartmentMaster expectedDepartmentMaster) {
        assertDepartmentMasterAllPropertiesEquals(expectedDepartmentMaster, getPersistedDepartmentMaster(expectedDepartmentMaster));
    }

    protected void assertPersistedDepartmentMasterToMatchUpdatableProperties(DepartmentMaster expectedDepartmentMaster) {
        assertDepartmentMasterAllUpdatablePropertiesEquals(
            expectedDepartmentMaster,
            getPersistedDepartmentMaster(expectedDepartmentMaster)
        );
    }
}
