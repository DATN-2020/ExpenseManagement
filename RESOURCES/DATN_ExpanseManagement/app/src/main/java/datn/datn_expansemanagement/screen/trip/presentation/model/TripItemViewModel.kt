package datn.datn_expansemanagement.screen.trip.presentation.model

import com.github.vivchar.rendererrecyclerviewadapter.ViewModel

class TripItemViewModel(
    var name: String,
    var isFinished: Boolean = false
) : ViewModel