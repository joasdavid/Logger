package com.joasvpereira.loggger.extentions

import com.joasvpereira.loggger.Logger

fun <T> T.logThis(): T = this.also {
    Logger.log(it.toString())
}

fun <T> T.logThis(
    tag: String = Logger.defaultTag,
    level: Logger.Level = Logger.defaultLevel,
    func: (T) -> String
): T = this.also {
    val msgToLog = func(it)
    Logger.log(level, tag, msgToLog)
}

fun <T> T.logThis(
    tag: String = Logger.defaultTag,
    level: Logger.Level = Logger.defaultLevel
): T = this.logThis(tag, level) {
    it.toString()
}
