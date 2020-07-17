package datn.datn_expansemanagement.screen.login.item_create_wallet.presentation

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.config.ConfigUtil
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.domain.request.WalletRequest
import datn.datn_expansemanagement.domain.response.PassportResponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.login.data.FinishLoginData
import datn.datn_expansemanagement.screen.splash.data.PassportDataIntent
import datn.datn_expansemanagement.screen.splash.presentation.SplashResource
import kotlinx.android.synthetic.main.custom_dialog_cancel_contact.*
import kotlinx.android.synthetic.main.item_layout_create_wallet.view.*

class ItemCreateWalletView(
    mvpActivity: MvpActivity,
    viewCreator: AndroidMvpView.ViewCreator,
    private val data: PassportDataIntent?
) :
    AndroidMvpView(mvpActivity, viewCreator), ItemCreateWalletContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_create_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemCreateWalletPresenter()
    private val mResource = SplashResource()

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        view.btnStart.setOnClickListener {
            val result = view.edtPrice.text.toString().replace(",", "")
            if (result.isNullOrEmpty()) {
                mPresenter.createWallet(
                    WalletRequest(
                        userId = data?.userId.getValueOrDefaultIsZero(),
                        amountWallet = 0.0
                    )
                )
            } else {
                mPresenter.createWallet(
                    WalletRequest(
                        userId = data?.userId.getValueOrDefaultIsZero(),
                        amountWallet = result.toDouble()
                    )
                )
            }

        }
        view.edtPrice.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                view.edtPrice.removeTextChangedListener(this)
                if (!view.edtPrice.text.isNullOrEmpty()) {
                    view.edtPrice.setText(Utils.customFormatMoney(s.toString()))
                    view.edtPrice.setSelection(view.edtPrice.text.toString().length)
                }
                view.edtPrice.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    private fun setDialogFullScreen(dialog: AlertDialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            dialog.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            dialog.window?.statusBarColor = mResource.getColorStatusBar()
            dialog.window?.navigationBarColor = Color.TRANSPARENT
            dialog.window?.decorView?.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun handleCreateWallet() {
        ConfigUtil.savePassport(null)
        val user = PassportResponse.Data(
            userId = data?.userId.getValueOrDefaultIsZero(),
            fullName = data?.fullName.getValueOrDefaultIsEmpty(),
            password = data?.password.getValueOrDefaultIsEmpty(),
            userName = data?.userName.getValueOrDefaultIsEmpty(),
            checkWallet = true
        )
        ConfigUtil.savePassport(PassportResponse(
            data = user
        ))
        eventBusLifeCycle.sendData(FinishLoginData())
    }

    override fun handleCreateWalletFail(message: String) {
        val layoutView = LayoutInflater.from(mvpActivity)
            .inflate(R.layout.custom_dialog_cancel_contact, null, false)
        val dialogRegister =
            AlertDialog.Builder(mvpActivity, R.style.DialogNotify).setView(layoutView).create()
        setDialogFullScreen(dialogRegister)
        dialogRegister.show()
        dialogRegister.tvTitleChooseDate.text = message
        dialogRegister.btnCancel.setOnClickListener {
            dialogRegister.dismiss()
        }
    }

    override fun startMvpView() {
        mPresenter.attachView(this)
        super.startMvpView()
    }

    override fun stopMvpView() {
        mPresenter.detachView()
        super.stopMvpView()
    }
}