import { DeviceTypes } from './device-types';
import { DeviceInventory } from './device-inventory';

export class UserDeviceInfo {
    deviceTypes = new DeviceTypes();
    deviceInv = new Array<DeviceInventory>();
}
