package datn.datn_expansemanagement.screen.category.item_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel

class ItemCategoryMapper(val tabId: Int, val categoryId: Int? = null) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        for(i in 1..4){
            list.add(ItemCategoryViewModel(id = i,name = "Ăn uống"))
        }

        if(categoryId != null){
            list.forEach {
                if(it is ItemCategoryViewModel){
                    if(it.id == categoryId){
                        it.isChoose = true
                    }
                }
            }
        }

        return list
    }

}