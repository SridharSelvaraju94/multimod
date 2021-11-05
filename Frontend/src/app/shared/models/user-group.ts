import { User } from './user';
import { Service } from './service';

export class UserGroup {
    userGroupId: String;
    userGroupName: String;
    serviceId: String;
    serviceName: String;
    userGroupStatus: String;
    userGroupMap: Array<User> = new Array<User>();
    userGroupService: Array<Service> = new Array<Service>();
    createdBy: String;
    createdDate: String;
    lastModifiedBy: String;
    lastModifiedDate: String;
}
