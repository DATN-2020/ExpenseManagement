package datn.datn_expansemanagement.screen

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.onesignal.BuildConfig
import com.onesignal.OSInAppMessageAction
import com.onesignal.OneSignal
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import datn.datn_expansemanagement.core.app.common.AppConfigs
import io.paperdb.Paper

class App : Application(){

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        app = this
        Paper.init(this)
//        initOneSignal()
        setupApplication()
        initLogger()
        initLogActivity()
    }
    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
//    private val ACTION_ID_MY_CUSTOM_ID = "MY_CUSTOM_ID"
    private fun initOneSignal(){
        // OneSignal Initialization
        OneSignal.startInit(this)
            .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
            .unsubscribeWhenNotificationsAreDisabled(true)
            .init()
//        OneSignal.InAppMessageClickHandler {
//            Log.d("ABC", "Ok")
//            // Example of an action id you could setup on the dashboard when creating the In App Message
//            if(ACTION_ID_MY_CUSTOM_ID == it.clickName){
//                Log.d("ABC", "Oksss")
//            }
//        }
//        val status = OneSignal.getPermissionSubscriptionState()
//        Log.d("Thang",status.subscriptionStatus.userId)
    }

    private fun initLogActivity() {
        if (BuildConfig.DEBUG) {
            this.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityPaused(activity: Activity?) {
                    activity?.let {
                        Logger.d("onActivityPaused ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivityResumed(activity: Activity?) {
                    activity?.let {
                        Logger.d("onActivityResumed ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivityStarted(activity: Activity?) {
                    activity?.let {
                        Logger.d("onActivityStarted ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivityDestroyed(activity: Activity?) {
                    activity?.let {
                        Logger.d("onActivityDestroyed ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
                    activity?.let {
                        Logger.d("onActivitySaveInstanceState ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivityStopped(activity: Activity?) {
                    activity?.let {
                        Logger.d("onActivityStopped ${activity.javaClass.simpleName}")
                    }
                }

                override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                    activity?.let {
                        Logger.d("onActivityCreated ${activity.javaClass.simpleName}")
                    }
                }

            })
        }
    }

    private fun setupApplication() {
        AppConfigs.getInstanceApp().setBaseApplication(this)
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }

        @JvmStatic
        var app: App? = null
            private set
    }
}