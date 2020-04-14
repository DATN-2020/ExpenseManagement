package datn.datn_expansemanagement.core.app.domain.excecutor

import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.domain.listener.OnActionNotify


class EventFireUtil {
    companion object {
        @JvmStatic
        fun <T> fireEvent(event: OnActionData<T>?, data: T) {
            event?.onAction(data) // lấy thằng acction và bấm nút lại thôi
        }

        @JvmStatic
        fun fireEvent(event: OnActionNotify?) {
            event?.onActionNotify()
        }
    }
}