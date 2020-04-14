package datn.datn_expansemanagement.screen.test.presentation

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpPresenter
import datn.datn_expansemanagement.core.base.presentation.mvp.base.MvpView

interface TestContract {
    interface View: MvpView {
        fun showError(msgError: String)
        fun showLoading()
        fun hideLoading()
    }

    abstract class Presenter : MvpPresenter<View>(){

    }
}