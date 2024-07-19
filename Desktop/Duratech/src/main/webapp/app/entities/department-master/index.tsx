import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DepartmentMaster from './department-master';
import DepartmentMasterDetail from './department-master-detail';
import DepartmentMasterUpdate from './department-master-update';
import DepartmentMasterDeleteDialog from './department-master-delete-dialog';

const DepartmentMasterRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DepartmentMaster />} />
    <Route path="new" element={<DepartmentMasterUpdate />} />
    <Route path=":id">
      <Route index element={<DepartmentMasterDetail />} />
      <Route path="edit" element={<DepartmentMasterUpdate />} />
      <Route path="delete" element={<DepartmentMasterDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DepartmentMasterRoutes;
