package com.alekskuzmin.flyhi.core.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.alekskuzmin.flyhi.R
import com.alekskuzmin.flyhi.core.viewmodel.FlyHiViewModel
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


abstract class BaseFragment<VIEWMODEL : FlyHiViewModel, VIEWBINDING : ViewDataBinding> :
    DataBindingFragment<VIEWMODEL, VIEWBINDING>() {

    protected abstract val toolbarResId: Int
    protected abstract val toolbarTitle: CharSequence
    protected open val toolbarTitleGravity = Gravity.CENTER
    protected open val toolbarShowBackButton = true
    private val dialogController: DialogController by inject { parametersOf(this@BaseFragment) }

    private var toolbar: Toolbar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Toolbar>(toolbarResId)?.run {
            toolbar = this
            setupToolbar(toolbarShowBackButton)
        }

        getViewModel().getRenderedState().observe(this, Observer { flightsViewState ->
            flightsViewState.error?.let {
                dialogController.showMessage(
                    getString(R.string.general_error_dialog_title),
                    it,
                    getString(R.string.general_error_dialog_button),
                    DialogInterface.OnClickListener { _: DialogInterface, _: Int ->
                        getViewModel().retryLastAction()
                    },
                    DialogInterface.OnCancelListener {
                        getViewModel().resetAppState()
                        findNavController().popBackStack()
                    }
                )
            }
        })
    }

    private fun setupToolbar(toolbarShowBackButton: Boolean) {

        if (this.toolbar == null) return
        val toolbar: Toolbar = this.toolbar!!

        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)

        val params = title?.layoutParams as Toolbar.LayoutParams
        params.gravity = toolbarTitleGravity
        title.layoutParams = params

        val mainActivity = activity as MainActivity
        mainActivity.setupToolbar(toolbar) {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(toolbarShowBackButton)
            title.text = toolbarTitle
        }
    }

}