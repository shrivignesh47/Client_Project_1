package com.mycompany.myapp.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LeadTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Lead getLeadSample1() {
        return new Lead()
            .id("id1")
            .first_name("first_name1")
            .last_name("last_name1")
            .title("title1")
            .phone_no(1)
            .fax("fax1")
            .lead_source("lead_source1")
            .industry("industry1")
            .no_of_Emp(1)
            .street("street1")
            .state("state1")
            .city("city1")
            .zipcode(1)
            .country("country1")
            .description("description1");
    }

    public static Lead getLeadSample2() {
        return new Lead()
            .id("id2")
            .first_name("first_name2")
            .last_name("last_name2")
            .title("title2")
            .phone_no(2)
            .fax("fax2")
            .lead_source("lead_source2")
            .industry("industry2")
            .no_of_Emp(2)
            .street("street2")
            .state("state2")
            .city("city2")
            .zipcode(2)
            .country("country2")
            .description("description2");
    }

    public static Lead getLeadRandomSampleGenerator() {
        return new Lead()
            .id(UUID.randomUUID().toString())
            .first_name(UUID.randomUUID().toString())
            .last_name(UUID.randomUUID().toString())
            .title(UUID.randomUUID().toString())
            .phone_no(intCount.incrementAndGet())
            .fax(UUID.randomUUID().toString())
            .lead_source(UUID.randomUUID().toString())
            .industry(UUID.randomUUID().toString())
            .no_of_Emp(intCount.incrementAndGet())
            .street(UUID.randomUUID().toString())
            .state(UUID.randomUUID().toString())
            .city(UUID.randomUUID().toString())
            .zipcode(intCount.incrementAndGet())
            .country(UUID.randomUUID().toString())
            .description(UUID.randomUUID().toString());
    }
}
