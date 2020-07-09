package datn.datn_expansemanagement.screen.information.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.information.presentation.model.InfoHeaderItemViewModel

class InformationMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(InfoHeaderItemViewModel())
        return list
    }

}