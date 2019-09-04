package com.alekskuzmin.flyhi.core.injection

import androidx.fragment.app.Fragment
import com.alekskuzmin.flyhi.core.utils.IntentManager
import com.alekskuzmin.flyhi.core.view.DialogController
import com.alekskuzmin.flyhi.core.view.DialogControllerImpl
import com.alekskuzmin.flyhi.core.view.IntentManagerImpl
import org.koin.dsl.module

val uiModule = module {
    factory { (fragment: Fragment) -> DialogControllerImpl(fragment) as DialogController }
    factory { (fragment: Fragment) -> IntentManagerImpl(fragment) as IntentManager }
}