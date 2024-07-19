package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentMasterAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentMasterAllPropertiesEquals(DepartmentMaster expected, DepartmentMaster actual) {
        assertDepartmentMasterAutoGeneratedPropertiesEquals(expected, actual);
        assertDepartmentMasterAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentMasterAllUpdatablePropertiesEquals(DepartmentMaster expected, DepartmentMaster actual) {
        assertDepartmentMasterUpdatableFieldsEquals(expected, actual);
        assertDepartmentMasterUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentMasterAutoGeneratedPropertiesEquals(DepartmentMaster expected, DepartmentMaster actual) {
        assertThat(expected)
            .as("Verify DepartmentMaster auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentMasterUpdatableFieldsEquals(DepartmentMaster expected, DepartmentMaster actual) {
        assertThat(expected)
            .as("Verify DepartmentMaster relevant properties")
            .satisfies(e -> assertThat(e.getDept_Name()).as("check dept_Name").isEqualTo(actual.getDept_Name()))
            .satisfies(e -> assertThat(e.getDept_shname()).as("check dept_shname").isEqualTo(actual.getDept_shname()))
            .satisfies(e -> assertThat(e.getStatus()).as("check status").isEqualTo(actual.getStatus()))
            .satisfies(e -> assertThat(e.getDate()).as("check date").isEqualTo(actual.getDate()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertDepartmentMasterUpdatableRelationshipsEquals(DepartmentMaster expected, DepartmentMaster actual) {}
}
