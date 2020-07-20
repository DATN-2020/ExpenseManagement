package datn.datn_expansemanagement.screen.control_saving.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.model.AddWalletBottomItemViewModel
import datn.datn_expansemanagement.screen.control_saving.presentation.model.ControlSavingHeaderViewModel

class ControlSavingMapper(private val isCome: Boolean? = false) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(ControlSavingHeaderViewModel(isCome = isCome, price = 0.0))
        list.add(AddWalletBottomItemViewModel())
        return list
    }

}