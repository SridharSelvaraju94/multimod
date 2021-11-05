import { UserBaseRule } from './user-base-rule';
import { DeviceInventory } from './device-inventory';

export class UserRuleAssociation {
    userRuleAssociationId: String;
    userId: String;
    userGroupId: String;
    userBaseRules = new Array<UserBaseRule>();
    uniqueRuleId: String;
    deviceInventory = new DeviceInventory();
}
