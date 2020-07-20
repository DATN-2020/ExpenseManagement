package datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.presentation

import datn.datn_expansemanagement.domain.GetDataService
import datn.datn_expansemanagement.domain.RetrofitClientInstance
import datn.datn_expansemanagement.domain.response.TypeWalletResponse
import datn.datn_expansemanagement.screen.add_wallet.add_wallet_saving.domain.SavingWalletMapper
import datn.datn_expansemanagement.screen.add_wallet.presentation.AddWalletResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SavingWalletPresenter : SavingWalletContact.Presenter(){
    private val mResource  = AddWalletResource()
    override fun getData() {
        view?.showData(SavingWalletMapper(mResource).map(""))
    }

    override fun getListBank() {
        view?.showListBank(mutableListOf())
    }

    override fun createWalletSaving() {

    }
}