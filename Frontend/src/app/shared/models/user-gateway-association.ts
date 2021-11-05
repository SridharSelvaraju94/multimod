import { DeviceInventory } from './device-inventory';
import { DeviceConfigDetails } from './device-config-details';
import { UserGatewayDeviceAssociation } from './user-gateway-device-association';

export class UserGatewayAssociation {
   userGatewayId: String;
   gateway = new DeviceInventory();
   ssmUserGatewayDeviceAssociation = new Array<UserGatewayDeviceAssociation>();
}
