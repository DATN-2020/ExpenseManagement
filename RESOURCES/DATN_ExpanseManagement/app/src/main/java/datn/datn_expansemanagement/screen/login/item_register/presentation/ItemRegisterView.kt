package datn.datn_expansemanagement.screen.login.item_register.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.domain.request.RegisterRequest
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.ValidateItemViewModel
import datn.datn_expansemanagement.screen.login.data.FinishRegisterData
import datn.datn_expansemanagement.screen.login.data.NextStepData
import datn.datn_expansemanagement.screen.login.presentation.LoginResource
import kotlinx.android.synthetic.main.item_layout_register.view.*

class ItemRegisterView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ItemRegisterContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_register, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemRegisterPresenter()
    private val mResource = LoginResource()


    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    private val onActionClick = View.OnClickListener {
        when (it.id) {
            view.btnRegister.id -> {
                checkRegister()
            }
            view.btnLoginFacebook.id->{
                loginFacebook()
            }
        }
    }

    private fun checkRegister(){
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

        val dataName = ValidateItemViewModel(value = view.edtName.text.toString())
        if(validationName(dataName)){
            isSuccess = false
            view.tvErrorName.visible()
            view.tvErrorName.text = dataName.warning
            view.edtName.background = mResource.getEditError()
        }else{
            view.edtName.background = mResource.getEditDefault()
            view.tvErrorName.gone()
        }

        if(isSuccess){
            val request = RegisterRequest(
                userName = view.edtUser.text.toString().getValueOrDefaultIsEmpty(),
                password = view.edtPassword.text.toString().getValueOrDefaultIsEmpty(),
                fullName = view.edtName.text.toString().getValueOrDefaultIsEmpty()
            )
            mPresenter.register(request)
        }
    }

    private fun loginFacebook(){

    }

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        view.btnRegister.setOnClickListener(onActionClick)
        view.btnLoginFacebook.setOnClickListener(onActionClick)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun handleAfterRegister(userName: String) {
        eventBusLifeCycle.sendData(FinishRegisterData(userName))
    }

    override fun handleRegisterFail(ms: String) {
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