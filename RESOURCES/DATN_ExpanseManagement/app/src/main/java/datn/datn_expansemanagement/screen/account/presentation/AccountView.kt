package datn.datn_expansemanagement.screen.account.presentation

import android.content.Context
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.screen.account.item_account.ItemAccountFragment
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import kotlinx.android.synthetic.main.layout_account.view.*
import kotlinx.android.synthetic.main.toolbar_account.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AccountView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), AccountContract.View {
    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_account, context, viewGroup)

    private val mPresenter = AccountPresenter(AndroidScreenNavigator(mvpActivity))
    private val adapter = ViewPagerAdapter(mvpActivity.supportFragmentManager)

    companion object{
        val listTab = mutableListOf<ViewModel>()
    }
    override fun initCreateView() {
        view.imgAdd.setOnClickListener {
            mPresenter.gotoAddWalletActivity(view.tlAccount.selectedTabPosition)
        }
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
        listTab.clear()
        if(list.isNotEmpty()){
            listTab.addAll(list)
        }

        initTabLayout()
    }

    private fun initTabLayout() {
        view.vpAccount.offscreenPageLimit = 3
        view.vpAccount.adapter = adapter
        view.tlAccount.setupWithViewPager(view.vpAccount)
        listTab.forEachIndexed { index, viewModel ->
            if(viewModel is TabItemViewModel) {
                view.tlAccount.getTabAt(index)?.text = viewModel.name
            }
        }
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ItemAccountFragment.newInstance(listTab[position])
        }

        override fun getCount(): Int {
            return listTab.size
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
    }

}