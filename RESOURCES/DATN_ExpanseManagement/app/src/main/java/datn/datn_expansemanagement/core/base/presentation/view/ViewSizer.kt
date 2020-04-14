package datn.datn_expansemanagement.core.base.presentation.view

import datn.datn_expansemanagement.core.base.presentation.view.ViewAction
import datn.datn_expansemanagement.core.base.presentation.view.ViewSize

interface ViewSizer {
    fun size(viewParentSize: ViewSize): ViewAction
}