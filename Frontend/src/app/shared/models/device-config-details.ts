import { SSMDeviceMproConfig } from './ssm-device-mpro-config';

export class DeviceConfigDetails {
   deviceConfigId: String;
   pollingInterval: String;
   threshold: String;
   latitude: String;
   longitude: String;
   altitude: String;
   wirelessProtocol: String;
   wirelessProtocolAddress: String;
   communicationProtocol: String;
   userAddressId: String;
   entLocationId: String;
   deviceUniqueId: String;
   deviceSubUniqueId: String;
   ssmDeviceMproConfig = new SSMDeviceMproConfig();
}
