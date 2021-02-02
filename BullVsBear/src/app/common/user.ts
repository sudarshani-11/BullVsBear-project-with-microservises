export class user {
        firstName: string;
        lastName: string;
        userName: string;
        password: string;
        email:string;
        set mail(value:string)
        {
                this.email=value;
        }
}
export class token
{
   jwtToken:string;
}
export class usermail
{
        email:string;
}
export class useId{
        userId:string;
}