package datn.datn_expansemanagement.screen.category.item_category.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class ItemTypeCategoryViewModel(
    var id: Int,
    var name: String,
    var imgUrl: String? = null,
    var isShowChill: Boolean = false,
    var listItem: List<ViewModel> = mutableListOf()
) : ViewModel