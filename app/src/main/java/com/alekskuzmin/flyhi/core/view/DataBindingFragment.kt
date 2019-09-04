package com.alekskuzmin.flyhi.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.alekskuzmin.flyhi.core.utils.AutoClearedValue
import com.alekskuzmin.flyhi.core.viewmodel.FlyHiViewModel

abstract class DataBindingFragment<VIEWMODEL : FlyHiViewModel, VIEWBINDING : ViewDataBinding> :
    Fragment() {

    private lateinit var binding: AutoClearedValue<VIEWBINDING>

    protected abstract val layoutResource: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val dataBinding: VIEWBINDING =
            DataBindingUtil.inflate(inflater, layoutResource, container, false)
        dataBinding.lifecycleOwner = this
        binding = AutoClearedValue(this, dataBinding)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = getViewModel()

        val binding = binding.get() ?: throw IllegalStateException("Binding was released")
        bindViewModel(binding, viewModel)
    }

    abstract fun bindViewModel(viewBinding: VIEWBINDING, viewModel: VIEWMODEL)

    abstract fun getViewModel(): VIEWMODEL

    protected fun getViewBinding(): VIEWBINDING {
        val view = view ?: throw IllegalStateException("Fragment was released")
        return DataBindingUtil.getBinding(view) ?: throw IllegalStateException("No binding")
    }

}