package datn.datn_expansemanagement.screen.report_detail.report_receive

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.report_receive.presentation.ReportReceiveView

class ReportReceiveFragment : MvpFragment(){

    companion object{
        private const val DATA = "DATA"

        fun newInstance(data: ReportViewModel): ReportReceiveFragment {
            val recipeTabFragment = ReportReceiveFragment()
            val bundle = Bundle()
            bundle.putParcelable(DATA, data)
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val extra = arguments?.getParcelable<ReportViewModel>(DATA)
        return ReportReceiveView(getMvpActivity(), ReportReceiveView.ViewCreator(getMvpActivity(), null), extra)
    }

}