package datn.datn_expansemanagement.screen.category.item_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemCategoryViewModel

class ItemCategoryMapper(val tabId: Int, private val categoryId: Int? = null) :
    Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(ItemCategoryViewModel(id = 1, name = "Ăn uống", isShowChill = false, isShow = true))
        list.add(ItemCategoryViewModel(id = 2, name = "Ăn uống"))
        list.add(ItemCategoryViewModel(id = 3, name = "Ăn uống"))
        list.add(ItemCategoryViewModel(id = 4, name = "Dịch vụ sinh hoạt", isShowChill = false, isShow = true))

        if (categoryId != null) {
            list.forEach {
                if (it is ItemCategoryViewModel) {
                    if (it.id == categoryId) {
                        it.isChoose = true
                    }
                }
            }
        }

        return list
    }

}