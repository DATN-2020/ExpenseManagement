package datn.datn_expansemanagement.screen.contacts.presentation

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.TrimSign
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.contacts.presentation.model.ContactsViewModel
import datn.datn_expansemanagement.screen.contacts.presentation.renderer.ContactTitleViewRenderer
import datn.datn_expansemanagement.screen.contacts.presentation.renderer.ContactsItemViewRenderer
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.model.ItemTripViewModel
import kotlinx.android.synthetic.main.custom_dialog_edit_trip.*
import kotlinx.android.synthetic.main.layout_contacts.view.*
import kotlinx.android.synthetic.main.layout_item_account.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp
import java.util.*

class ContactsView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ContactsContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_contacts, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ContactsPresenter(mvpActivity)
    private val listData = mutableListOf<ViewModel>()
    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()
    private var searchKey: String? = null

    private val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        mPresenter.getData()
    }

    override fun initCreateView() {
        initRecycleView()
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgAdd.setOnClickListener {
            showDialogEdit()
        }

        view.tvToolbar.text = "Danh sách liên hệ"
        view.svContacts.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                searchKey = newText
                searchContact()
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchKey = query
                searchContact()
                return true
            }
        })

        view.swRefresh.setOnRefreshListener(onRefreshListener)
    }

    private fun showDialogEdit() {
        val customView =
            LayoutInflater.from(mvpActivity).inflate(R.layout.custom_dialog_edit_trip, null, false)
        val dialog = AlertDialog.Builder(mvpActivity).setView(customView).create()
        dialog.show()
        dialog.edtChangeName.hint = "Nhập tên liên hệ mới"
        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.tvDelete.gone()
        dialog.ckIsFinished.gone()
        dialog.tvOk.setOnClickListener {
            // gọi api chỉnh sửa item
            listData.add(
                1,
                ContactsViewModel(
                    id = Math.random().toInt(),
                    name = dialog.edtChangeName.text.toString(),
                    nameChar = dialog.edtChangeName.text.toString().substring(0, 2)
                )
            )

            listViewMvp?.setItems(listData)
            listViewMvp?.notifyDataChanged()
            dialog.dismiss()
        }
    }

    private fun searchContact() {
        val listResult = if (searchKey.isNullOrEmpty()) {
            listData
        } else {
            listResult()
        }

        if (listResult.isEmpty()) {
            view.clNoData.visible()
        } else {
            view.clNoData.gone()
        }

        listViewMvp?.setItems(listResult)
        listViewMvp?.notifyDataChanged()
    }

    private fun listResult(): MutableList<ViewModel> {
        val trimSearch = if (!searchKey.isNullOrEmpty()) {
            TrimSign.getInstances(mvpActivity).unicodeTrimSign(searchKey.getValueOrDefaultIsEmpty())
                .toLowerCase(Locale.ROOT)
        } else {
            ""
        }
        return listData.filter {
            if (it is ContactsViewModel) {
                if (trimSearch.isNotEmpty()) {
                    TrimSign.getInstances(mvpActivity)
                        .unicodeTrimSign(it.name.toLowerCase(Locale.ROOT))
                        .contains(trimSearch, true)
                } else false
            } else false
        }.toMutableList()
    }

    private val onItemClick = object : OnItemRvClickedListener<ViewModel> {
        override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
            dataItem as ContactsViewModel
            AddExpenseFragment.listFriend.list.forEach {
                if (it is AddExpenseViewModel.Info.ListFriend.Friend) {
                    if (it.id == dataItem.id) {
                        mvpActivity.finish()
                        return
                    }
                }
            }

            val item = AddExpenseViewModel.Info.ListFriend.Friend(
                id = dataItem.id.getValueOrDefaultIsZero(),
                name = dataItem.name.getValueOrDefaultIsEmpty(),
                nameUnit = dataItem.nameChar.getValueOrDefaultIsEmpty()
            )
            AddExpenseFragment.listFriend.list.add(item)
            AddExpenseFragment.model.listFriend = AddExpenseFragment.listFriend
            mvpActivity.setResult(Activity.RESULT_OK)
            mvpActivity.finish()
        }

    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
        view.swRefresh.isRefreshing = false
    }

    override fun initData() {
        super.initData()
        requestStoragePermission()
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
        listViewMvp = ListViewMvp(mvpActivity, view.rvContacts, renderConfig)
        listViewMvp?.addViewRenderer(ContactsItemViewRenderer(mvpActivity))
        listViewMvp?.addViewRenderer(ContactTitleViewRenderer(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(onItemClick)
        listViewMvp?.createView()
    }

    private fun requestStoragePermission() {
        Dexter.withActivity(mvpActivity)
            .withPermissions(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                    try {
                        mPresenter.getData()
                    } catch (e: Exception) {
                        mvpActivity.onBackPressed()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }
            }).withErrorListener {
                mvpActivity.onBackPressed()
            }
            .onSameThread()
            .check()
    }
}