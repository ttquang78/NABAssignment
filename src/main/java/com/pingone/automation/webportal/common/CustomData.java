package com.pingone.automation.webportal.common;

public class CustomData {
    public enum EnvironmentName {
        TEST("test-"), ORT("ort-"), PROD("");

        private String environment;

        EnvironmentName(String name) {
            this.environment = name;
        }

        public String getEnvironment() {
            return this.environment;
        }
    }

    public enum OS {
        WINDOWS("test-"), MAC("ort-");

        private String os;

        OS(String name) {
            this.os = name;
        }

        public String getOs() {
            return this.os;
        }
    }

    public enum BrowserName {
        CHROME("chrome"), FIREFOX("firefox"), IE("Internet Explorer"), SAFARI("safari"), MSEDGE("microsoftedge");

        private String browser;

        BrowserName(String name) {
            this.browser = name;
        }

        public String getBrowser() {
            return this.browser;
        }
    }

    public enum AccountType {
        P14E("p14e"), MSP("msp"), APS("aps");

        private String accountType;

        AccountType(String name) {
            this.accountType = name;
        }

        public String getAccoungType() {
            return this.accountType;
        }
    }

    public enum AccountRole {
        GLOBAL_ADMIN("GlobalAdmin"), IDR_ADMIN("IDRAdmin");

        private String accountRole;

        AccountRole(String name) {
            this.accountRole = name;
        }

        public String getAccountRole() {
            return this.accountRole;
        }
    }

    public enum RegistrationAccountType {
        DEFAULT("Select One"), P14E("PingOne For Enterprise"), APS("PingOne SSO for SaaS Apps");

        private String accountType;

        RegistrationAccountType(String name) {
            this.accountType = name;
        }

        public String getAccountType() {
            return this.accountType;
        }
    }

    public enum DataCenterRegion {
        NORTH_AMERICA("North America"), EUROPE("Europe"), AUSTRALIA("Australia");

        private String region;

        DataCenterRegion(String regionName) {
            this.region = regionName;
        }

        public String getRegion() {
            return this.region;
        }
    }

    public enum RegistrationButtons {
        SaveAccountType,
        SaveProfile,
        SavePassword,
    }

    public enum Messages {
        ERROR_MSG_EMAIL(" is not a valid email address."),
        ERROR_MSG_REGISTRATIONKEY(" is not a valid registration key."),
        ERROR_MSG_DUPLICATEDEMAIL_BEFORE("The email '"),
        ERROR_MSG_DUPLICATEDEMAIL_AFTER("' is already in use. Please select a unique email address."),
        ERROR_MSG_PASSWORD_NOTMATCH("New passwords don't match. Please try again."),
        ERROR_MSG_PASSWORD_POLICY("Passwords must be at least 8 character(s) long, " +
                "and include at least 1 upper case letter(s), 0 special letter(s), " +
                "and 1 digit(s)."),
        ERROR_MSG_DISABLED_USER_LOGIN_DOCK("Sorry, your account is awaiting approval and can't be accessed yet. " +
                "If you're not notified soon, please contact your administrator."),
        INFO_MSG_CANCELLED_REGISTRATION("Registration cancelled"),
        WARNING_MSG_INVITATION_SUCCESS("Invitation has been sent"),
        WARNING_MSG_CREATE_USER_SUCCESS("User has been saved."),
        WARNING_MSG_DELETE_USER_SUCCESS("Deleted user "),
        WARNING_MSG_DELETE_GROUP_SUCCESS_BEFORE("Deleted "),
        WARNING_MSG_DELETE_GROUP_SUCCESS_AFTER(" group"),
        WARNING_MSG_DISABLE_SUCCESS("Disabled user "),
        WARNING_MSG_ENABLE_SUCCESS("Enabled user "),
        WARNING_MSG_CREATE_GROUP_SUCCESS(" has been saved");

        private String message;

        Messages(String msg) {
            this.message = msg;
        }

        public String getMessage() {
            return this.message;
        }
    }

    public enum UserTypes{
        ALL_USERS("All Users"),
        ENABLED_USERS("Enabled"),
        DISABLED_USERS("Disabled"),
        INVITED_USERS("Invited"),
        LOCKED_USERS("Locked"),
        PENDING_APPROVAL_USERS("Pending Approval");

        private String userType;

        UserTypes(String userType) {
            this.userType = userType;
        }

        public String getUserType(){
            return this.userType;
        }
    }

    public enum UsersCol {
        USER(1), USERNAME(2), STATUS(3), ACTION(4);
        private int colIndex;

        UsersCol(int col) {
            this.colIndex = col;
        }

        public int getColIndex() {
            return this.colIndex;
        }
    }

    public enum GroupsCol {
        GROUP(1), MEMBERS(2), ACTION(3);
        private int colIndex;

        GroupsCol(int col) {
            this.colIndex = col;
        }

        public int getColIndex() {
            return this.colIndex;
        }
    }

    public enum UsersGridActionName {
        DETAILS("Details"), DISABLE("Disable"), ENABLE("Enable"), DELETE("Delete"),
        RESEND_INVITATION("Resend Invitation"),
        RESEND_INV_TO_ALT_EMAIL("Resend invitation to alternate email address");

        private String actionName;

        UsersGridActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public enum UsersDetailsActionName {
        DISABLE("Disable"), ENABLE("Enable"), DELETE("Delete");

        private String actionName;

        UsersDetailsActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public enum GroupsGridActionName {
        DETAILS("Details"), DELETE("Delete");

        private String actionName;

        GroupsGridActionName(String actionName) {
            this.actionName = actionName;
        }

        public String getActionName() {
            return this.actionName;
        }
    }

    public enum GroupDirectoryPermissions {
        NO_ACCESS("No Access"),
        USER_VIEWER("User Reader"),
        USER_MANAGER("User Manager"),
        POLICY_EDITOR("Group and Entitlement Manager");

        private String permission;

        GroupDirectoryPermissions(String permission) { this.permission = permission; }

        public String getPermission() {return  this.permission; }
    }

    public enum AdministratorsRole {
        APPLICATION_ADMIN("Application Administrator"),
        GLOBAL_ADMIN("Global Administrator"),
        AUDIT_REPORT_ADMIN("Audit & Report Administrator"),
        IDENTITY_REPO_ADMIN("Identity Repository Administrator"),
        SAAS_ADMIN("SaaS Administrator"),
        SERVICE_ADMIN("Service User Administrator");

        private String role;

        AdministratorsRole(String role) {
            this.role = role;
        }

        public String getRole() {
            return this.role;
        }
    }

    public enum AdminStatus {
        INVITED,
        ENABLED,
        DISABLED;
    }

    public enum AdministratorsPermission {
        FULL_CONTROL("Full Control"),
        EDIT("Edit"),
        POLICY("Policy"),
        REPORT("Report"),
        VIEW("View");

        private String permission;

        AdministratorsPermission(String permission) {
            this.permission = permission;
        }

        public String getPermission() {
            return this.permission;
        }
    }
}
