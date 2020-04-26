package datn.datn_expansemanagement.screen.report.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel

class ReportMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        for (i in 1..6){
            listReturn.add(ReportViewModel(
                id = i,
                name = "Tài chính đầu tư"
            ))
        }
        return listReturn
    }

}