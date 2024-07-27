import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILead } from 'app/shared/model/lead.model';
import { status } from 'app/shared/model/enumerations/status.model';
import { rating } from 'app/shared/model/enumerations/rating.model';
import { getEntity, updateEntity, createEntity, reset } from './lead.reducer';

export const LeadUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const leadEntity = useAppSelector(state => state.lead.entity);
  const loading = useAppSelector(state => state.lead.loading);
  const updating = useAppSelector(state => state.lead.updating);
  const updateSuccess = useAppSelector(state => state.lead.updateSuccess);
  const statusValues = Object.keys(status);
  const ratingValues = Object.keys(rating);

  const handleClose = () => {
    navigate('/lead' + location.search);
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
    if (values.phone_no !== undefined && typeof values.phone_no !== 'number') {
      values.phone_no = Number(values.phone_no);
    }
    if (values.no_of_Emp !== undefined && typeof values.no_of_Emp !== 'number') {
      values.no_of_Emp = Number(values.no_of_Emp);
    }
    if (values.annual_revenue !== undefined && typeof values.annual_revenue !== 'number') {
      values.annual_revenue = Number(values.annual_revenue);
    }
    if (values.zipcode !== undefined && typeof values.zipcode !== 'number') {
      values.zipcode = Number(values.zipcode);
    }

    const entity = {
      ...leadEntity,
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
      ? {}
      : {
          lead_status: 'JUNKLEAD',
          rating: 'ACTIVE',
          ...leadEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="crmApp.lead.home.createOrEditLabel" data-cy="LeadCreateUpdateHeading">
            <Translate contentKey="crmApp.lead.home.createOrEditLabel">Create or edit a Lead</Translate>
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
                  id="lead-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('crmApp.lead.first_name')}
                id="lead-first_name"
                name="first_name"
                data-cy="first_name"
                type="text"
              />
              <ValidatedField
                label={translate('crmApp.lead.last_name')}
                id="lead-last_name"
                name="last_name"
                data-cy="last_name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.title')}
                id="lead-title"
                name="title"
                data-cy="title"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.phone_no')}
                id="lead-phone_no"
                name="phone_no"
                data-cy="phone_no"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('crmApp.lead.fax')} id="lead-fax" name="fax" data-cy="fax" type="text" />
              <ValidatedField
                label={translate('crmApp.lead.lead_source')}
                id="lead-lead_source"
                name="lead_source"
                data-cy="lead_source"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.lead_status')}
                id="lead-lead_status"
                name="lead_status"
                data-cy="lead_status"
                type="select"
              >
                {statusValues.map(status => (
                  <option value={status} key={status}>
                    {translate('crmApp.status.' + status)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('crmApp.lead.industry')}
                id="lead-industry"
                name="industry"
                data-cy="industry"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.no_of_Emp')}
                id="lead-no_of_Emp"
                name="no_of_Emp"
                data-cy="no_of_Emp"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.annual_revenue')}
                id="lead-annual_revenue"
                name="annual_revenue"
                data-cy="annual_revenue"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField label={translate('crmApp.lead.rating')} id="lead-rating" name="rating" data-cy="rating" type="select">
                {ratingValues.map(rating => (
                  <option value={rating} key={rating}>
                    {translate('crmApp.rating.' + rating)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('crmApp.lead.street')}
                id="lead-street"
                name="street"
                data-cy="street"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.state')}
                id="lead-state"
                name="state"
                data-cy="state"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.city')}
                id="lead-city"
                name="city"
                data-cy="city"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.zipcode')}
                id="lead-zipcode"
                name="zipcode"
                data-cy="zipcode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.country')}
                id="lead-country"
                name="country"
                data-cy="country"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('crmApp.lead.description')}
                id="lead-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/lead" replace color="info">
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

export default LeadUpdate;
