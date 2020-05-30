package datn.datn_expansemanagement.screen.add_expense_donate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel
import datn.datn_expansemanagement.screen.add_expense_donate.presentation.model.AddExpenseDonateInfoViewModel
import kotlinx.android.synthetic.main.item_contacts_vholder.view.*

class ItemFriendViewRenderer (context: Context,
private val onActionRemove: OnActionData<AddExpenseViewModel.Info.ListFriend.Friend>): ViewRenderer<AddExpenseViewModel.Info.ListFriend.Friend>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_contacts_vholder
    }

    override fun getModelClass(): Class<AddExpenseViewModel.Info.ListFriend.Friend> = AddExpenseViewModel.Info.ListFriend.Friend::class.java

    override fun bindView(model: AddExpenseViewModel.Info.ListFriend.Friend, viewRoot: View) {
        viewRoot.tvName.text = model.name
        viewRoot.imgRemove.setOnClickListener {
            EventFireUtil.fireEvent(onActionRemove, model)
        }
    }

}