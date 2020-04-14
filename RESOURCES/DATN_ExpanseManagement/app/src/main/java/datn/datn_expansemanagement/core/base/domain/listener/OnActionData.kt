package datn.datn_expansemanagement.core.base.domain.listener

import java.io.Serializable

@FunctionalInterface
interface OnActionData<in T> : Serializable {
    fun onAction(data: T)
}
