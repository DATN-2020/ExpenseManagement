package datn.datn_expansemanagement.screen.category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.category.presentation.CategoryResource

class CategoryMapper(
    private val mResource: CategoryResource,
    private val isDonate: Boolean = false,
    private val isPlan: Boolean = false
) : Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        if(isPlan){
            listReturn.add(
                TabItemViewModel(
                    id = 1,
                    name = mResource.getTextDonate()
                )
            )
            listReturn.add(
                TabItemViewModel(
                    id = 2,
                    name = mResource.getTextReceive()
                )
            )
            listReturn.add(
                TabItemViewModel(
                    id = 3,
                    name = mResource.getTextLoan()
                )
            )
        }else{
            if (isDonate) {
                listReturn.add(
                    TabItemViewModel(
                        id = 1,
                        name = mResource.getTextDonate()
                    )
                )
                return listReturn
            } else {
                listReturn.add(
                    TabItemViewModel(
                        id = 2,
                        name = mResource.getTextReceive()
                    )
                )
                return listReturn
            }
        }
        return listReturn
    }
}