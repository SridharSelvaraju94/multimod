import { UserGatewayAssociation } from './user-gateway-association';
import { User } from './user';
import { UserGroup } from './user-group';
import { Service } from './service';

export class UserAssociation {
   userAssciationId: String;
   service = new Service();
   user = new User();
   userGroup = new UserGroup();
   createdDate: Date;
   lastModifiedDate: Date;
   createdBy: String;
   lastModifiedBy: String;
   ssmUserGatewayAssociation = new Array<UserGatewayAssociation>();
}
