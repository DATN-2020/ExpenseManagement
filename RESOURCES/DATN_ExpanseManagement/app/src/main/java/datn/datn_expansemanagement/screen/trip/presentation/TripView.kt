package datn.datn_expansemanagement.screen.trip.presentation

import android.content.Context
import android.util.SparseArray
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.screen.trip.item_trip.ItemTripFragment
import datn.datn_expansemanagement.screen.trip.presentation.model.TripItemViewModel
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import kotlinx.android.synthetic.main.layout_trip.view.*

class TripView (mvpActivity: MvpActivity, viewCreator: ViewCreator): AndroidMvpView(mvpActivity, viewCreator), TripContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_trip, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = TripPresenter()

    companion object{
        val listTab = mutableListOf<ViewModel>()
    }
    private val adapter = ViewPagerAdapter(mvpActivity.supportFragmentManager)

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        view.tvToolbar.text = "Chuyến đi, sự kiện"
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgSave.setOnClickListener {
            //todo luu item
            mvpActivity.finish()
        }
    }

    override fun showLoading() {
        loadingView.show()

    }

    override fun hideLoading() {
        loadingView.hide()
    }

    override fun showData(list: MutableList<ViewModel>) {
        listTab.clear()
        if(list.isNotEmpty()){
            listTab.addAll(list)
        }

        initTabLayout()
    }

    private fun initTabLayout() {
        view.vpTrip.offscreenPageLimit = 3
        view.vpTrip.adapter = adapter
        view.tlTrip.setupWithViewPager(view.vpTrip)
        listTab.forEachIndexed { index, viewModel ->
            if(viewModel is TripItemViewModel) {
                view.tlTrip.getTabAt(index)?.text = viewModel.name
            }
        }
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val mFragmentList: SparseArray<Fragment> = SparseArray()

        override fun getItem(position: Int): Fragment {
            return ItemTripFragment.newInstance(listTab[position])
        }

        override fun getCount(): Int {
            return listTab.size
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val fragment = super.instantiateItem(container, position)
            mFragmentList.put(position, fragment as Fragment)
            return fragment
        }

        override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
            super.destroyItem(container, position, any)
            mFragmentList.remove(position)
        }
    }

    override fun initData() {
        super.initData()
        mPresenter.getData()
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