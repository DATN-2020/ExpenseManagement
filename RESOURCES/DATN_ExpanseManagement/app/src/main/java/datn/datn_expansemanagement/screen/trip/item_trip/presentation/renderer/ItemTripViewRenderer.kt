package datn.datn_expansemanagement.screen.trip.item_trip.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.model.ItemTripViewModel
import kotlinx.android.synthetic.main.item_layout_trip.view.*

class ItemTripViewRenderer (context: Context,
private val onActionEdit: OnActionData<ItemTripViewModel>): ViewRenderer<ItemTripViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_layout_trip
    }

    override fun getModelClass(): Class<ItemTripViewModel> = ItemTripViewModel::class.java

    override fun bindView(model: ItemTripViewModel, viewRoot: View) {
        viewRoot.tvTrip.text = model.name
        viewRoot.tvNameImg.text = model.nameIcon
        viewRoot.imgEdit.setOnClickListener {
            EventFireUtil.fireEvent(onActionEdit, model)
        }
    }

}