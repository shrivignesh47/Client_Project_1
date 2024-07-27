import { status } from 'app/shared/model/enumerations/status.model';
import { rating } from 'app/shared/model/enumerations/rating.model';

export interface ILead {
  id?: string;
  first_name?: string | null;
  last_name?: string;
  title?: string;
  phone_no?: number;
  fax?: string | null;
  lead_source?: string;
  lead_status?: keyof typeof status;
  industry?: string;
  no_of_Emp?: number;
  annual_revenue?: number;
  rating?: keyof typeof rating;
  street?: string;
  state?: string;
  city?: string;
  zipcode?: number;
  country?: string;
  description?: string | null;
}

export const defaultValue: Readonly<ILead> = {};
