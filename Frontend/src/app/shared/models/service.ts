import { ServiceGroup } from './service-group';
import { DeviceType } from './device-type';

export class Service {
    serviceId: string;
    serviceName: string;
    serviceDescription: string;
    serviceStatus: string;
    serviceType: string;
    serviceGroup = new ServiceGroup();
    serviceDeviceTypes: Array<DeviceType> = new Array<DeviceType>();
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
}
