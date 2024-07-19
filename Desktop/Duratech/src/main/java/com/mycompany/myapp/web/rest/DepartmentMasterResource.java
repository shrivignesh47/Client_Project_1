package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.DepartmentMaster;
import com.mycompany.myapp.repository.DepartmentMasterRepository;
import com.mycompany.myapp.service.DepartmentMasterService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.ForwardedHeaderUtils;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.DepartmentMaster}.
 */
@RestController
@RequestMapping("/api/department-masters")
public class DepartmentMasterResource {

    private static final Logger log = LoggerFactory.getLogger(DepartmentMasterResource.class);

    private static final String ENTITY_NAME = "departmentMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartmentMasterService departmentMasterService;

    private final DepartmentMasterRepository departmentMasterRepository;

    public DepartmentMasterResource(
        DepartmentMasterService departmentMasterService,
        DepartmentMasterRepository departmentMasterRepository
    ) {
        this.departmentMasterService = departmentMasterService;
        this.departmentMasterRepository = departmentMasterRepository;
    }

    /**
     * {@code POST  /department-masters} : Create a new departmentMaster.
     *
     * @param departmentMaster the departmentMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new departmentMaster, or with status {@code 400 (Bad Request)} if the departmentMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public Mono<ResponseEntity<DepartmentMaster>> createDepartmentMaster(@Valid @RequestBody DepartmentMaster departmentMaster)
        throws URISyntaxException {
        log.debug("REST request to save DepartmentMaster : {}", departmentMaster);
        if (departmentMaster.getId() != null) {
            throw new BadRequestAlertException("A new departmentMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return departmentMasterService
            .save(departmentMaster)
            .map(result -> {
                try {
                    return ResponseEntity.created(new URI("/api/department-masters/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /department-masters/:id} : Updates an existing departmentMaster.
     *
     * @param id the id of the departmentMaster to save.
     * @param departmentMaster the departmentMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departmentMaster,
     * or with status {@code 400 (Bad Request)} if the departmentMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the departmentMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public Mono<ResponseEntity<DepartmentMaster>> updateDepartmentMaster(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody DepartmentMaster departmentMaster
    ) throws URISyntaxException {
        log.debug("REST request to update DepartmentMaster : {}, {}", id, departmentMaster);
        if (departmentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departmentMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return departmentMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return departmentMasterService
                    .update(departmentMaster)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        result ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId()))
                                .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /department-masters/:id} : Partial updates given fields of an existing departmentMaster, field will ignore if it is null
     *
     * @param id the id of the departmentMaster to save.
     * @param departmentMaster the departmentMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated departmentMaster,
     * or with status {@code 400 (Bad Request)} if the departmentMaster is not valid,
     * or with status {@code 404 (Not Found)} if the departmentMaster is not found,
     * or with status {@code 500 (Internal Server Error)} if the departmentMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DepartmentMaster>> partialUpdateDepartmentMaster(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody DepartmentMaster departmentMaster
    ) throws URISyntaxException {
        log.debug("REST request to partial update DepartmentMaster partially : {}, {}", id, departmentMaster);
        if (departmentMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, departmentMaster.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return departmentMasterRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DepartmentMaster> result = departmentMasterService.partialUpdate(departmentMaster);

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(
                        res ->
                            ResponseEntity.ok()
                                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId()))
                                .body(res)
                    );
            });
    }

    /**
     * {@code GET  /department-masters} : get all the departmentMasters.
     *
     * @param pageable the pagination information.
     * @param request a {@link ServerHttpRequest} request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departmentMasters in body.
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<List<DepartmentMaster>>> getAllDepartmentMasters(
        @org.springdoc.core.annotations.ParameterObject Pageable pageable,
        ServerHttpRequest request
    ) {
        log.debug("REST request to get a page of DepartmentMasters");
        return departmentMasterService
            .countAll()
            .zipWith(departmentMasterService.findAll(pageable).collectList())
            .map(
                countWithEntities ->
                    ResponseEntity.ok()
                        .headers(
                            PaginationUtil.generatePaginationHttpHeaders(
                                ForwardedHeaderUtils.adaptFromForwardedHeaders(request.getURI(), request.getHeaders()),
                                new PageImpl<>(countWithEntities.getT2(), pageable, countWithEntities.getT1())
                            )
                        )
                        .body(countWithEntities.getT2())
            );
    }

    /**
     * {@code GET  /department-masters/:id} : get the "id" departmentMaster.
     *
     * @param id the id of the departmentMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the departmentMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<DepartmentMaster>> getDepartmentMaster(@PathVariable("id") String id) {
        log.debug("REST request to get DepartmentMaster : {}", id);
        Mono<DepartmentMaster> departmentMaster = departmentMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(departmentMaster);
    }

    /**
     * {@code DELETE  /department-masters/:id} : delete the "id" departmentMaster.
     *
     * @param id the id of the departmentMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteDepartmentMaster(@PathVariable("id") String id) {
        log.debug("REST request to delete DepartmentMaster : {}", id);
        return departmentMasterService
            .delete(id)
            .then(
                Mono.just(
                    ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build()
                )
            );
    }
}
