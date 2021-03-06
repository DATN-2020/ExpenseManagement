package datn.datn_expansemanagement.screen.trip.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel
import kotlinx.android.synthetic.main.item_layout_trip.view.*

class TripItemViewRenderer(
    context: Context,
    private val onActionEdit: OnActionData<TripItemViewModel>
) : ViewRenderer<TripItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_trip
    }

    override fun getModelClass(): Class<TripItemViewModel> = TripItemViewModel::class.java

    override fun bindView(model: TripItemViewModel, viewRoot: View) {
        viewRoot.tvTrip.text = model.name
        viewRoot.tvNameImg.gone()
        GlideImageHelper(context).loadThumbnail(viewRoot.imgTrip, "", R.drawable.ic_report_trip)
        viewRoot.imgEdit.setOnClickListener {
            EventFireUtil.fireEvent(onActionEdit, model)
        }
    }

}