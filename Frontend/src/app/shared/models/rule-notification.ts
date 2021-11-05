export class RuleNotification {
    ruleNotificationId: string;
    email: Boolean;
    push: Boolean;
    sms: Boolean;
    call: Boolean;
    emailTemplate: string;
    pushTemplate: string;
    smsTemplate: string;
    callTemplate: string;
    emailSubject: string;
}
