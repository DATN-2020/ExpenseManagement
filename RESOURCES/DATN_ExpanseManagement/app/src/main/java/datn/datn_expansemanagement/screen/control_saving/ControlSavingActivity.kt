package datn.datn_expansemanagement.screen.control_saving

import android.os.Bundle
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.account.item_account.presentation.model.ItemAccountAccumulationViewModel
import datn.datn_expansemanagement.screen.control_saving.presentation.ControlSavingView

class ControlSavingActivity : MvpActivity() {
    override fun createAndroidMvpView(savedInstanceState: Bundle?): AndroidMvpView {
        val isCome = intent?.getBooleanExtra("isCome", false)
        val data = intent?.getParcelableExtra<ItemAccountAccumulationViewModel>(
            ItemAccountAccumulationViewModel::class.java.simpleName
        )
        return ControlSavingView(
            this, ControlSavingView.ViewCreator(this, null),
            isCome, data
        )
    }

}