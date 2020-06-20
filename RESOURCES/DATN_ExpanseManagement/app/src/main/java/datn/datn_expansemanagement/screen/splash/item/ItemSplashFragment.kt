package datn.datn_expansemanagement.screen.splash.item

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.splash.item.presentation.ItemSplashView
import datn.datn_expansemanagement.screen.splash.presentation.model.SplashModel

class ItemSplashFragment : MvpFragment(){


    companion object {
        private const val DATA = "DATA"

        fun newInstance(data: ViewModel): ItemSplashFragment {
            val recipeTabFragment = ItemSplashFragment()
            val bundle = Bundle()
            if(data is SplashModel){
                bundle.putParcelable(DATA, data)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val data = arguments?.getParcelable<SplashModel>(DATA)

        return ItemSplashView(getMvpActivity(), ItemSplashView.ViewCreator(getMvpActivity(), null), data)
    }

}