package datn.datn_expansemanagement.screen.splash.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.screen.splash.presentation.SplashResource
import datn.datn_expansemanagement.screen.splash.presentation.model.SplashModel

class SplashMapper(private val mResource : SplashResource) : Mapper<String, MutableList<ViewModel>>{
    override fun map(input: String): MutableList<ViewModel> {
        val list = mutableListOf<ViewModel>()
        list.add(SplashModel(title = mResource.getTitleSplash1(), img = R.drawable.splash_1))
        list.add(SplashModel(title = mResource.getTitleSplash2(), img = R.drawable.splash_2))
        list.add(SplashModel(title = mResource.getTitleSplash3(), img = R.drawable.splash_3))
        return list
    }
}