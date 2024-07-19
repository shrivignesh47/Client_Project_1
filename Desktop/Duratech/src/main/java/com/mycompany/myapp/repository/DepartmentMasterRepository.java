package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DepartmentMaster;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Spring Data MongoDB reactive repository for the DepartmentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepartmentMasterRepository extends ReactiveMongoRepository<DepartmentMaster, String> {
    Flux<DepartmentMaster> findAllBy(Pageable pageable);
}
