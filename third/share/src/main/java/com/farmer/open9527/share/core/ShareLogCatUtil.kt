package com.farmer.open9527.share.core

import android.text.TextUtils
import android.util.Log
import java.util.logging.Logger

object ShareLogCatUtil {

    var DEBUG = false
    private var DESC = ""

    fun setLogCatInfo(logEnable: Boolean, descString: String?) {
        DEBUG = logEnable
        DESC = if (TextUtils.isEmpty(descString)) "ShareLogCatUtil" else descString.toString()
    }

    fun i(tag: String, msg: String) {
        if (DEBUG) {
            Log.i(tag, logStartFormat() + getFileLineMethod() + logMsgFormat(msg) + logEndFormat())
        }
    }

    fun d(tag: String, msg: String) {
        if (DEBUG) {
            Log.d(tag, logStartFormat() + getFileLineMethod() + logMsgFormat(msg) + logEndFormat())
        }
    }


    fun w(tag: String, msg: String) {
        if (DEBUG) {
            Log.w(tag, logStartFormat() + getFileLineMethod() + logMsgFormat(msg) + logEndFormat())
        }
    }

    fun e(tag: String, msg: String) {
        if (DEBUG) {
            Log.e(tag, logStartFormat() + getFileLineMethod() + logMsgFormat(msg) + logEndFormat())
        }
    }


    private fun getFileLineMethod(): String {
        val traceElement = Exception().stackTrace[2]
        val toStringBuffer = StringBuffer()
            .append("│").append(traceElement.className)
            .append(".").append(traceElement.methodName)
            .append("(").append(traceElement.fileName).append(":").append(traceElement.lineNumber).append(")")
            .append("\n").append(logLineFormat()).append("\n")
            .append("│args[0] = ").append(traceElement.methodName).append("\n")
            .append("│args[1] = ")
        return toStringBuffer.toString()
    }


    private fun ignorable(stackTraceElement: StackTraceElement): Boolean {
        return stackTraceElement.isNativeMethod || stackTraceElement.className == Thread::class.java.name || stackTraceElement.className == Logger::class.java.getName()
    }

    private fun logStartFormat(): String {
        return "   \n┌───────────────────────$DESC─────────────────────────────────────────────────────────────────────────────────────────\n"
    }

    private fun logMsgFormat(msg: String): String {
        return msg
    }

    private fun logEndFormat(): String {
        return "\n└───────────────────────$DESC─────────────────────────────────────────────────────────────────────────────────────────\n"
    }

    private fun logLineFormat(): String {
        return "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄"
    }
}