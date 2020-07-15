package datn.datn_expansemanagement.screen.category.item_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.CategoryItemViewModel
import datn.datn_expansemanagement.screen.category.item_category.presentation.model.ItemTypeCategoryViewModel

class ItemCategoryMapper(private val typeExpense: String, private val categoryId: Int? = null) :
    Mapper<List<TypeCategoryResponse>, MutableList<ViewModel>> {
    override fun map(input: List<TypeCategoryResponse>): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        if (!input.isNullOrEmpty()) {
            input.forEach {
                val listItem = mutableListOf<ViewModel>()
                if (!it.categories.isNullOrEmpty()) {
                    it.categories.forEach { item ->
                        if (item.idCate == categoryId) {
                            listItem.add(
                                CategoryItemViewModel(
                                    id = item.idCate.getValueOrDefaultIsZero(),
                                    name = item.nameCate.getValueOrDefaultIsEmpty(),
                                    imgUrl = item.imageCate.getValueOrDefaultIsEmpty(),
                                    isChoose = true
                                )
                            )
                        } else {
                            listItem.add(
                                CategoryItemViewModel(
                                    id = item.idCate.getValueOrDefaultIsZero(),
                                    name = item.nameCate.getValueOrDefaultIsEmpty(),
                                    imgUrl = item.imageCate.getValueOrDefaultIsEmpty()
                                )
                            )
                        }

                    }
                }
//                when(it.typeExpense.trim().toLowerCase()){
//                    "Spend money"->{}
//                    "Collect money"->{}
//                    "Borrow"->{}
//                }
//                if (/*it.typeExpense.trim().toLowerCase() == typeExpense.trim().toLowerCase()*/) {
//                    list.add(
//                        ItemTypeCategoryViewModel(
//                            id = it.id.getValueOrDefaultIsZero(),
//                            name = it.nameType.getValueOrDefaultIsEmpty(),
//                            imgUrl = it.imageType.getValueOrDefaultIsEmpty(),
//                            listItem = listItem
//                        )
//                    )
//                }
            }
        }
        return list
    }

}