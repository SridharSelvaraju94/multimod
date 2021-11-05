import { DeviceMproConfig } from './device-mpro-config';
import { GatewayMproConfig } from './gateway-mpro-config';
import { DeviceTypeMetadata } from './device-type-metadata';
import { DeviceType } from './device-type';
import { TwoNetData } from './two-net-data';

export class DeviceInventory {
    deviceInventoryId: string;
    deviceInventoryName: string;
    serialNumber: string;
    macId: string;
    serviceId: string;
    entityId: string;
    inventoryStatus: string;
    publicInv: Boolean;
    purchaseDate: any;
    updateComments: string;
    gatewayMproConfig: Array<GatewayMproConfig> = new Array<GatewayMproConfig>();
    deviceMproConfig: Array<DeviceMproConfig> = new Array<DeviceMproConfig>();
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
    deviceType = new DeviceType();
    deviceTypeMetadata = new DeviceTypeMetadata();
    twoNetData: TwoNetData;
}
