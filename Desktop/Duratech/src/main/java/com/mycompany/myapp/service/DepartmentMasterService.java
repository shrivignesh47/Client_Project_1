package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DepartmentMaster;
import com.mycompany.myapp.repository.DepartmentMasterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.DepartmentMaster}.
 */
@Service
public class DepartmentMasterService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentMasterService.class);

    private final DepartmentMasterRepository departmentMasterRepository;

    public DepartmentMasterService(DepartmentMasterRepository departmentMasterRepository) {
        this.departmentMasterRepository = departmentMasterRepository;
    }

    /**
     * Save a departmentMaster.
     *
     * @param departmentMaster the entity to save.
     * @return the persisted entity.
     */
    public Mono<DepartmentMaster> save(DepartmentMaster departmentMaster) {
        log.debug("Request to save DepartmentMaster : {}", departmentMaster);
        return departmentMasterRepository.save(departmentMaster);
    }

    /**
     * Update a departmentMaster.
     *
     * @param departmentMaster the entity to save.
     * @return the persisted entity.
     */
    public Mono<DepartmentMaster> update(DepartmentMaster departmentMaster) {
        log.debug("Request to update DepartmentMaster : {}", departmentMaster);
        return departmentMasterRepository.save(departmentMaster);
    }

    /**
     * Partially update a departmentMaster.
     *
     * @param departmentMaster the entity to update partially.
     * @return the persisted entity.
     */
    public Mono<DepartmentMaster> partialUpdate(DepartmentMaster departmentMaster) {
        log.debug("Request to partially update DepartmentMaster : {}", departmentMaster);

        return departmentMasterRepository
            .findById(departmentMaster.getId())
            .map(existingDepartmentMaster -> {
                if (departmentMaster.getDept_Name() != null) {
                    existingDepartmentMaster.setDept_Name(departmentMaster.getDept_Name());
                }
                if (departmentMaster.getDept_shname() != null) {
                    existingDepartmentMaster.setDept_shname(departmentMaster.getDept_shname());
                }
                if (departmentMaster.getStatus() != null) {
                    existingDepartmentMaster.setStatus(departmentMaster.getStatus());
                }
                if (departmentMaster.getDate() != null) {
                    existingDepartmentMaster.setDate(departmentMaster.getDate());
                }

                return existingDepartmentMaster;
            })
            .flatMap(departmentMasterRepository::save);
    }

    /**
     * Get all the departmentMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    public Flux<DepartmentMaster> findAll(Pageable pageable) {
        log.debug("Request to get all DepartmentMasters");
        return departmentMasterRepository.findAllBy(pageable);
    }

    /**
     * Returns the number of departmentMasters available.
     * @return the number of entities in the database.
     *
     */
    public Mono<Long> countAll() {
        return departmentMasterRepository.count();
    }

    /**
     * Get one departmentMaster by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    public Mono<DepartmentMaster> findOne(String id) {
        log.debug("Request to get DepartmentMaster : {}", id);
        return departmentMasterRepository.findById(id);
    }

    /**
     * Delete the departmentMaster by id.
     *
     * @param id the id of the entity.
     * @return a Mono to signal the deletion
     */
    public Mono<Void> delete(String id) {
        log.debug("Request to delete DepartmentMaster : {}", id);
        return departmentMasterRepository.deleteById(id);
    }
}
