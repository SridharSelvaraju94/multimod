import { DeviceRuleCriteria } from './device-rule-criteria';
import { RuleNotification } from './rule-notification';
import { DeviceType } from './device-type';
import { Service } from './service';


export class UserBaseRule {
    userBaseRuleId: string;
    baseRuleName: string;
    baseRuleDescription: string;
    thresholdType: string;
    uniqueRuleId: string;
    criticality: string;
    serviceId: string;
    deviceTypeId: string;
    measurementType: string;
    uom: string;
    createdDate: Date;
    lastModifiedDate: Date;
    createdBy: string;
    lastModifiedBy: string;
    deviceRuleCriteria = new DeviceRuleCriteria();
    ruleNotification = new RuleNotification();
    baseRuleDeviceType = new DeviceType();
    baseRuleService = new Service();
    status : string;

}
