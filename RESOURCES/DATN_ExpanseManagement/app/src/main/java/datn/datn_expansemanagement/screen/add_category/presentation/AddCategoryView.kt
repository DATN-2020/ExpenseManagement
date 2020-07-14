package datn.datn_expansemanagement.screen.add_category.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.change_screen.AndroidScreenNavigator
import datn.datn_expansemanagement.core.app.change_screen.Request
import datn.datn_expansemanagement.core.app.view.loading.Loadinger
import datn.datn_expansemanagement.core.base.presentation.mvp.android.AndroidMvpView
import datn.datn_expansemanagement.core.base.presentation.mvp.android.MvpActivity
import datn.datn_expansemanagement.core.base.presentation.mvp.android.lifecycle.ViewResult
import datn.datn_expansemanagement.core.base.presentation.mvp.android.list.LinearRenderConfigFactory
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.kotlinex.view.gone
import datn.datn_expansemanagement.kotlinex.view.visible
import datn.datn_expansemanagement.screen.ValidateItemViewModel
import datn.datn_expansemanagement.screen.add_category.data.TypeCategoryDataIntent
import datn.datn_expansemanagement.screen.add_category.presentation.model.CategoryDataIntent
import datn.datn_expansemanagement.screen.list_type_category.presentation.model.TypeCategoryItemViewModel
import kotlinx.android.synthetic.main.layout_add_category.view.*
import kotlinx.android.synthetic.main.layout_toolbar_add_category.view.*
import vn.minerva.core.base.presentation.mvp.android.list.ListViewMvp

class AddCategoryView (mvpActivity: MvpActivity, viewCreator: AndroidMvpView.ViewCreator): AndroidMvpView(mvpActivity, viewCreator), AddCategoryContract.View{

    class ViewCreator(context: Context, viewGroup: ViewGroup?) :
        AndroidMvpView.LayoutViewCreator(R.layout.layout_add_category, context, viewGroup)

    private val loadingView = Loadinger.create(mvpActivity, mvpActivity.window)
    private val mPresenter = AddCategoryPresenter(screenNavigator = AndroidScreenNavigator(mvpActivity))
    private val mResource = AddCategoryResource()
    private var dataType : TypeCategoryDataIntent? = null

    override fun initCreateView() {
        mvpActivity.setFullScreen()
        view.imgBack.setOnClickListener {
            mvpActivity.onBackPressed()
        }
        view.imgSave.setOnClickListener {
            saveCategory()
        }

        view.btnSave.setOnClickListener {
            saveCategory()
        }

        view.edtCategoryName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(view.edtCategoryName.text.isNullOrEmpty()){
                    view.tvWarning.visible()
                    view.viewTop.setBackgroundColor(mResource.getColorError())
                }else{
                    view.tvWarning.gone()
                    view.viewTop.setBackgroundColor(mResource.getColorSuccess())
                }
            }
        })

        view.tvChooseCategory.setOnClickListener {
            mPresenter.gotoListTypeCategoryActivity(dataType)
        }
    }

    private fun validationName(model: ValidateItemViewModel): Boolean {
        val isWarning = model.value.isNullOrEmpty()
        var warningValue: String? = mResource.getTextErrorEmpty()
        model.showWarning = isWarning
        model.warning = warningValue
        return isWarning
    }

    private fun saveCategory(){
        var isSuccess = true
        val dataCheck = ValidateItemViewModel(value = view.edtCategoryName.text.toString())
        if (validationName(dataCheck)) {
            view.tvWarning.visible()
            view.tvWarning.text = dataCheck.warning.getValueOrDefaultIsEmpty()
            isSuccess = false
        }

        if(isSuccess){
            val intent = Intent()
            val data = CategoryDataIntent(1, "test")
            intent.putExtra(CategoryDataIntent::class.java.simpleName, data)
            mvpActivity.setResult(Activity.RESULT_OK, intent)
            mvpActivity.finish()
        }
    }

    override fun onViewResult(viewResult: ViewResult) {
        super.onViewResult(viewResult)
        when (viewResult.requestCode) {
            Request.REQUEST_CODE_TYPE_CATEGORY -> {
                var typeData : TypeCategoryItemViewModel? = null
                typeData = viewResult.data?.getParcelableExtra(TypeCategoryItemViewModel::class.java.simpleName)
                dataType = TypeCategoryDataIntent(id = typeData?.id.getValueOrDefaultIsZero())
                view.tvChooseCategory.text = typeData?.name.getValueOrDefaultIsEmpty()
            }
        }
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