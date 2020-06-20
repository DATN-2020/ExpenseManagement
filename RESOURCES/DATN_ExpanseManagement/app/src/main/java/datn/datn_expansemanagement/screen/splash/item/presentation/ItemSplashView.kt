package datn.datn_expansemanagement.screen.splash.item.presentation

import android.content.Context
import android.view.ViewGroup
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.splash.presentation.model.SplashModel
import kotlinx.android.synthetic.main.item_layout_splash.view.*

class ItemSplashView(mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator,
private val data : SplashModel?): AndroidMvpView(mvpActivity, viewCreator), ItemSplashContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.item_layout_splash, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)

    override fun initCreateView() {
        GlideImageHelper(mvpActivity).loadThumbnail(view.imgSplash, data?.img.getValueOrDefaultIsZero(), R.drawable.splash_1)
        view.tvSplash.text = data?.title.getValueOrDefaultIsEmpty()
    }

    override fun showLoading() {
        loadingView.show()
    }

    override fun hideLoading() {
        loadingView.hide()
    }

}