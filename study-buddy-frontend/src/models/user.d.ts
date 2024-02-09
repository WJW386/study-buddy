/**
 * 用户
 */
export type UserType = {
    id: number;
    username: string;
    userAccount: string;
    avatarUrl?: string;
    userProfile?: string;
    gender:number;
    phone: string;
    email: string;
    userStatus: number;
    userRole: number;
    webId: string;
    tags: string;
    createTime: Date;
};
