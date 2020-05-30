package datn.datn_expansemanagement.screen.contacts.presentation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import datn.datn_expansemanagement.R
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
import datn.datn_expansemanagement.screen.contacts.presentation.renderer.ContactsItemViewRenderer
import kotlinx.android.synthetic.main.layout_contacts.view.*
import kotlinx.android.synthetic.main.toolbar_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class ContactsView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ContactsContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_contacts, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ContactsPresenter(mvpActivity)
    private val mResource = ContactsResource()
    private val listData = mutableListOf<ViewModel>()
    private val listNewContacts = mutableListOf<ViewModel>()
    private var mvpNewContacts: ListViewMvp? = null
    private val render = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val config = LinearRenderConfigFactory(render).create()

    private var listViewMvp: ListViewMvp? = null
    private val renderInput = LinearRenderConfigFactory.Input(
        context = mvpActivity,
        orientation = LinearRenderConfigFactory.Orientation.VERTICAL
    )
    private val renderConfig = LinearRenderConfigFactory(renderInput).create()

    override fun initCreateView() {
        initRecycleView()
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgAdd.setOnClickListener {
            // add them contact
            if(!view.edtAddContact.text.isNullOrEmpty()){
                this.listNewContacts.add(ContactsViewModel(
                    id = this.listNewContacts.size + 1,
                    name = view.edtAddContact.text.toString()
                ))
                view.edtAddContact.setText("")
                view.edtAddContact.setHintTextColor(mResource.getColorBlack())
                mvpNewContacts?.setItems(this.listNewContacts)
                mvpNewContacts?.notifyDataChanged()
            }else{
                view.edtAddContact.setHintTextColor(mResource.getColorRed())
            }

        }
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
                name = dataItem.name.getValueOrDefaultIsEmpty()
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
            list.forEach {
                if (it is ContactsViewModel) {
                    if (it.isNew) {
                        this.listNewContacts.add(it)
                    } else {
                        this.listData.add(it)
                    }
                }
            }
        }

        if (!this.listNewContacts.isNullOrEmpty()) {
            view.llNewContact.visible()
            mvpNewContacts?.setItems(this.listNewContacts)
            mvpNewContacts?.notifyDataChanged()
        } else {
            view.llNewContact.gone()
        }
        listViewMvp?.setItems(this.listData)
        listViewMvp?.notifyDataChanged()
    }

    private fun initRecycleView() {
        listViewMvp = ListViewMvp(mvpActivity, view.rvContacts, renderConfig)
        listViewMvp?.addViewRenderer(ContactsItemViewRenderer(mvpActivity))
        listViewMvp?.setOnItemRvClickedListener(onItemClick)
        listViewMvp?.createView()

        mvpNewContacts = ListViewMvp(mvpActivity, view.rvContactsNew, config)
        mvpNewContacts?.addViewRenderer(ContactsItemViewRenderer(mvpActivity))
        mvpNewContacts?.setOnItemRvClickedListener(onItemClick)
        mvpNewContacts?.createView()
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