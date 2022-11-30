package com.joasvpereira.loggger.extentions

import com.joasvpereira.loggger.Logger

fun <T> T.logThis(
    tag: String = Logger.tag,
    func: (T) -> String
): T = this.also {
    val msgToLog = func(it)
    Logger.log(tag, msgToLog)
}

fun <T> T.logThis(
    tag: String = Logger.tag,
    channel: Logger.Level = Logger.defaultChannel,
    func: (T) -> String
): T = this.also {
    val msgToLog = func(it)
    Logger.log(channel, tag, msgToLog)
}

fun <T> T.logThis(
    tag: String = Logger.tag
): T = this.logThis(tag = tag) {
    it.toString()
}