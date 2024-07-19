package com.mycompany.myapp.domain;

import java.util.UUID;

public class DepartmentMasterTestSamples {

    public static DepartmentMaster getDepartmentMasterSample1() {
        return new DepartmentMaster().id("id1").dept_Name("dept_Name1").dept_shname("dept_shname1").status("status1");
    }

    public static DepartmentMaster getDepartmentMasterSample2() {
        return new DepartmentMaster().id("id2").dept_Name("dept_Name2").dept_shname("dept_shname2").status("status2");
    }

    public static DepartmentMaster getDepartmentMasterRandomSampleGenerator() {
        return new DepartmentMaster()
            .id(UUID.randomUUID().toString())
            .dept_Name(UUID.randomUUID().toString())
            .dept_shname(UUID.randomUUID().toString())
            .status(UUID.randomUUID().toString());
    }
}
