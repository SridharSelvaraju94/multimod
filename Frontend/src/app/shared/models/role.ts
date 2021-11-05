import { Permission } from './permission';

export class Role {
    roleId: string;
    roleName: string;
    permissions: Array<Permission> = new Array<Permission>();
}
