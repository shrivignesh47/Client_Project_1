package com.mycompany.myapp.domain;

import static com.mycompany.myapp.domain.DepartmentMasterTestSamples.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartmentMasterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DepartmentMaster.class);
        DepartmentMaster departmentMaster1 = getDepartmentMasterSample1();
        DepartmentMaster departmentMaster2 = new DepartmentMaster();
        assertThat(departmentMaster1).isNotEqualTo(departmentMaster2);

        departmentMaster2.setId(departmentMaster1.getId());
        assertThat(departmentMaster1).isEqualTo(departmentMaster2);

        departmentMaster2 = getDepartmentMasterSample2();
        assertThat(departmentMaster1).isNotEqualTo(departmentMaster2);
    }
}
