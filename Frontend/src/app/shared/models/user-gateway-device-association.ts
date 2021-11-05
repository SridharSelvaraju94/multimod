import { DeviceConfigDetails } from './device-config-details';
import { DeviceInventory } from './device-inventory';

export class UserGatewayDeviceAssociation {
   userGatewayDeviceId: String;
   device = new DeviceInventory();
   ssmDeviceConfigDetails = new DeviceConfigDetails();
   status: String;
}
