package com.alekskuzmin.flyhi.flights.view

import android.os.Bundle
import android.view.View
import com.alekskuzmin.flyhi.BR
import com.alekskuzmin.flyhi.R
import com.alekskuzmin.flyhi.core.utils.IntentManager
import com.alekskuzmin.flyhi.core.view.BaseFragment
import com.alekskuzmin.flyhi.databinding.FragmentAvailableFlightsBinding
import com.alekskuzmin.flyhi.flights.presentation.AvailableFlightsVm
import com.alekskuzmin.flyhi.flights.presentation.FlightItem
import com.alekskuzmin.flyhi.flights.presentation.OnFlightClickListener
import com.alekskuzmin.flyhi.flights.presentation.OnFlightItemBind
import kotlinx.android.synthetic.main.fragment_available_flights.*
import me.tatarka.bindingcollectionadapter2.ItemBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AvailableFlightsFragment :
    BaseFragment<AvailableFlightsVm, FragmentAvailableFlightsBinding>(), OnFlightClickListener {

    private val intentManager: IntentManager by inject { parametersOf(this) }

    override fun onItemCLick(flightItem: FlightItem) {
        intentManager.openInBrowser(flightItem.bookingUrl)
    }

    override val toolbarResId: Int
        get() = R.id.flights_toolbar
    override val toolbarTitle: CharSequence
        get() = getString(R.string.available_flights_title)
    override val layoutResource: Int
        get() = R.layout.fragment_available_flights

    private val availableFlightsVm by viewModel<AvailableFlightsVm>()
    var flightsItemBinding: ItemBinding<FlightItem> = ItemBinding.of(OnFlightItemBind()).bindExtra(
        BR.listener, this
    )

    override fun bindViewModel(
        viewBinding: FragmentAvailableFlightsBinding,
        viewModel: AvailableFlightsVm
    ) {
        viewBinding.view = this
        viewBinding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.pageMargin = resources.getDimensionPixelOffset(R.dimen.dimen_default) * 2
    }

    override fun getViewModel(): AvailableFlightsVm {
        return availableFlightsVm
    }

}
