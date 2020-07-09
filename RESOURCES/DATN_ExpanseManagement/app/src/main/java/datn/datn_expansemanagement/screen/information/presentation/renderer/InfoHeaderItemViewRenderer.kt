package datn.datn_expansemanagement.screen.information.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.domain.excecutor.EventFireUtil
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.information.presentation.model.InfoHeaderItemViewModel
import kotlinx.android.synthetic.main.item_layout_information_header_vholder.view.*

class InfoHeaderItemViewRenderer(
    context: Context,
    private val onActionChooseAvatar: OnActionData<InfoHeaderItemViewModel>
) : ViewRenderer<InfoHeaderItemViewModel>(context) {
    override fun getLayoutId(): Int {
        return R.layout.item_layout_information_header_vholder
    }

    override fun getModelClass(): Class<InfoHeaderItemViewModel> =
        InfoHeaderItemViewModel::class.java

    override fun bindView(model: InfoHeaderItemViewModel, viewRoot: View) {
        if (model.isEnable) {
            viewRoot.imgAvatar.setOnClickListener {
                EventFireUtil.fireEvent(onActionChooseAvatar, model)
            }
            viewRoot.imgCamera.setOnClickListener {
                EventFireUtil.fireEvent(onActionChooseAvatar, model)
            }
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgAvatar,
                model.imgUrl.getValueOrDefaultIsEmpty(),
                R.drawable.ic_photo_information
            )
            if (model.imgUrl.isNullOrEmpty()) {
                viewRoot.imgCamera.gone()
            } else {
                viewRoot.imgCamera.visible()
            }
        } else {
            if (model.imgUrl.isNullOrEmpty()) {
                viewRoot.imgCamera.gone()
            } else {
                viewRoot.imgCamera.visible()
            }
            GlideImageHelper(context).loadThumbnail(
                viewRoot.imgAvatar,
                model.imgUrl.getValueOrDefaultIsEmpty(),
                R.drawable.ic_gray_image_info_default
            )
        }
    }

}