package com.farmer.open9527.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import java.lang.Exception
import java.lang.NullPointerException
import java.lang.ref.WeakReference
import java.util.ArrayList


/**
 *@author   open_9527
 *Create at 2021/11/3
 **/
object DialogUtils {


    private val HANDLER = Handler(Looper.getMainLooper())

    fun runOnUiThread(runnable: Runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run()
        } else {
            HANDLER.post(runnable)
        }
    }


    fun getFragmentActivity(context: Context): FragmentActivity? {
        val activity = getActivityByContext(context)
        if (activity != null) {
            if (activity is FragmentActivity) {
                return activity
            }
        }
        throw NullPointerException("context not instance FragmentActivity!")
    }

    /**
     * Return the activity by context.
     *
     * @param context The context.
     * @return the activity by context.
     */
    private fun getActivityByContext(context: Context): Activity? {
        val activity = getActivityByContextInner(context)
        return if (!isActivityAlive(activity)) null else activity
    }

    private fun getActivityByContextInner( context: Context): Activity? {
        var context: Context? = context
        val list: MutableList<Context> = ArrayList()
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            val activity = getActivityFromDecorContext(context)
            if (activity != null) return activity
            list.add(context)
            context = context.baseContext
            if (context == null) {
                return null
            }
            if (list.contains(context)) {
                // loop context
                return null
            }
        }
        return null
    }

    private fun getActivityFromDecorContext(context: Context?): Activity? {
        if (context == null) return null
        if ("com.android.internal.policy.DecorContext" == context.javaClass.name) {
            try {
                val mActivityContextField = context.javaClass.getDeclaredField("mActivityContext")
                mActivityContextField.isAccessible = true
                return (mActivityContextField[context] as WeakReference<Activity?>).get()
            } catch (ignore: Exception) {
            }
        }
        return null
    }

    /**
     * Return whether the activity is alive.
     *
     * @param activity The activity.
     * @return `true`: yes<br></br>`false`: no
     */
    @SuppressLint("ObsoleteSdkInt")
    fun isActivityAlive(activity: Activity?): Boolean {
        return (activity != null && !activity.isFinishing
                && (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1 || !activity.isDestroyed))
    }

    /**
     * Return whether the activity is alive.
     *
     * @param context The context.
     * @return `true`: yes<br></br>`false`: no
     */
    fun isActivityAlive(context: Context): Boolean {
        return isActivityAlive(getActivityByContext(context))
    }


    private fun hideSoftInput(activity: Activity, view: View) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


     fun closeSoftInput(activity: Activity, dialog: Dialog?) {
         val view = dialog?.currentFocus
         if (view is TextView) {
             hideSoftInput(activity, view)
         }
     }

     fun bindingVariable(binding: ViewDataBinding, bindingParams: SparseArray<*>) {
        var i = 0
        val length = bindingParams.size()
        while (i < length) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i))
            i++
        }
    }

}