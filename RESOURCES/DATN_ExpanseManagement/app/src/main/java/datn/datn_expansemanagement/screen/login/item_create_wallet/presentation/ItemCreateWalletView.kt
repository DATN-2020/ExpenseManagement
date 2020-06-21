package datn.datn_expansemanagement.screen.login.item_create_wallet.presentation

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.Utils
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.domain.listener.OnActionData
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.event.EventBusData
import datn.datn_expansemanagement.core.event.EventBusLifeCycle
import datn.datn_expansemanagement.screen.login.data.FinishLoginData
import kotlinx.android.synthetic.main.item_layout_create_wallet.view.*

class ItemCreateWalletView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator) :
    AndroidMvpView(mvpActivity, viewCreator), ItemCreateWalletContract.View {

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_create_wallet, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = ItemCreateWalletPresenter()

    private val eventBusLifeCycle = EventBusLifeCycle(object : OnActionData<EventBusData> {
        override fun onAction(data: EventBusData) {
        }
    })

    override fun initCreateView() {
        addLifeCycle(eventBusLifeCycle)
        view.btnStart.setOnClickListener{
            val result = view.edtPrice.text.toString().replace(",","")
            eventBusLifeCycle.sendData(FinishLoginData(result.toDouble()))
        }
        view.edtPrice.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                view.edtPrice.removeTextChangedListener(this)
                if(!view.edtPrice.text.isNullOrEmpty()){
                    view.edtPrice.setText(Utils.customFormatMoney(s.toString()))
                    view.edtPrice.setSelection( view.edtPrice.text.toString().length)
                }
                view.edtPrice.addTextChangedListener(this)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
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