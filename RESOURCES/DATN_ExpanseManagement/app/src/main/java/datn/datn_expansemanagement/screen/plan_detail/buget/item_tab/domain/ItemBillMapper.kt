package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.BillResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BillItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.NoDataItemViewModel

class ItemBillMapper(private val tab: TabItemViewModel) :
    Mapper<BillResponse, MutableList<ViewModel>> {
    override fun map(input: BillResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        val listReturn = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                list.add(
                    BillItemViewModel(
                        idBill = it.idBill.getValueOrDefaultIsZero(),
                        name = it.name.getValueOrDefaultIsEmpty(),
                        amount = it.amount.getValueOrDefaultIsZero(),
                        dateE = it.dateE.getValueOrDefaultIsEmpty(),
                        dateS = it.dateS.getValueOrDefaultIsEmpty(),
                        image = it.image.getValueOrDefaultIsEmpty(),
                        isDeadline = it.isDeadline.getValueOrDefault(),
                        isPay = it.isPay.getValueOrDefault()
                    )
                )
            }
        }

        if (tab.id == 0) {
            list.forEach {
                when (it) {
                    is BillItemViewModel -> {
                        if (!it.isPay) {
                            listReturn.add(it)
                        }
                    }
                }
            }
        }

        if (tab.id == 1) {
            list.forEach {
                when (it) {
                    is BillItemViewModel -> {
                        if (it.isPay) {
                            listReturn.add(it)
                        }
                    }
                }
            }
        }

        if (listReturn.isNullOrEmpty()) {
            listReturn.add(NoDataItemViewModel())
        }

        return listReturn
    }

}