package datn.datn_expansemanagement.screen.account.item_account.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.common.AppConstants
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import kotlinx.android.synthetic.main.item_layout_account_accumulation.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class ItemAccountAccumulationViewRenderer(
    context: Context,
    private val onActionClickMore: OnActionData<ItemAccountAccumulationViewModel>,
    private val onClickItem: OnActionData<ItemAccountAccumulationViewModel>
) : ViewRenderer<ItemAccountAccumulationViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_account_accumulation
    }

    override fun getModelClass(): Class<ItemAccountAccumulationViewModel> =
        ItemAccountAccumulationViewModel::class.java

    override fun bindView(model: ItemAccountAccumulationViewModel, viewRoot: View) {
        viewRoot.tvWallet.text = model.name
        viewRoot.tvAccumulation.text = Utils.formatMoney(model.price)
        viewRoot.tvCurrent.text = "Từ ngày: ${model.startDate}"
        viewRoot.tvRest.text = "Đến ngày: ${model.endDate}"
        viewRoot.sbPercent.progress =
            (dateToDays(convertStringToDate(getCurrentDate())!!) * 100) / dateToDays(
                convertStringToDate(model.endDate)!!
            )
        viewRoot.sbPercent.max = model.maxProcess

        if (model.isLast) {
            viewRoot.viewBottom.gone()
        } else {
            viewRoot.viewBottom.visible()
        }

        if(model.isFinish){
            viewRoot.clFinish.visible()
        }else{
            viewRoot.clFinish.gone()
            viewRoot.imgMore.setOnClickListener {
                EventFireUtil.fireEvent(onActionClickMore, model)
            }

            viewRoot.clItem.setOnClickListener {
                EventFireUtil.fireEvent(onClickItem, model)
            }
        }
    }

    private fun dateToDays(date: Date): Int {
        //  convert a date to an integer and back again
        val currentTime = date.time
        return (currentTime / AppConstants.MAGIC).toInt()
    }

    private fun convertStringToDate(value: String): Date? {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return try {
            format.parse(value)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    private fun getCurrentDate(): String {
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val calendar = Calendar.getInstance()
        return sdf.format(calendar.time)
    }


}