import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './lead.reducer';

export const LeadDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const leadEntity = useAppSelector(state => state.lead.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="leadDetailsHeading">
          <Translate contentKey="crmApp.lead.detail.title">Lead</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{leadEntity.id}</dd>
          <dt>
            <span id="first_name">
              <Translate contentKey="crmApp.lead.first_name">First Name</Translate>
            </span>
          </dt>
          <dd>{leadEntity.first_name}</dd>
          <dt>
            <span id="last_name">
              <Translate contentKey="crmApp.lead.last_name">Last Name</Translate>
            </span>
          </dt>
          <dd>{leadEntity.last_name}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="crmApp.lead.title">Title</Translate>
            </span>
          </dt>
          <dd>{leadEntity.title}</dd>
          <dt>
            <span id="phone_no">
              <Translate contentKey="crmApp.lead.phone_no">Phone No</Translate>
            </span>
          </dt>
          <dd>{leadEntity.phone_no}</dd>
          <dt>
            <span id="fax">
              <Translate contentKey="crmApp.lead.fax">Fax</Translate>
            </span>
          </dt>
          <dd>{leadEntity.fax}</dd>
          <dt>
            <span id="lead_source">
              <Translate contentKey="crmApp.lead.lead_source">Lead Source</Translate>
            </span>
          </dt>
          <dd>{leadEntity.lead_source}</dd>
          <dt>
            <span id="lead_status">
              <Translate contentKey="crmApp.lead.lead_status">Lead Status</Translate>
            </span>
          </dt>
          <dd>{leadEntity.lead_status}</dd>
          <dt>
            <span id="industry">
              <Translate contentKey="crmApp.lead.industry">Industry</Translate>
            </span>
          </dt>
          <dd>{leadEntity.industry}</dd>
          <dt>
            <span id="no_of_Emp">
              <Translate contentKey="crmApp.lead.no_of_Emp">No Of Emp</Translate>
            </span>
          </dt>
          <dd>{leadEntity.no_of_Emp}</dd>
          <dt>
            <span id="annual_revenue">
              <Translate contentKey="crmApp.lead.annual_revenue">Annual Revenue</Translate>
            </span>
          </dt>
          <dd>{leadEntity.annual_revenue}</dd>
          <dt>
            <span id="rating">
              <Translate contentKey="crmApp.lead.rating">Rating</Translate>
            </span>
          </dt>
          <dd>{leadEntity.rating}</dd>
          <dt>
            <span id="street">
              <Translate contentKey="crmApp.lead.street">Street</Translate>
            </span>
          </dt>
          <dd>{leadEntity.street}</dd>
          <dt>
            <span id="state">
              <Translate contentKey="crmApp.lead.state">State</Translate>
            </span>
          </dt>
          <dd>{leadEntity.state}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="crmApp.lead.city">City</Translate>
            </span>
          </dt>
          <dd>{leadEntity.city}</dd>
          <dt>
            <span id="zipcode">
              <Translate contentKey="crmApp.lead.zipcode">Zipcode</Translate>
            </span>
          </dt>
          <dd>{leadEntity.zipcode}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="crmApp.lead.country">Country</Translate>
            </span>
          </dt>
          <dd>{leadEntity.country}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="crmApp.lead.description">Description</Translate>
            </span>
          </dt>
          <dd>{leadEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/lead" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/lead/${leadEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LeadDetail;
