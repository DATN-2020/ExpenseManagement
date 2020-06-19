package datn.datn_expansemanagement.screen.report_detail.main.presentation

import android.content.Context
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.report.presentation.model.ReportViewModel
import datn.datn_expansemanagement.screen.report_detail.report_receive.ReportReceiveFragment
import kotlinx.android.synthetic.main.layout_add_expanse.view.*

class ReportDetailView (mvpActivity: MvpActivity, viewCreator: LayoutViewCreator,
private val data : ReportViewModel): AndroidMvpView(mvpActivity, viewCreator), ReportDetailContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_report_detail, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ReportDetailPresenter()

    override fun initCreateView() {
        replaceFragment(ReportReceiveFragment.newInstance(data))
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun initData() {
        super.initData()
        mPresenter.getData()
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }

    override fun showData(list: MutableList<ViewModel>) {

    }

    private fun replaceFragment(f: Fragment){
        mvpActivity.supportFragmentManager.beginTransaction()
            .replace(view.flChange.id, f)
            .commit()
    }

}