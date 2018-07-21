package com.base.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment

/**
 * Created by vophamtuananh on 12/24/17.
 */

const val DELAY_TRANSITION_TIME: Long = 700
inline fun <reified T> Activity.start(clearBackStack: Boolean = false, bundle: Bundle? = null, activityOptions: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    if (clearBackStack) {
        if (activityOptions == null) {
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        } else {
            //need to delay for better UI performance in case no transition between activities
            Handler().postDelayed({ finish() }, DELAY_TRANSITION_TIME)
        }
    }
    bundle?.let {
        intent.putExtras(bundle)
    }
    ActivityCompat.startActivity(this, intent, activityOptions)
}

inline fun <reified T> Activity.startForResult(requestCode: Int, bundle: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    bundle?.let {
        intent.putExtras(bundle)
    }
    startActivityForResult(intent, requestCode)
}

inline fun <reified T : Fragment> createNewFragment(context: Context, bundle: Bundle? = null): T {
    return T::class.java.cast(Fragment.instantiate(context, T::class.java.name, bundle))
}

inline fun <reified T : Fragment> Context.newFragment(bundle: Bundle? = null): T {
    return T::class.java.cast(Fragment.instantiate(this, T::class.java.name, bundle))
}

inline fun <reified T : Activity> Fragment.getOwnActivity(): T? {
    activity ?: return null
    return T::class.java.cast(activity)
}
