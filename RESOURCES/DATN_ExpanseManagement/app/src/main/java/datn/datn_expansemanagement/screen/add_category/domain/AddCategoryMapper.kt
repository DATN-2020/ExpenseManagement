package datn.datn_expansemanagement.screen.add_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.add_category.presentation.model.TypeCategoryItemViewModel

class AddCategoryMapper : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        for (i in 1.. 6){
            list.add(TypeCategoryItemViewModel(
                id = i,
                name = "Sinh hoạt"
            ))
        }
        return list
    }

}