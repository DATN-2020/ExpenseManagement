package datn.datn_expansemanagement.screen.list_type_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TypeCategoryResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.list_type_category.presentation.model.TypeCategoryItemViewModel

class ListTypeCategoryMapper(private val typeId: Int? = null) : Mapper<List<TypeCategoryResponse>, MutableList<ViewModel>>{
    override fun map(input: List<TypeCategoryResponse>): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()

        if(!input.isNullOrEmpty()){
            input.forEach {
                list.add(TypeCategoryItemViewModel(
                    id = it.idType.getValueOrDefaultIsZero(),
                    name = it.name.getValueOrDefaultIsEmpty(),
                    imageUrl = it.image.getValueOrDefaultIsEmpty(),
                    isLast = true,
                    isChoose = false
                ))
            }
        }


        if(typeId != null){
            list.forEach {
                if(it is TypeCategoryItemViewModel){
                    if(it.id == typeId){
                        it.isChoose = true
                    }
                }
            }
        }

        return list
    }

}