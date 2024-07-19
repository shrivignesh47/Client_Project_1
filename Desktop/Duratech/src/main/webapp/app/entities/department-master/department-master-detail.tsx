import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './department-master.reducer';

export const DepartmentMasterDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const departmentMasterEntity = useAppSelector(state => state.departmentMaster.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="departmentMasterDetailsHeading">
          <Translate contentKey="duraApp.departmentMaster.detail.title">DepartmentMaster</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{departmentMasterEntity.id}</dd>
          <dt>
            <span id="dept_Name">
              <Translate contentKey="duraApp.departmentMaster.dept_Name">Dept Name</Translate>
            </span>
          </dt>
          <dd>{departmentMasterEntity.dept_Name}</dd>
          <dt>
            <span id="dept_shname">
              <Translate contentKey="duraApp.departmentMaster.dept_shname">Dept Shname</Translate>
            </span>
          </dt>
          <dd>{departmentMasterEntity.dept_shname}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="duraApp.departmentMaster.status">Status</Translate>
            </span>
          </dt>
          <dd>{departmentMasterEntity.status}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="duraApp.departmentMaster.date">Date</Translate>
            </span>
          </dt>
          <dd>
            {departmentMasterEntity.date ? <TextFormat value={departmentMasterEntity.date} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
        </dl>
        <Button tag={Link} to="/department-master" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/department-master/${departmentMasterEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DepartmentMasterDetail;
