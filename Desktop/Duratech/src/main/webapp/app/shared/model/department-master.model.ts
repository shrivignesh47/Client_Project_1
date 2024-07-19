import dayjs from 'dayjs';

export interface IDepartmentMaster {
  id?: string;
  dept_Name?: string | null;
  dept_shname?: string | null;
  status?: string;
  date?: dayjs.Dayjs;
}

export const defaultValue: Readonly<IDepartmentMaster> = {};
