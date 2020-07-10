package datn.datn_expansemanagement.screen.information.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView
import datn.datn_expansemanagement.domain.response.PassportResponse

interface InformationContract{
    interface View : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message: String)
        fun showData(list: MutableList<ViewModel>)
    }

    abstract class Presenter : MvpPresenter<View>() {
        abstract fun getData(passportResponse: PassportResponse)
        abstract fun gotoLoginActivity()
    }
}