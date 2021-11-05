import { User } from './user';
import { DeviceInventory } from './device-inventory';

export class UserDirectory {
    userDetails = new User();
    totalDeviceCount: string;
    devices: Array<DeviceInventory> = new Array<DeviceInventory>();
    totalAlertCount: string;
    overallDeviceStatus: string;
}
