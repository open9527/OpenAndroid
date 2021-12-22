package com.farmer.open9527.demo.starter

import java.util.HashMap

class StarterManager {

    private var serviceStarterMap: HashMap<String, BaseStarter> = HashMap()

    constructor()

    companion object {

        private var INSTANCE: StarterManager? = null

        fun instance(): StarterManager? {
            if (INSTANCE == null) {
                INSTANCE = StarterManager()
            }
            return INSTANCE
        }
    }


    fun ready(): Boolean {
        return serviceStarterMap.isNotEmpty()
    }


    fun register(starter: BaseStarter) {
        for (service in starter.supportService()) {
            serviceStarterMap[service]=starter
        }
    }

    fun find(service: String?): BaseStarter? {
        return serviceStarterMap[service]
    }

//    fun unRegister(starter: BaseStarter) {
//        serviceStarterMap.remove(starter.supportService())
//    }

    fun clear(starter: BaseStarter) {
        serviceStarterMap.clear()
    }

}