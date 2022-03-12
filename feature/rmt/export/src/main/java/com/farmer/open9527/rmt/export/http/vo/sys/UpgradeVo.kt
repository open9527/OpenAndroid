package com.farmer.open9527.rmt.export.http.vo.sys

import java.io.Serializable

class UpgradeVo : Serializable {
    var type: String? = null
    var tips: String? = null
    var appVersion: VersionVo? = null
    var downloadUrl: String? = null

    companion object {
        private const val TYPE_IGNORE = "ignore"
        private const val TYPE_RECOMMEND = "recommend"
        private const val TYPE_NECESSARY = "necessary"

        fun isIgnore(upgrade: UpgradeVo?): Boolean {
            return upgrade != null && TYPE_IGNORE == upgrade.type
        }

        fun isRecommend(upgrade: UpgradeVo?): Boolean {
            return upgrade != null && TYPE_RECOMMEND == upgrade.type
        }

        fun isNecessary(upgrade: UpgradeVo?): Boolean {
            return upgrade != null && TYPE_NECESSARY == upgrade.type
        }
    }
}