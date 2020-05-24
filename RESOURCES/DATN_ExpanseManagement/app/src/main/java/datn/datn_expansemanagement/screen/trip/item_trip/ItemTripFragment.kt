package datn.datn_expansemanagement.screen.trip.item_trip

import android.os.Bundle
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpFragment
import datn.datn_expansemanagement.screen.account.presentation.model.TabItemViewModel
import datn.datn_expansemanagement.screen.trip.item_trip.presentation.ItemTripView
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel

class ItemTripFragment : MvpFragment(){

    companion object {
        private const val KEY_TAB = "KEY_TAB"

        fun newInstance(data: ViewModel): ItemTripFragment {
            val recipeTabFragment = ItemTripFragment()
            val bundle = Bundle()
            if(data is TripItemViewModel){
                bundle.putBoolean(KEY_TAB, data.isFinished)
            }
            recipeTabFragment.arguments = bundle
            return recipeTabFragment
        }
    }

    override fun createAndroidMvpView(): AndroidMvpView {
        val isFinished = arguments?.getBoolean(KEY_TAB)
        return ItemTripView(getMvpActivity(), ItemTripView.ViewCreator(getMvpActivity(), null), isFinished)
    }
}