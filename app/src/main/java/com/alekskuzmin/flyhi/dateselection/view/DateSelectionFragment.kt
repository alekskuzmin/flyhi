package com.alekskuzmin.flyhi.dateselection.view


import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.findNavController
import com.alekskuzmin.flyhi.R
import com.alekskuzmin.flyhi.core.view.BaseFragment
import com.alekskuzmin.flyhi.databinding.FragmentDateSelectionBinding
import com.alekskuzmin.flyhi.dateselection.presentation.DateSelectionVm
import kotlinx.android.synthetic.main.fragment_date_selection.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DateSelectionFragment : BaseFragment<DateSelectionVm, FragmentDateSelectionBinding>() {

    private val dateSelectionVm by viewModel<DateSelectionVm>()

    override val toolbarResId: Int
        get() = R.id.date_selection_toolbar
    override val toolbarTitle: CharSequence
        get() = "Select Date"
    override val layoutResource: Int
        get() = R.layout.fragment_date_selection
    override val toolbarShowBackButton: Boolean
        get() = false

    override fun bindViewModel(
        viewBinding: FragmentDateSelectionBinding,
        viewModel: DateSelectionVm
    ) {
        viewBinding.view = this
        viewBinding.vm = viewModel
    }

    override fun getViewModel(): DateSelectionVm {
        return dateSelectionVm
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calendar = Calendar.getInstance()
        date_picker.minDate = calendar.time.time

        date_picker.init(
            dateSelectionVm.getRenderedState().value?.selectedDate?.year
                ?: calendar.get(Calendar.YEAR),
            dateSelectionVm.getRenderedState().value?.selectedDate?.month
                ?: calendar.get(Calendar.MONTH),
            dateSelectionVm.getRenderedState().value?.selectedDate?.day
                ?: calendar.get(Calendar.DAY_OF_MONTH)
            , null
        )

        val yearSpinnerId =
            Resources.getSystem().getIdentifier("date_picker_header_year", "id", "android")
        val year = date_picker.findViewById<TextView>(yearSpinnerId)
        val headerTextId =
            Resources.getSystem().getIdentifier("date_picker_header_date", "id", "android")
        val selectedDate = date_picker.findViewById<TextView>(headerTextId)
        year.setTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorPrimaryDark,
                context?.theme
            )
        )
        selectedDate.setTextColor(
            ResourcesCompat.getColor(
                resources,
                R.color.colorPrimaryDark,
                context?.theme
            )
        )
    }

    fun navigateToFlights() {
        dateSelectionVm.onDateSelected(
            date_picker.year,
            date_picker.month + 1,
            date_picker.dayOfMonth
        )
        findNavController().navigate(R.id.availableFlightsFragment)
    }
}
