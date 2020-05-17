package datn.datn_expansemanagement.screen.category.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.category.presentation.CategoryResource

class CategoryMapper(private val mResource: CategoryResource) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
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

        listReturn.add(
            TabItemViewModel(
                id = 4,
                name = mResource.getTextInvest()
            )
        )
        return listReturn
    }

}