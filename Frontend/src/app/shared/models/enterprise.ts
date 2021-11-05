import { EnterpriseLocation } from './enterprise-location';
import { Service } from './service';

export class Enterprise {
    enterpriseId: String;
    enterpriseName: String;
    enterpriseAddress: String;
    enterpriseCity: String;
    enterpriseState: String;
    enterpriseZip: String;
    enterpriseCountry: String;
    enterpriseEmail: String;
    enterpriseContactNo: String;
    enterpriseAlternateNo: String;
    enterprisePoc: String;
    enterpriseStatus: String;
    enterpriseLocation: Array<EnterpriseLocation> = new Array<EnterpriseLocation>();
    parentEntityId: string;
    enterpriseService: Array<Service> = new Array<Service>();
    enterpriseComments: String;
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
}
