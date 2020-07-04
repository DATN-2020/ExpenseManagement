package datn.datn_expansemanagement.screen.category.item_category.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class CategoryItemViewModel (
    var id: Int,
    var name: String,
    var isChoose: Boolean = false,
    var imgUrl: String? = null
): ViewModel