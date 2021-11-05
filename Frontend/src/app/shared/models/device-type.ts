import { DeviceTypeProperty } from './device-type-property';
import { DeviceTypes } from './device-types';
import { DeviceTypeMetadata } from './device-type-metadata';

export class DeviceType {
    deviceTypeId: string;
    deviceTypes = new DeviceTypes();
    deviceTypeStatus: string;
    deviceTypeProperties: Array<DeviceTypeProperty> = new Array<DeviceTypeProperty>();
    updateComments: string;
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
    deviceTypeMetadata: Array<DeviceTypeMetadata> = new Array<DeviceTypeMetadata>();
}
