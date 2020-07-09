package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.ItemTabBudgetFragment
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel
import datn.datn_expansemanagement.screen.report.presentation.renderer.GetWalletItemViewRenderer
import kotlinx.android.synthetic.main.custom_bottomsheet_recycleview.*
import kotlinx.android.synthetic.main.layout_plan_detail_item.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.imgAdd
import kotlinx.android.synthetic.main.toolbar_category.view.imgBack
import kotlinx.android.synthetic.main.toolbar_category.view.tvToolbar
import kotlinx.android.synthetic.main.toolbar_plan_detail.view.*
import kotlinx.android.synthetic.main.toolbar_plan_detail.view.imgChooseWallet
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class BudgetView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val type: PlanItemViewModel?
) : AndroidMvpView(mvpActivity, viewCreator), BudgetContract.View {
    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_plan_detail_item, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = BudgetPresenter(AndroidScreenNavigator(mvpActivity))
    private val adapter = ViewPagerAdapter(mvpActivity.supportFragmentManager)

    private val listBottom = mutableListOf<ViewModel>()
    private var listViewBottom: ListViewMvp? = null

    private val renderBottom = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfigBottom = LinearRenderConfigFactory(renderBottom).create()
    private val customView = LayoutInflater.from(mvpActivity)
        .inflate(R.layout.custom_bottomsheet_recycleview, null, false)
    private val bottomSheet = BottomSheetDialog(mvpActivity)

    companion object {
        var listTab = mutableListOf<ViewModel>()
    }

    private val onItemClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as GetWalletItemViewModel

            listBottom.forEach {
                if (it is GetWalletItemViewModel) {
                    it.isChoose = false
                }
            }
            dataItem.isChoose = true

            view.tvWalletName.text = dataItem.name
            view.tvPriceWallet.text = Utils.formatMoney(dataItem.money)
            // gọi api đổ lại list report
            listViewBottom?.notifyDataChanged()
            bottomSheet.dismiss()
        }

    }

    private fun initRecycleView() {
        bottomSheet.setContentView(customView)
        bottomSheet.create()
        listViewBottom = ListViewMvp(mvpActivity, bottomSheet.rvChoose, renderConfigBottom)
        listViewBottom?.addViewRenderer(GetWalletItemViewRenderer(mvpActivity))
        listViewBottom?.setOnItemRvClickedListener(onItemClick)
        listViewBottom?.createView()
    }

    override fun initCreateView() {
        when(type?.type){
            PlanItemViewModel.Type.BUDGET->{
                view.tvToolbar.text = "Ngân sách"
            }
            PlanItemViewModel.Type.TRANSACTION->{
                view.tvToolbar.text = "Định kỳ"
            }
            else->{
                view.tvToolbar.text = "Hoá đơn"
            }
        }

        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgAdd.setOnClickListener {
            type?.let { mPresenter.gotoAddPlanActivity(type) }
        }

        view.imgChooseWallet.setOnClickListener {
            bottomSheet.show()
        }

        initRecycleView()
    }

    override fun handleAfterGetWallet(list: MutableList<ViewModel>) {
        this.listBottom.clear()
        if (list.isNotEmpty()) {
            this.listBottom.addAll(list)
        }

        listViewBottom?.setItems(this.listBottom)
        listViewBottom?.notifyDataChanged()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        type?.let { mPresenter.getData(it) }
        //        mPresenter.getWalletForUser(null)
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
        if (list.isNotEmpty()) {
            listTab.addAll(list)
        }

        initTabLayout()
    }

    private fun initTabLayout() {
        view.vpControlDetail.offscreenPageLimit = 3
        view.vpControlDetail.adapter = adapter
        view.tlControlDetail.setupWithViewPager(view.vpControlDetail)
        listTab.forEachIndexed { index, viewModel ->
            if (viewModel is TabItemViewModel) {
                view.tlControlDetail.getTabAt(index)?.text = viewModel.name
            }
        }
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ItemTabBudgetFragment.newInstance(listTab[position])
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