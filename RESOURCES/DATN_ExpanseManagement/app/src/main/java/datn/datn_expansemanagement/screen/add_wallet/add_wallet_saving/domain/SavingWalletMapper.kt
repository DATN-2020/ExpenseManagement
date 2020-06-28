package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.*
import datn.datn_expansemanagement.screen.overview.presentation.model.EmptyLineViewModel

class SavingWalletMapper(private val mResource: AddWalletResource) :
    Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(AddWalletHeaderItemViewModel())
        list.add(AddWalletNameItemViewModel())
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.SAVING,
                hint = mResource.getHintBank()
            )
        )
        list.add(EmptyLineViewModel())
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.START_DATE,
                hint = mResource.getStartDateSent()
            )
        )
        list.add(
            AddWalletTypeItemViewModel(
                type = AddWalletTypeItemViewModel.Type.PERIOD,
                hint = mResource.getPeriod()
            )
        )
        list.add(
            AddWalletRateItemViewModel(
                title = mResource.getTextInterestRate(),
                unit = mResource.getTextUnitRate()
            )
        )
        list.add(
            AddWalletRateItemViewModel(
                title = mResource.getTextNonInterestRate(),
                unit = mResource.getTextUnitRate()
            )
        )
        list.add(
            AddWalletRateItemViewModel(
                title = mResource.getTextDateRate(),
                unit = mResource.getTextDay()
            )
        )
        list.add(AddWalletBottomItemViewModel())
        return list
    }
}
