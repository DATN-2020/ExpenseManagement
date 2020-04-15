package datn.datn_expansemanagement.screen.overview.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.overview.presentation.model.ExchangeRateViewModel
import kotlinx.android.synthetic.main.item_tygia.view.*

class ExchangeReteViewRenderer (context: Context): ViewRenderer<ExchangeRateViewModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_tygia
    }

    override fun getModelClass(): Class<ExchangeRateViewModel> = ExchangeRateViewModel::class.java

    override fun bindView(model: ExchangeRateViewModel, viewRoot: View) {
        viewRoot.tvTyGia.text = model.name
        viewRoot.tvTyGiaDetaiil.text = model.exchange
        // còn cái ảnh thì nào làm được api thì trả về cái ảnh luôn
    }

}