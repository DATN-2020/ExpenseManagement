package datn.datn_expansemanagement.screen.login.item_register.presentation

import android.content.Context
import android.view.View
import android.view.ViewGroup
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.ValidateItemViewModel
import datn.datn_expansemanagement.screen.login.presentation.LoginResource
import kotlinx.android.synthetic.main.item_layout_register.view.*

class ItemRegisterView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ItemRegisterContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_register, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemRegisterPresenter()
    private val mResource = LoginResource()

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
    }

    private fun loginFacebook(){

    }

    override fun initCreateView() {
        view.btnRegister.setOnClickListener(onActionClick)
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
        var warningValue: String? = mResource.getTextErrorEmpty()
        model.showWarning = isWarning
        model.warning = warningValue
        return isWarning
    }

    private fun validationPhone(model: ValidateItemViewModel): Boolean {
        var isWarning = false
        var warningValue: String? = null
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