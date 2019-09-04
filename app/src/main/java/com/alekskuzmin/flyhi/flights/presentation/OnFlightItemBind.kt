package com.alekskuzmin.flyhi.flights.presentation

import com.alekskuzmin.flyhi.BR
import com.alekskuzmin.flyhi.R
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

class OnFlightItemBind : OnItemBind<FlightItem> {
    override fun onItemBind(itemBinding: ItemBinding<*>, position: Int, item: FlightItem?) {
        itemBinding.set(BR.item, R.layout.item_flight)
    }

}
