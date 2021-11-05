import { UserAdditionalInfo } from './user-additional-info';
import { UserAddress } from './user-address';
import { UserDeviceToken } from './user-device-token';
import { UserLicense } from './user-license';
import { Enterprise } from './enterprise';
import { Service } from './service';

export class User {
    userId: String;
    userTitle: String;
    firstName: String;
    lastName: String;
    middleName: String;
    userName: String;
    phone: String;
    gender: String;
    profilePic: String;
    emailId: String;
    enterpriseId: String;
    enterprise = new Enterprise();
    userLevelId: String;
    userRoleId: String;
    userRoleName: String;
    roleDesignation: String;
    status: String;
    userPhoto: String;
    photoPath: String;
    userActivationStatus: String;
    userService: Array<Service> = new Array<Service>();
    userAddress: Array<UserAddress> = new Array<UserAddress>();
    userAdditonalInfo = new UserAdditionalInfo();
    userDeviceToken: Array<UserDeviceToken> = new Array<UserDeviceToken>();
    userLicense = new UserLicense();
    createdBy: String;
    createdDate: Date;
    lastModifiedBy: String;
    lastModifiedDate: Date;
    userPermission: string;
}

