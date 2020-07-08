package datn.datn_expansemanagement.screen.add_plan.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class TimeItemViewModel(
    var id: Int,
    var name: String,
    var value: String,
    var isChoose: Boolean = false,
    var isLast: Boolean = false
) : ViewModel