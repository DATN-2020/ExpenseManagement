package datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.BillResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.BillItemViewModel
import datn.datn_expansemanagement.screen.plan_detail.buget.item_tab.presentation.model.NoDataItemViewModel
import java.text.SimpleDateFormat

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
                        dateE = Utils.convertDateFormat(
                            it.dateE.getValueOrDefaultIsEmpty(),
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
                            SimpleDateFormat("dd/MM/yyyy")
                        ),
                        dateS = Utils.convertDateFormat(
                            it.dateS.getValueOrDefaultIsEmpty(),
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
                            SimpleDateFormat("dd/MM/yyyy")
                        ),
                        image = it.image.getValueOrDefaultIsEmpty(),
                        isDeadline = it.isFinnish.getValueOrDefault(),
                        isPay = it.isPay.getValueOrDefault(),
                        currentDate = Utils.convertDateFormat(
                            it.dateTimeS.getValueOrDefaultIsEmpty(),
                            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
                            SimpleDateFormat("dd/MM/yyyy")
                        )
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