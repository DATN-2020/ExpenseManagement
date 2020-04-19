package datn.datn_expansemanagement.screen.test.presentation.renderer

import android.content.Context
import android.view.View
import datn.datn_expansemanagement.R
import datn.datn_expansemanagement.core.app.util.image.GlideImageHelper
import datn.datn_expansemanagement.core.base.presentation.mvp.android.model.ViewRenderer
import datn.datn_expansemanagement.screen.test.presentation.model.TestModel
import kotlinx.android.synthetic.main.item_test_reponse.view.*

class TestRenderer (context: Context): ViewRenderer<TestModel>(context){
    override fun getLayoutId(): Int {
        return R.layout.item_test_reponse
    }

    override fun getModelClass(): Class<TestModel> = TestModel::class.java

    override fun bindView(model: TestModel, viewRoot: View) {
        //GlideImageHelper(context).loadThumbnail(viewRoot.imgTest, model.imgUrl, R.drawable.ic_account_circle)
        viewRoot.tvTest.text = model.name
    }


}