package datn.datn_expansemanagement.screen.plan_detail.buget.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.presentation.model.TypeAddViewModel

class BudgetPresenter(private val screenNavigator: AndroidScreenNavigator) : BudgetContract.Presenter(){
    override fun getData() {
        val list = mutableListOf<ViewModel>()
        list.add(TabItemViewModel(
            id = 0,
            name = "Đang áp dụng"
        ))

        list.add(TabItemViewModel(
            id = 1,
            name = "Đã kết thúc"
        ))
        view?.showData(list)
    }

    override fun gotoAddPlanActivity(typeAdd: TypeAddViewModel) {
        screenNavigator.gotoAddPlanActivity(typeAdd)
    }

}