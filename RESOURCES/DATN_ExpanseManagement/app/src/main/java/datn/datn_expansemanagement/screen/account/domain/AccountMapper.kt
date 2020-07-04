package datn.datn_expansemanagement.screen.account.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.account.presentation.AccountResource
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel

class AccountMapper(private val mResource: AccountResource) :
    Mapper<String, MutableList<ViewModel>> {
    override fun map(input: String): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        listReturn.add(
            TabItemViewModel(
                id = 1,
                name = mResource.getTabOne()
            )
        )

        listReturn.add(
            TabItemViewModel(
                id = 2,
                name = mResource.getTabTwo()
            )
        )
        return listReturn
    }

}