package datn.datn_expansemanagement.core.app.config

import datn.datn_expansemanagement.core.app.config.config_paper.ConfigSaver
import datn.datn_expansemanagement.core.app.config.config_paper.PaperConfigSaverImpl
import datn.datn_expansemanagement.domain.request.PassportRequest
import datn.datn_expansemanagement.domain.response.PassportResponse

class ConfigUtil {
    companion object {
        @JvmStatic
        val passport: PassportResponse?
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                return configSaver.get(ConfigSaver.CONFIG_SETTING_PASSPORT)
            }

        val passportRequest: PassportRequest?
            get() {
                val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
                return configSaver.get(ConfigSaver.CONFIG_SETTING_KEY_LOGIN)
            }

        @JvmStatic
        fun savePassport(passportResponse: PassportResponse?) {
            val configSaver = PaperConfigSaverImpl(ConfigSaver.CONFIG_PAGER)
            configSaver.save(ConfigSaver.CONFIG_SETTING_PASSPORT, passportResponse)
        }
    }
}