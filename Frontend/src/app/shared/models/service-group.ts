import { Service } from './service';

export class ServiceGroup {
    serviceGroupId: string;
    serviceGroupName: string;
    serviceGroupDescription: string;
    service: Array<Service> = new Array<Service>();
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
}
