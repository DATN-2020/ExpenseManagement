package datn.datn_expansemanagement.core.app.config.config_paper;

public interface ConfigSaver {
    String CONFIG_PAGER = "config_pager";

    String CONFIG_SETTING_PASSPORT = "config_setting_saved_passport";
    String CONFIG_SETTING_KEY_LOGIN = "config_setting_saved_key_login";


    void save(String key, Object data);

    <T> T get(String key);

    void delete(String key);

    void deleteAll();
}
