package com.base.viewmodel

import android.arch.lifecycle.LifecycleOwner

/**
 * Created by vophamtuananh on 1/7/18.
 */
interface CommonView {

    fun showLoading()

    fun showError(throwable: Throwable, tryAgainAction: (() -> Unit)?)

    fun showError(throwable: Throwable)

    fun hideLoading()

    fun lifecycleOwner(): LifecycleOwner
}