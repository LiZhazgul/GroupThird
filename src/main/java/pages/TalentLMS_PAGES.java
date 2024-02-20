package pages;

public enum TalentLMS_PAGES {
    TALENTLMS("https://www.talentlms.com/"),
    LOGIN("https://app.talentlms.com/login"),
    DASHBOARD("https://fall2023.talentlms.com/dashboard"),
    USERS("https://fall2023.talentlms.com/user/index"),
    BASIC_SETTINGS("https://fall2023.talentlms.com/account/basic_index"),
    ACCOUNT_SETTINGS_USERS("https://fall2023.talentlms.com/account/users_index"),
    THEMES("https://fall2023.talentlms.com/account/themes_index"),
    CERTIFICATES("https://fall2023.talentlms.com/account/certifications_index"),
    ECOMMERCE("https://fall2023.talentlms.com/account/ecommerce_index"),
    DOMAIN("https://fall2023.talentlms.com/account/domain_index"),
    GAMIFICATION("https://fall2023.talentlms.com/account/gamification_index"),
    SUBSCRIPTION("https://fall2023.talentlms.com/account/subscription_index"),
    STARTER_PLAN("https://fall2023.talentlms.com/account/subscription_selectplan/plan:starter-annual-c2022,mode:upgrade"),
    BASIC_PLAN("https://fall2023.talentlms.com/account/subscription_selectplan/plan:basic-annual-c2022,mode:upgrade"),
    PLUS_PLAN("https://fall2023.talentlms.com/account/subscription_selectplan/plan:plus-annual-c2022,mode:upgrade"),
    PREMIUM_PLAN("https://fall2023.talentlms.com/account/subscription_selectplan/plan:premium-annual-c2022,mode:upgrade"),

    USER_TYPES_PAGE("https://fall2023.talentlms.com/acl/index"),
    ADD_USER_TYPE_PAGE("https://fall2023.talentlms.com/acl/index");

    public final String URL;

    TalentLMS_PAGES(String URL){
        this.URL = URL;
    }

    @Override
    public String toString() {
        return URL;
    }
}
