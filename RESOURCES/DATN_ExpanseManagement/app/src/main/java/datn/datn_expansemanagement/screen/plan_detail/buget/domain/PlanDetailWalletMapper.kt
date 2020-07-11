package datn.datn_expansemanagement.screen.plan_detail.buget.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.WalletResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.report.presentation.model.GetWalletItemViewModel

class PlanDetailWalletMapper(private val idWallet: Int? = null) : Mapper<WalletResponse, MutableList<ViewModel>>{
    override fun map(input: WalletResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if (!input.data.isNullOrEmpty()) {
            input.data.forEach {
                if(it.idWallet == idWallet){
                    list.add(
                        GetWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero(),
                            isChoose = true
                        )
                    )
                }else{
                    list.add(
                        GetWalletItemViewModel(
                            id = it.idWallet.getValueOrDefaultIsZero(),
                            name = it.nameWallet.getValueOrDefaultIsEmpty(),
                            money = it.amountWallet.getValueOrDefaultIsZero()
                        )
                    )
                }
            }
        }
        return list
    }

}