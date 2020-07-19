package datn.datn_expansemanagement.screen.report_detail.main.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.invisible
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.report_detail.main.presentation.model.ReportDetailItemViewModel
import kotlinx.android.synthetic.main.item_layout_report_detail_child.view.*

class ReportDetailItemViewRenderer(context: Context) :
    ViewRenderer<ReportDetailItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_report_detail_child
    }

    override fun getModelClass(): Class<ReportDetailItemViewModel> =
        ReportDetailItemViewModel::class.java

    override fun bindView(model: ReportDetailItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgItem,
            model.imgUrl,
            R.drawable.ic_default
        )
        viewRoot.tvName.text = model.name
        viewRoot.tvDate.text = model.date
        viewRoot.tvDes.text = model.des
        viewRoot.tvPrice.text = Utils.formatMoney(model.price)

        if(model.isLast){
            viewRoot.viewBottom.invisible()
        }else{
            viewRoot.viewBottom.visible()
        }
    }

}