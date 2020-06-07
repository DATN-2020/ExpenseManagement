package datn.datn_expansemanagement.screen.history.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.history.presentation.HistoryResource
import datn.datn_expansemanagement.screen.history.presentation.model.HistoryItemViewModel
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryItemViewRenderer(context: Context, private val mResource: HistoryResource) :
    ViewRenderer<HistoryItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_history
    }

    override fun getModelClass(): Class<HistoryItemViewModel> = HistoryItemViewModel::class.java

    override fun bindView(model: HistoryItemViewModel, viewRoot: View) {
        GlideImageHelper(context).loadThumbnail(
            viewRoot.imgCategory,
            model.categoryUrl,
            R.drawable.ic_add_category_icon
        )
        viewRoot.tvTitle.text = model.title
        viewRoot.tvMoney.text = Utils.formatMoneyVND(model.money)
        viewRoot.tvWallet.text = model.wallet

        if (model.isIncome) {
            viewRoot.tvMoney.setTextColor(mResource.getColorIncome())
        } else {
            viewRoot.tvMoney.setTextColor(mResource.getColorOutcome())
        }
    }

}