package datn.datn_expansemanagement.screen.login.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.login.data.FinishLoginData
import datn.datn_expansemanagement.screen.login.data.NextStepData
import datn.datn_expansemanagement.screen.login.item_create_wallet.ItemCreateWalletFragment
import datn.datn_expansemanagement.screen.login.item_login.ItemLoginFragment
import datn.datn_expansemanagement.screen.login.item_register.ItemRegisterFragment
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent

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
            when(data){
                is FinishLoginData->{
                    mPresenter.gotoMainActivity()
                }

                is NextStepData->{
                    val userData = ConfigUtil.passport
                    var dataIntent : PassportDataIntent? = null
                    if(userData != null){
                        dataIntent = PassportDataIntent(id = userData.data.userId.getValueOrDefaultIsZero(),
                            name = userData.data.fullName.getValueOrDefaultIsEmpty(),
                            phone = userData.data.userName.getValueOrDefaultIsEmpty()
                        )
                    }
                    if(dataIntent != null){
                        replaceFragment(ItemCreateWalletFragment.newInstance(dataIntent as ViewModel))
                    }

                }
            }
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        mvpActivity.setFullScreen()
        if (isLogin == true) {
            if(user != null){
                replaceFragment(ItemCreateWalletFragment.newInstance(user as ViewModel))
            }else{
                replaceFragment(ItemLoginFragment())
            }
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