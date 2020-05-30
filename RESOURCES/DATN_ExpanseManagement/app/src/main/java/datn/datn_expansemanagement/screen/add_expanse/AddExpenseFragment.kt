package datn.datn_expansemanagement.screen.add_expanse

import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.add_expanse.presentation.AddExpenseView
import datn.datn_expansemanagement.screen.add_expanse.presentation.model.AddExpenseViewModel

class AddExpenseFragment : MvpFragment(){
    companion object{
        var model = AddExpenseViewModel.Info()
        var listFriend = AddExpenseViewModel.Info.ListFriend()
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        return AddExpenseView(getMvpActivity(), AddExpenseView.ViewCreator(getMvpActivity(), null))
    }
}