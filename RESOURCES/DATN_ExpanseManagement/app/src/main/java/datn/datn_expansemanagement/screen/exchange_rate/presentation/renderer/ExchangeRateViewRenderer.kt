package datn.datn_expansemanagement.screen.exchange_rate.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.exchange_rate.presentation.model.ExchangeRateViewModel

class ExchangeRateViewRenderer (context: Context): ViewRenderer<ExchangeRateViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_exchange_rate
    }

    override fun getModelClass(): Class<ExchangeRateViewModel> = ExchangeRateViewModel::class.java

    override fun bindView(model: ExchangeRateViewModel, viewRoot: View) {
//        viewRoot.tvTyGia.text = model.name
//        viewRoot.tvTyGiaDetaiil.text = model.exchange
        // còn cái ảnh thì nào làm được api thì trả về cái ảnh luôn
    }

}