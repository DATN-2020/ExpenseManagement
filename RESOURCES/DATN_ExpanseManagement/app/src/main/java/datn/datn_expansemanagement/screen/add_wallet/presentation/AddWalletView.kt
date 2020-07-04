package datn.datn_expansemanagement.screen.add_wallet.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_default.DefaultWalletFragment
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.SavingWalletFragment
import kotlinx.android.synthetic.main.toolbar_category.view.*

class AddWalletView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val type: Int? = 0
) :
    AndroidMvpView(mvpActivity, viewCreator), AddWalletContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = AddWalletPresenter()
    private val mResource = AddWalletResource()

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_wallet, context, viewGroup)

    override fun initCreateView() {
        when (type) {
            0 -> {
                view.tvToolbar.text = mResource.getTitleAddWalletDefault()
                replaceFragment(DefaultWalletFragment())
            }
            else -> {
                view.tvToolbar.text = mResource.getTitleAddWalletSaving()
                replaceFragment(SavingWalletFragment())
            }
        }

        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        view.imgAdd.gone()

        GlideImageHelper(mvpActivity).loadThumbnail(view.imgAdd, "", R.drawable.ic_check_black_24dp)
        view.imgAdd.setOnClickListener {

        }
        mvpActivity.setFullScreen()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
    }

    private fun replaceFragment(frm: Fragment) {
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(R.id.flChange, frm)
            .commit()
    }

}