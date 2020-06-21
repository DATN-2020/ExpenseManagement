package datn.datn_expansemanagement.core.app.config.config_paper;

public interface ConfigSaver {
    String CONFIG_PAGER = "config_pager";

    String CONFIG_SETTING_SAVED_IS_SHOW_DIALOG_WARNING_LOGGED_OTHER_DEVICE = "config_setting_saved_is_show_dialog_warning_logged_other";
    String CONFIG_SETTING_RES_ID = "config_setting_resId_update";
    String CONFIG_SETTING_SAVED_IS_FIRST_LOGIN_APP = "config_setting_saved_is_first_login_app";
    String CONFIG_SETTING_PROFILE = "config_setting_saved_profile";
    String CONFIG_SETTING_PASSPORT = "config_setting_saved_passport";
    String CONFIG_SETTING_KEY_LOGIN = "config_setting_saved_key_login";
    String CONFIG_SETTING_SAVED_AUGMENTED_IMAGE_DATABASE = "config_setting_saved_augmented_image_database";


    void save(String key, Object data);

    <T> T get(String key);

    void delete(String key);

    void deleteAll();
}
