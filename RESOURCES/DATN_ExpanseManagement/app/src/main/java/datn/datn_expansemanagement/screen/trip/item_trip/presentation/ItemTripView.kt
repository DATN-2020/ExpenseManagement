package datn.datn_expansemanagement.screen.trip.item_trip.presentation

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.model.ItemTripViewModel
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.renderer.ItemTripViewRenderer
import kotlinx.android.synthetic.main.custom_dialog_edit_trip.*
import kotlinx.android.synthetic.main.layout_item_trip.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ItemTripView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val isFinished: Boolean?): AndroidMvpView(mvpActivity, viewCreator), ItemTripContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_item_trip, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null

    private val mPresenter = ItemTripPresenter()

    private val onActionEdit = object : OnActionData<ItemTripViewModel>{
        override fun onAction(data: ItemTripViewModel) {
            showDialogEdit(data)
        }

    }

    private val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel>{
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ItemTripViewModel
            val model = AddExpenseViewModel.Info.Trip(
                id = dataItem.id.getValueOrDefaultIsZero(),
                name = dataItem.name.getValueOrDefaultIsEmpty()
            )
            AddExpenseFragment.model.trip = model
            mvpActivity.setResult(Activity.RESULT_OK)
            mvpActivity.finish()
        }

    }

    private fun showDialogEdit(data: ItemTripViewModel) {
        val customView = LayoutInflater.from(mvpActivity).inflate(R.layout.custom_dialog_edit_trip, null, false)
        val dialog = AlertDialog.Builder(mvpActivity).setView(customView).create()
        dialog.show()
        dialog.edtChangeName.setText(data.name.getValueOrDefaultIsEmpty())
        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.tvDelete.setOnClickListener {
            // gọi api xóa item
            dialog.dismiss()

        }
        dialog.tvOk.setOnClickListener {
            // gọi api chỉnh sửa item
            dialog.dismiss()
        }
    }

    override fun initCreateView() {
        initRecycleView()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData(isFinished)
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
        if(list.isNotEmpty()){
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView(){
        listViewMvp = ListViewMvp(mvpActivity, view.rvTrip, renderConfig)
        listViewMvp?.addViewRenderer(ItemTripViewRenderer(mvpActivity, onActionEdit))
        listViewMvp?.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewMvp?.createView()
    }

}