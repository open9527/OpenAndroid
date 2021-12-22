package com.farmer.open9527.demo.starter

abstract class BaseStarter {

    companion object {
        const val APP_SCHEMA = "app://"
    }

    abstract fun supportService(): List<String>

    open fun name(serviceName: String): String {
        return javaClass.simpleName
    }

    open fun usage(serviceName: String): String {
        return ""
    }

    open fun validate(serviceName: String, queryParams: Map<String, String>): Boolean {
        return true
    }

    open fun authorizeRequired(serviceName: String): Boolean {
        return false
    }

    abstract fun startActivity(serviceName: String, queryParams: Map<String, String>?)
}