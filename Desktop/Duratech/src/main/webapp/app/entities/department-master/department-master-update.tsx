import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDepartmentMaster } from 'app/shared/model/department-master.model';
import { getEntity, updateEntity, createEntity, reset } from './department-master.reducer';

export const DepartmentMasterUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const departmentMasterEntity = useAppSelector(state => state.departmentMaster.entity);
  const loading = useAppSelector(state => state.departmentMaster.loading);
  const updating = useAppSelector(state => state.departmentMaster.updating);
  const updateSuccess = useAppSelector(state => state.departmentMaster.updateSuccess);

  const handleClose = () => {
    navigate('/department-master' + location.search);
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    values.date = convertDateTimeToServer(values.date);

    const entity = {
      ...departmentMasterEntity,
      ...values,
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          date: displayDefaultDateTime(),
        }
      : {
          ...departmentMasterEntity,
          date: convertDateTimeFromServer(departmentMasterEntity.date),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="duraApp.departmentMaster.home.createOrEditLabel" data-cy="DepartmentMasterCreateUpdateHeading">
            <Translate contentKey="duraApp.departmentMaster.home.createOrEditLabel">Create or edit a DepartmentMaster</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="department-master-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('duraApp.departmentMaster.dept_Name')}
                id="department-master-dept_Name"
                name="dept_Name"
                data-cy="dept_Name"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('duraApp.departmentMaster.dept_shname')}
                id="department-master-dept_shname"
                name="dept_shname"
                data-cy="dept_shname"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('duraApp.departmentMaster.status')}
                id="department-master-status"
                name="status"
                data-cy="status"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('duraApp.departmentMaster.date')}
                id="department-master-date"
                name="date"
                data-cy="date"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/department-master" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DepartmentMasterUpdate;
