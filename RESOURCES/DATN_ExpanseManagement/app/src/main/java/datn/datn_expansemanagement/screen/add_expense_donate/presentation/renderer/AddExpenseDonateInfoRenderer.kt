package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.view.View
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.OnItemRvClickedListener
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.collection.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.add_expanse.AddExpenseFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.AddExpenseDonateResource
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import kotlinx.android.synthetic.main.item_layout_add_expanse_info.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddExpenseDonateInfoRenderer(
    val mvpActivity: MvpActivity, private val mResource: AddExpenseDonateResource,
    private val onClickExpand: OnActionData<AddExpenseDonateInfoViewModel>,
    private val onChooseTrip: OnActionData<AddExpenseDonateInfoViewModel>,
    private val onChooseFriend: OnActionData<AddExpenseDonateInfoViewModel>
) : ViewRenderer<AddExpenseDonateInfoViewModel>(mvpActivity) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_add_expanse_info
    }

    override fun getModelClass(): Class<AddExpenseDonateInfoViewModel> =
        AddExpenseDonateInfoViewModel::class.java

    override fun bindView(model: AddExpenseDonateInfoViewModel, viewRoot: View) {

        val renderInput = LinearRenderConfigFactory.Input(
            context = mvpActivity,
            orientation = LinearRenderConfigFactory.Orientation.HORIZONTAL
        )
        val renderConfig = LinearRenderConfigFactory(renderInput).create()

        val listViewMvp = ListViewMvp(mvpActivity, viewRoot.rvFriend, renderConfig)

        val actionRemove = object : OnActionData<AddExpenseViewModel.Info.ListFriend.Friend>{
            override fun onAction(data: AddExpenseViewModel.Info.ListFriend.Friend) {
                AddExpenseFragment.listFriend.list.forEach {
                    it as AddExpenseViewModel.Info.ListFriend.Friend
                    if(it.id == data.id){
                        AddExpenseFragment.listFriend.list.remove(it)
                    }
                }
                listViewMvp.setItems(AddExpenseFragment.listFriend.list.getValueOrDefault())
                listViewMvp.notifyDataChanged()

                if(AddExpenseFragment.listFriend.list.isNullOrEmpty()){
                    viewRoot.edtFriend.visible()
                }else{
                    viewRoot.edtFriend.invisible()
                }
            }

        }

        val onItemRvClickedListener = object : OnItemRvClickedListener<ViewModel>{
            override fun onItemClicked(view: View, position: Int, dataItem: ViewModel) {
                EventFireUtil.fireEvent(onChooseFriend, model)
            }

        }

        if (!AddExpenseFragment.listFriend.list.isNullOrEmpty()){
            viewRoot.rvFriend.visible()
            viewRoot.edtFriend.invisible()
            listViewMvp.addViewRenderer(ItemFriendViewRenderer(mvpActivity, actionRemove))
            listViewMvp.setOnItemRvClickedListener(onItemRvClickedListener)
            listViewMvp.createView()
            listViewMvp.setItems(AddExpenseFragment.listFriend.list.getValueOrDefault())
            listViewMvp.notifyDataChanged()
        }else{
            viewRoot.edtFriend.visible()
            viewRoot.rvFriend.gone()
        }

        if(AddExpenseFragment.model.trip != null){
            viewRoot.edtTrip.text = AddExpenseFragment.model.trip?.name.getValueOrDefaultIsEmpty()
        }else{
            viewRoot.edtTrip.text = ""
        }

        if (model.isExpand) {
            viewRoot.clTop.visible()
            viewRoot.viewBottom3.visible()
            viewRoot.tvExpand.text = mResource.getTextCollapse()
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgExpand,
                "",
                R.drawable.ic_keyboard_arrow_up_blue_24dp
            )
        } else {
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgExpand,
               "",
                R.drawable.ic_keyboard_arrow_down_blue_24dp
            )
            viewRoot.viewBottom3.gone()
            viewRoot.clTop.gone()
            viewRoot.tvExpand.text = mResource.getTextExpand()
            AddExpenseFragment.model.trip = null
            AddExpenseFragment.listFriend.list.clear()
            AddExpenseFragment.model.listFriend?.list?.clear()
        }

        viewRoot.clExpand.setOnClickListener {
            EventFireUtil.fireEvent(onClickExpand, model)
        }

        viewRoot.clTrip.setOnClickListener {
            EventFireUtil.fireEvent(onChooseTrip, model)
        }

        viewRoot.clContact.setOnClickListener {
            EventFireUtil.fireEvent(onChooseFriend, model)
        }

        viewRoot.rvFriend.setOnClickListener {
            EventFireUtil.fireEvent(onChooseFriend, model)
        }
    }

}