import { DeviceInventory } from './device-inventory';

export class TwoNetData {
   twoNetDataId: string;
   vHubId: string;
   authCode: string;
   deactivationDate: string;
   deviceInventory: DeviceInventory;
   associationStatus: string;
   activationStatus: string;
   createdDate: Date;
   lastModifiedDate: Date;
   createdBy: string;
   lastModifiedBy: string;
   activatedDate: Date;
}
