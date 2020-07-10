package datn.datn_expansemanagement.screen.information.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.information.presentation.model.InfoBottomViewModel
import datn.datn_expansemanagement.screen.information.presentation.model.InfoHeaderItemViewModel
import datn.datn_expansemanagement.screen.information.presentation.model.InfoItemViewModel

class InformationMapper : Mapper<PassportResponse, MutableList<ViewModel>> {
    override fun map(input: PassportResponse): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()

        if(input.data != null){
            list.add(InfoHeaderItemViewModel())
            list.add(
                InfoItemViewModel(
                    name = input.data.userName
                )
            )

            list.add(
                InfoItemViewModel(
                    name = input.data.password.getValueOrDefaultIsEmpty()
                )
            )

            list.add(
                InfoItemViewModel(
                    name = input.data.fullName
                )
            )
            list.add(InfoBottomViewModel())
        }
        return list
    }

}