package org.caojun.rcn.ormlite

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable
import java.io.Serializable

@DatabaseTable
class Diary: Serializable {

    @DatabaseField(id = true)
    var day: String? = null//yyyyMMdd
    @DatabaseField
    var cntName: Byte = 0//取名次数

    constructor() {}

    constructor(day: String, cntName: Byte) {
        this.day = day
        this.cntName = cntName
    }
}