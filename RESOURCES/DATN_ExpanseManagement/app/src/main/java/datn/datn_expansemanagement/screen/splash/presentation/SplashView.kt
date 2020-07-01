package datn.datn_expansemanagement.screen.splash.presentation

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent
import datn.datn_expansemanagement.screen.splash.item.ItemSplashFragment
import kotlinx.android.synthetic.main.layout_splash.view.*

class SplashView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), SplashContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_splash, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val listData = mutableListOf<ViewModel>()
    private val adapter = ViewPagerAdapter(mvpActivity.supportFragmentManager)
    private val mPresenter = SplashPresenter(AndroidScreenNavigator(mvpActivity))

    companion object {
        var listImage = mutableListOf<ViewModel>()
    }

    private val onActionClick = View.OnClickListener {
        when (it.id) {
            view.btnRegister.id -> {
                mPresenter.gotoLoginActivity(false)
            }
            view.tvLogin.id -> {
                mPresenter.gotoLoginActivity(true)
            }
        }
    }

    override fun initCreateView() {
        val data = ConfigUtil.passport
        if (data != null) {
            if(data.data.checkWallet){
                mPresenter.gotoMainActivity()
            }else{
                mPresenter.gotoLoginActivity(
                    true,
                    PassportDataIntent(
                        id = data.data.userId.getValueOrDefaultIsZero(),
                        phone = data.data.userName.getValueOrDefaultIsEmpty(),
                        name = data.data.fullName.getValueOrDefaultIsEmpty()
                    )
                )
            }

        }
        mvpActivity.setFullScreen()
        view.btnRegister.setOnClickListener(onActionClick)
        view.tvLogin.setOnClickListener(onActionClick)
    }

    private fun initTabLayout() {
        view.vpSplash.offscreenPageLimit = 3
        view.vpSplash.adapter = adapter
        view.tlSplash.setupWithViewPager(view.vpSplash)
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }
        listImage = this.listData
        initTabLayout()
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ItemSplashFragment.newInstance(listImage[position])
        }

        override fun getCount(): Int {
            return listImage.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            mFragmentList.put(position, fragment as Fragment)
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            super.destroyItem(container, position, any)
            mFragmentList.remove(position)
        }

        fun clearData() {
            mFragmentList.clear()
        }
    }

}