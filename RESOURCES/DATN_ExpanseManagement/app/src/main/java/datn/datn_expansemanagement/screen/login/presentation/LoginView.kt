package datn.datn_expansemanagement.screen.login.presentation

import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.squareup.picasso.Picasso
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.domain.response.RegisterResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.login.data.FinishLoginData
import datn.datn_expansemanagement.screen.login.data.FinishRegisterData
import datn.datn_expansemanagement.screen.login.data.NextStepData
import datn.datn_expansemanagement.screen.login.data.OnLoginFacebook
import datn.datn_expansemanagement.screen.login.item_create_wallet.ItemCreateWalletFragment
import datn.datn_expansemanagement.screen.login.item_login.ItemLoginFragment
import datn.datn_expansemanagement.screen.login.item_register.ItemRegisterFragment
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent
import kotlinx.android.synthetic.main.item_layout_login.view.*

class LoginView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isLogin: Boolean? = true,
    private val user: PassportDataIntent? = null
) : AndroidMvpView(mvpActivity, viewCreator), LoginContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_login, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = LoginPresenter(AndroidScreenNavigator(mvpActivity))

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            when (data) {
                is FinishLoginData -> {
                    mPresenter.gotoMainActivity()
                }

                is FinishRegisterData -> {
                    replaceFragment(ItemLoginFragment(data.user))
                }

                is NextStepData -> {
                    val userData = ConfigUtil.passport
                    var dataIntent: PassportDataIntent? = null
                    if (userData != null) {
                        dataIntent = PassportDataIntent(
                            id = userData.data.userId.getValueOrDefaultIsZero(),
                            name = userData.data.fullName.getValueOrDefaultIsEmpty(),
                            phone = userData.data.userName.getValueOrDefaultIsEmpty()
                        )
                    }
                    if (dataIntent != null) {
                        replaceFragment(ItemCreateWalletFragment.newInstance(dataIntent as ViewModel))
                    }

                }

                is OnLoginFacebook -> {
                    FacebookSdk.sdkInitialize(FacebookSdk.getApplicationContext())
                    AppEventsLogger.activateApp(mvpActivity)
                    view.btnLoginFacebook.setReadPermissions(listOf("email", "public_profile"))
                    view.btnLoginFacebook.registerCallback(
                        callBackManager,
                        object : FacebookCallback<LoginResult> {
                            override fun onSuccess(result: LoginResult?) {
                                Toast.makeText(mvpActivity, "OK", Toast.LENGTH_LONG).show() // vào sau
//                                if (!loggedOut) {
//                                    Picasso.with(this@MainActivity).load(
//                                        Profile.getCurrentProfile()
//                                            .getProfilePictureUri(200, 200)
//                                    ).into(imageView)
//                                    Log.d(
//                                        "TAG",
//                                        "Username is: " + Profile.getCurrentProfile()
//                                            .name
//                                    )
//
//                                    //Using Graph API
//                                    getUserProfile(AccessToken.getCurrentAccessToken())
//                                }
                            }

                            override fun onCancel() {
                                Toast.makeText(mvpActivity, "cancel", Toast.LENGTH_LONG).show()
                            }

                            override fun onError(error: FacebookException?) {
                                Toast.makeText(mvpActivity, "fail", Toast.LENGTH_LONG).show()
                            }

                        })

                }
            }
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        mvpActivity.setFullScreen()
        if (isLogin == true) {
            if (user != null) {
                replaceFragment(ItemCreateWalletFragment.newInstance(user as ViewModel))
            } else {
                replaceFragment(ItemLoginFragment())
            }
        } else {
            replaceFragment(ItemRegisterFragment())
        }
    }

    private val callBackManager = CallbackManager.Factory.create()
    override fun onViewResult(viewResult: ViewResult) {
        callBackManager.onActivityResult(
            viewResult.requestCode,
            viewResult.resultCode,
            viewResult.data
        )
        mPresenter.gotoMainActivity()
        super.onViewResult(viewResult)

    }

    override fun handleAfterRegister(user: RegisterResponse) {

        val data = PassportResponse.Data(
            userId = user.userId.getValueOrDefaultIsZero(),
            userName = user.userName.getValueOrDefaultIsEmpty(),
            fullName = user.fullName.getValueOrDefaultIsEmpty(),
            password = user.password.getValueOrDefaultIsEmpty(),
            checkWallet = user.checkWallet.getValueOrDefault()
        )
        val passportResponse = PassportResponse(
            data = data
        )
        val pp = ConfigUtil.passport
        if (pp != null) {
            ConfigUtil.savePassport(null)
            ConfigUtil.savePassport(passportResponse)
        } else {
            ConfigUtil.savePassport(passportResponse)
        }

    }

    override fun handleRegisterFail(ms: String) {

    }

    private fun replaceFragment(frm: Fragment) {
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flChange, frm)
            .commit()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}