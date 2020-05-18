package datn.datn_expansemanagement.screen.add_category.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class TypeCategoryItemViewModel(
    var id: Int,
    var name: String,
    var isChoose : Boolean = false
) : ViewModel