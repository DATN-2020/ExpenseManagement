package datn.datn_expansemanagement.screen.trip.presentation

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.domain.request.AddTripRequest
import datn.datn_expansemanagement.domain.request.TripRequest
import datn.datn_expansemanagement.domain.request.UpdateTripRequest
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.AddExpenseResource
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel
import datn.datn_expansemanagement.screen.trip.presentation.renderer.TripItemViewRenderer
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.custom_dialog_edit_trip.*
import kotlinx.android.synthetic.main.custom_dialog_edit_trip.tvTitleChooseDate
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import kotlinx.android.synthetic.main.layout_trip.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class TripView(mvpActivity: MvpActivity, viewCreator: ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), TripContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_trip, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = TripPresenter(mvpActivity)

    private val renderInputProject = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInputProject).create()
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        view.tvToolbar.text = "Chuyến đi, sự kiện"
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }

        GlideImageHelper(mvpActivity).loadThumbnail(view.imgSave, "", R.drawable.ic_add_white_24dp)
        view.imgSave.setOnClickListener {
            showDialogEdit(isCreate = true)
        }

        initRecycleView()
    }

    override fun showLoading() {
        loadingView.show()

    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        this.listData.clear()
        if (list.isNotEmpty()) {
            this.listData.addAll(list)
        }

        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private val onActionEdit = object : OnActionData<TripItemViewModel> {
        override fun onAction(data: TripItemViewModel) {
            showDialogEdit(data)
        }

    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvTrip, renderConfig)
        listViewMvp?.addViewRenderer(TripItemViewRenderer(mvpActivity, onActionEdit))
        listViewMvp?.setOnItemRvClickedListener(onItemRvClickedListener)
        listViewMvp?.createView()
    }

    private fun showDialogEdit(data: TripItemViewModel? = null, isCreate: Boolean = false) {
        val customView =
            LayoutInflater.from(mvpActivity).inflate(R.layout.custom_dialog_edit_trip, null, false)
        val dialog = AlertDialog.Builder(mvpActivity).setView(customView).create()
        dialog.show()
        if (!isCreate) {
            dialog.edtChangeName.setText(data?.name.getValueOrDefaultIsEmpty())

        }
        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.tvDelete.setOnClickListener {
            // gọi api xóa item
            mPresenter.deleteTrip(data?.id.getValueOrDefaultIsZero())
            dialog.dismiss()

        }
        dialog.tvOk.setOnClickListener {
            // gọi api chỉnh sửa item
            if (isCreate) {
                val data = ConfigUtil.passport
                if (data != null) {
                    val request = AddTripRequest(
                        idUser = data.data.userId,
                        nameTrip = dialog.edtChangeName.text.toString()
                    )
                    mPresenter.addTrip(request)
                }
            } else {
                val request = UpdateTripRequest(
                    nameTrip =  dialog.edtChangeName.text.toString()
                )

                mPresenter.updateTrip(data?.id.getValueOrDefaultIsZero(), request)
            }

            dialog.dismiss()
        }

        if (isCreate) {
            dialog.tvDelete.invisible()
            dialog.tvTitleChooseDate.text = "Thêm sự kiện mới"
        }
    }

    override fun reload(type: String) {
        when(type){
            "add"->{
                showDialogNotify("Thêm sự kiện thành công")
                getData()
            }
            "delete"->{
                showDialogNotify("Xóa sự kiện thành công")
                getData()
            }
            else->{
                showDialogNotify("Cập nhật sự kiện thành công")
                getData()
            }
        }
    }

    private val mResource = AddExpenseResource()
    private fun setDialogFullScreen(dialog: android.app.AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    private fun showDialogNotify(title: String? = null) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            android.app.AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }
        if (!title.isNullOrEmpty()) {
            dialogRegister.tvTitleChooseDate.text = title
        }
    }

    override fun initData() {
        super.initData()
        getData()
    }

    fun getData(){
        val data = ConfigUtil.passport
        if (data != null) {
            mPresenter.getData(TripRequest((data.data.userId.getValueOrDefaultIsZero().toString())))
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

    private val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as TripItemViewModel
            val model = AddExpenseViewModel.Info.Trip(
                id = dataItem.id.getValueOrDefaultIsZero(),
                name = dataItem.name.getValueOrDefaultIsEmpty()
            )
            AddExpenseFragment.model.trip = model
            mvpActivity.setResult(Activity.RESULT_OK)
            mvpActivity.finish()
        }

    }
}