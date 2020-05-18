package datn.datn_expansemanagement.core.app.change_screen

interface Request {
    companion object{
        val REQUEST_CODE_CATEGORY: Int
            get() = 1
        val REQUEST_CODE_ADD_CATEGORY: Int
            get() = 2
        val REQUEST_CODE_TYPE_CATEGORY: Int
            get() = 3
        val REQUEST_CODE_WALLET: Int
            get() = 4
    }

}