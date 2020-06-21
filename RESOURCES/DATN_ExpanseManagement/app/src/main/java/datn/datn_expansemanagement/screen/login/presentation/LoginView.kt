package datn.datn_expansemanagement.screen.login.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.screen.login.data.FinishLoginData
import datn.datn_expansemanagement.screen.login.data.NextStepData
import datn.datn_expansemanagement.screen.login.item_create_wallet.ItemCreateWalletFragment
import datn.datn_expansemanagement.screen.login.item_login.ItemLoginFragment
import datn.datn_expansemanagement.screen.login.item_register.ItemRegisterFragment

class LoginView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val isLogin: Boolean? = true
) : AndroidMvpView(mvpActivity, viewCreator), LoginContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_login, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = LoginPresenter(AndroidScreenNavigator(mvpActivity))

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
            when(data){
                is FinishLoginData->{
                    mPresenter.gotoMainActivity()
                }

                is NextStepData->{
                    replaceFragment(ItemCreateWalletFragment())
                }
            }
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        mvpActivity.setFullScreen()
        if (isLogin == true) {
            replaceFragment(ItemLoginFragment())
        }else{
            replaceFragment(ItemRegisterFragment())
        }
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