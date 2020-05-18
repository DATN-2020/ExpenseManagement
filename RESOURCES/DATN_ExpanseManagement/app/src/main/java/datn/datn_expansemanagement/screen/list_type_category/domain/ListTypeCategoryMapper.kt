package datn.datn_expansemanagement.screen.list_type_category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.list_type_category.presentation.model.TypeCategoryItemViewModel

class ListTypeCategoryMapper(private val typeId: Int? = null) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        for (i in 1.. 6){
            if(i == 6){
                list.add(TypeCategoryItemViewModel(
                    id = i,
                    name = "Sinh hoạt",
                    isLast = true
                ))
            }else{
                list.add(TypeCategoryItemViewModel(
                    id = i,
                    name = "Sinh hoạt"
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