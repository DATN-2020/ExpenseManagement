package datn.datn_expansemanagement.screen.category.item_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel

class ItemCategoryMapper(val tabId: Int) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        for(i in 1..10){
            list.add(ItemCategoryViewModel(name = "Ăn uống"))
        }
        return list
    }

}