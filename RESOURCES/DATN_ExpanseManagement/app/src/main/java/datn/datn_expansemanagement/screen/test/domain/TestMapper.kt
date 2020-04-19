package datn.datn_expansemanagement.screen.test.domain

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel
import datn.datn_expansemanagement.core.base.domain.mapper.Mapper
import datn.datn_expansemanagement.domain.response.TestReponse
import datn.datn_expansemanagement.kotlinex.number.getValueOrDefaultIsZero
import datn.datn_expansemanagement.kotlinex.string.getValueOrDefaultIsEmpty
import datn.datn_expansemanagement.screen.test.presentation.model.TestModel

class TestMapper : Mapper<TestReponse, MutableList<ViewModel>>{
    override fun map(input: TestReponse): MutableList<ViewModel> {
        val listReturn = mutableListOf<ViewModel>()
        if(!input.list.isNullOrEmpty()){
            input.list.forEach {
                listReturn.add(TestModel(
                    id = it.id.getValueOrDefaultIsZero(),
                    name = it.name.getValueOrDefaultIsEmpty(),
                    imgUrl = it.color.getValueOrDefaultIsEmpty()
                ))
            }
        }
        return listReturn
    }

}