package datn.datn_expansemanagement.screen.add_expanse.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class AddExpenseViewModel (
    var type: Type,
    var isChoose: Boolean = false
): ViewModel{
    enum class Type(id: Int){
        DONATE(1), RECEIVE(2)
    }
}