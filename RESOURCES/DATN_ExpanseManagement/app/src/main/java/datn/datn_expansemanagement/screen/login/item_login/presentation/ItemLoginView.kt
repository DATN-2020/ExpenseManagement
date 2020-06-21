package datn.datn_expansemanagement.screen.login.item_login.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.getApplicationContext
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.ValidateItemViewModel
import datn.datn_expansemanagement.screen.login.data.NextStepData
import datn.datn_expansemanagement.screen.login.presentation.LoginResource
import kotlinx.android.synthetic.main.item_layout_login.view.*
import java.util.*

class ItemLoginView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), ItemLoginContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_login, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemLoginPresenter()
    private val mResource = LoginResource()

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    private val onActionClick = View.OnClickListener {
        when(it.id){
            view.btnLogin.id->{
                checkLogin()
            }
            view.btnLoginFacebook.id->{
                loginFacebook()
            }
        }
    }

    private val callBackManager = CallbackManager.Factory.create()

    private fun loginFacebook(){
        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(mvpActivity)
//        view.btnLoginFacebook.setFragment(this)
        view.btnLoginFacebook.setReadPermissions(listOf("email", "public_profile"))
        view.btnLoginFacebook.registerCallback( callBackManager , object : FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(mvpActivity, "OK", Toast.LENGTH_LONG).show()
            }

            override fun onCancel() {
                Toast.makeText(mvpActivity, "cancel", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(mvpActivity, "fail", Toast.LENGTH_LONG).show()
            }

        })

    }

    override fun onViewResult(viewResult: ViewResult) {
        callBackManager.onActivityResult(viewResult.requestCode, viewResult.resultCode, viewResult.data)
        super.onViewResult(viewResult)
    }

    private fun checkLogin(){
        var isSuccess = true
        val dataCheck = ValidateItemViewModel(value = view.edtUser.text.toString())
        if(validationPhone(dataCheck)){
            isSuccess = false
            view.tvErrorUser.visible()
            view.tvErrorUser.text = dataCheck.warning
            view.edtUser.background = mResource.getEditError()
        }else{
            view.edtUser.background = mResource.getEditDefault()
            view.tvErrorUser.gone()
        }

        val dataPass = ValidateItemViewModel(value = view.edtPassword.text.toString())
        if(validationName(dataPass)){
            isSuccess = false
            view.tvErrorPassword.visible()
            view.tvErrorPassword.text = dataPass.warning
            view.edtPassword.background = mResource.getEditError()
        }else{
            view.edtPassword.background = mResource.getEditDefault()
            view.tvErrorPassword.gone()
        }

        if(isSuccess){
            eventBusLifeCycle.sendData(NextStepData())
        }
    }

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        view.btnLogin.setOnClickListener(onActionClick)
        view.btnLoginFacebook.setOnClickListener(onActionClick)
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

    private fun validationName(model: ValidateItemViewModel): Boolean {
        val isWarning = model.value.isNullOrEmpty()
        val warningValue: String? = mResource.getTextErrorEmpty()
        model.showWarning = isWarning
        model.warning = warningValue
        return isWarning
    }

    private fun validationPhone(model: ValidateItemViewModel): Boolean {
        var isWarning = false
        var warningValue: String? = null
        if(model.value.isNullOrEmpty()){
            isWarning = true
            warningValue = mResource.getTextErrorEmpty()
        }
        if (model.value.getValueOrDefaultIsEmpty()
                .isNotEmpty() && !Utils.checkValidPhoneNumber(
                model.value.getValueOrDefaultIsEmpty().trim().replace(" ", "")
            )
        ) {
            isWarning = true
            warningValue = mResource.getWarningPhone()
        }
        model.showWarning = isWarning
        model.warning = warningValue
        return isWarning
    }
}