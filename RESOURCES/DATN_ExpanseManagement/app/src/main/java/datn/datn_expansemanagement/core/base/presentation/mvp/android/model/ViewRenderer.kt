@file:Suppress("DEPRECATION")

package datn.datn_expansemanagement.core.base.presentation.mvp.android.model

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.LayoutRes
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewFinder
import datn.datn_expansemanagement.core.base.domain.manager.AndroidResourceManager

abstract class ViewRenderer<M : ViewModel>(val context: Context) : ViewBinder.Binder<M> {
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getModelClass(): Class<M>

    abstract fun bindView(model: M, viewRoot: View)
    override fun bindView(model: M, finder: ViewFinder, payloads: MutableList<Any>) {
        bindView(model, finder.getRootView())
    }

    fun createViewBinder(): ViewBinder<M> =
        ViewBinder<M>(getLayoutId(), getModelClass(), context, this)

    fun getString(stringId: Int): String {
        return AndroidResourceManager().getString(stringId)
    }

    fun getInt(@IntegerRes intId: Int): Int {
        return AndroidResourceManager().getInt(intId)
    }

    fun getDrawable(@DrawableRes drawableId: Int): Drawable? {
        return AndroidResourceManager().getDrawable(drawableId)
    }

    fun getColor(@ColorRes colorId: Int): Int {
        return AndroidResourceManager().getColor(colorId)
    }
}