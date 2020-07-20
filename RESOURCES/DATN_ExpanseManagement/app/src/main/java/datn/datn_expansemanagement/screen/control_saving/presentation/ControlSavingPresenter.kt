package datn.datn_expansemanagement.screen.control_saving.presentation

import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.control_saving.domain.ControlSavingMapper

class ControlSavingPresenter : ControlSavingContract.Presenter(){
    override fun getData(isCome: Boolean?, data: ItemAccountAccumulationViewModel?) {
        view?.showData(ControlSavingMapper(isCome).map(""))
    }

}