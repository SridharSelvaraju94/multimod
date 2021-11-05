import { DeviceType } from './device-type';

export class ServiceView {
    serviceId: string;
    serviceName: string;
    serviceDescription: string;
    serviceStatus: string;
    serviceType: string;
    serviceGroupName: String;
    serviceDeviceTypes: Array<DeviceType> = new Array<DeviceType>();
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
}

