package datn.datn_expansemanagement.screen.category.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import datn.datn_expansemanagement.screen.account.item_account.ItemAccountFragment
import datn.datn_expansemanagement.screen.account.presentation.AccountView
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.ItemCategoryFragment
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel
import kotlinex.view.hideKeyboard
import kotlinx.android.synthetic.main.layout_category.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*

class CategoryView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val itemId: Int? = null
) : AndroidMvpView(mvpActivity, viewCreator), CategoryContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_category, context, viewGroup)

    private val adapter = ViewPagerAdapter(mvpActivity.supportFragmentManager)

    private val mResource = CategoryResource()
    private val mPresenter =
        CategoryPresenter(mResource, screenNavigator = AndroidScreenNavigator((mvpActivity)))

    companion object {
        val listTab = mutableListOf<ViewModel>()
        var categoryId : Int? = null
    }

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        view.imgAdd.setOnClickListener {
            mPresenter.gotoAddCategoryActivity()
        }
        categoryId = itemId
        view.tvToolbar.text = mResource.getTitleCategory()
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

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        listTab.clear()
        if (list.isNotEmpty()) {
            listTab.addAll(list)
        }

        initTabLayout()
    }

    private fun initTabLayout() {
        view.vpCategory.offscreenPageLimit = 3
        view.vpCategory.adapter = adapter
        view.tlCategory.setupWithViewPager(view.vpCategory)
        listTab.forEachIndexed { index, viewModel ->
            if (viewModel is TabItemViewModel) {
                view.tlCategory.getTabAt(index)?.text = viewModel.name
            }
        }
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ItemCategoryFragment.newInstance(listTab[position], categoryId)
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