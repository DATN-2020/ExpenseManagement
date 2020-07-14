package datn.datn_expansemanagement.screen.main_plan.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.main_plan.presentation.model.EmptyLineViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanDesItemViewModel
import datn.datn_expansemanagement.screen.main_plan.presentation.model.PlanItemViewModel

class OverviewMapper : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(PlanItemViewModel(type = PlanItemViewModel.Type.BUDGET, name = "Ngân sách"))
        listReturn.add(PlanDesItemViewModel(des = "Tạo ngân sách cho từng nhóm, hoặc cho cả ví"))
        listReturn.add(PlanItemViewModel(type = PlanItemViewModel.Type.TRANSACTION, name = "Giao dịch định kỳ"))
        listReturn.add(PlanDesItemViewModel(des = "Tạo ra định kì các giao dịch sẽ được thêm trong tương lai"))
        listReturn.add(PlanItemViewModel(type = PlanItemViewModel.Type.BILL, name = "Hóa đơn"))
        listReturn.add(PlanDesItemViewModel(des = "Theo dõi các hóa đơn của bạn như điện, internet,.."))
        return listReturn
    }

}