package datn.datn_expansemanagement.screen.report.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.ReportWalletSavingResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel

class GetTransactionMapper : Mapper<ReportWalletSavingResponse, MutableList<ViewModel>> {
    override fun map(input: ReportWalletSavingResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if(!input.data.isNullOrEmpty()){
            input.data.forEach {
                list.add(ReportDetailItemViewModel(
                    name = it.nameTrans.getValueOrDefaultIsEmpty(),
                    date = it.dateTrans.getValueOrDefaultIsEmpty(),
                    price = it.priceTrans.getValueOrDefaultIsZero()
                ))
            }
        }
        return list
    }
}