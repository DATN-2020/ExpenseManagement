package datn.datn_expansemanagement.screen.account.item_account.data

import datn.datn_expansemanagement.core.event.EventBusData

class OnReportWalletDataBus(
    var idWallet: Int,
    var isCard: Boolean = false
) : EventBusData