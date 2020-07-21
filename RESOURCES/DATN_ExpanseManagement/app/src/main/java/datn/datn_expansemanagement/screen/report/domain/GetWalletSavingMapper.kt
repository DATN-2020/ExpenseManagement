package datn.datn_expansemanagement.screen.report.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.domain.response.WalletSavingResponse
import datn.datn_expansemanagement.kotlinex.boolean.getValueOrDefault
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel

class GetWalletSavingMapper(private val idWallet: Int? = null) : Mapper<WalletSavingResponse, MutableList<ViewModel>> {
    override fun map(input: WalletSavingResponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()

        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if(it.idSaving == idWallet){
                    listReturn.add(
                        GetWalletItemViewModel(
                            id = it.idSaving.getValueOrDefaultIsZero(),
                            name = it.name.getValueOrDefaultIsEmpty(),
                            money = it.price.getValueOrDefaultIsZero(),
                            currentMoney = it.priceEnd.getValueOrDefaultIsZero(),
                            endDate = it.dateE.getValueOrDefaultIsEmpty(),
                            startDate = it.dateS.getValueOrDefaultIsEmpty(),
                            interest = it.interest.getValueOrDefaultIsZero(),
                            isFinish = it.isFinish.getValueOrDefault(),
                            isChoose = true,
                            isCreditCard = true
                        )
                    )
                }else{
                    listReturn.add(
                        GetWalletItemViewModel(
                            id = it.idSaving.getValueOrDefaultIsZero(),
                            name = it.name.getValueOrDefaultIsEmpty(),
                            money = it.price.getValueOrDefaultIsZero(),
                            currentMoney = it.priceEnd.getValueOrDefaultIsZero(),
                            endDate = it.dateE.getValueOrDefaultIsEmpty(),
                            startDate = it.dateS.getValueOrDefaultIsEmpty(),
                            isFinish = it.isFinish.getValueOrDefault(),
                            interest = it.interest.getValueOrDefaultIsZero(),
                            isCreditCard = true
                        )
                    )
                }
            }
        }

        return listReturn
    }

}