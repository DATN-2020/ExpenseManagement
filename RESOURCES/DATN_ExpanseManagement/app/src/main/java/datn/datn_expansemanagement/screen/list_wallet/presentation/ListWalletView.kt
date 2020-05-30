package datn.datn_expansemanagement.screen.list_wallet.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.list_wallet.presentation.model.ListWalletItemViewModel
import datn.datn_expansemanagement.screen.list_wallet.presentation.renderer.ListWalletItemViewRenderer
import kotlinx.android.synthetic.main.layout_list_wallet.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ListWalletView(
    mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
    private val walletId: Int? = null
) : AndroidMvpView(mvpActivity, viewCreator), ListWalletContract.View {

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_list_wallet, context, viewGroup)


    private val mResource = ListWalletResource()
    private val mPresenter = ListWalletPresenter()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    private val onClickItem = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ListWalletItemViewModel
            val model = AddExpenseViewModel.Info.Wallet(
                id = dataItem.id.getValueOrDefaultIsZero(),
                name = dataItem.name.getValueOrDefaultIsEmpty()
            )
            AddExpenseFragment.model.wallet = model
            mvpActivity.setResult(Activity.RESULT_OK)
            mvpActivity.finish()
        }
    }

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        initRecycleView()
        initView()
    }

    private fun initView() {
        view.tvToolbar.text = mResource.getTextTitleScreen()
        view.imgBack.setOnClickListener {
            listData.forEach {
                if(it is ListWalletItemViewModel){
                    if(it.isChoose){
                        val intent = Intent()
                        intent.putExtra(ListWalletItemViewModel::class.java.simpleName, it)
                        mvpActivity.setResult(Activity.RESULT_OK, intent)
                    }
                }
            }
            mvpActivity.finish()
        }
        view.imgAdd.setOnClickListener {
            mPresenter.gotoCreateWalletActivity()
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
        if (walletId == null){
            mPresenter.getData()
        }else{
            mPresenter.getData(walletId)
        }

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

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvListWallet, renderConfig)
        listViewMvp?.addViewRenderer(ListWalletItemViewRenderer(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(onClickItem)
        listViewMvp?.createView()
    }

}