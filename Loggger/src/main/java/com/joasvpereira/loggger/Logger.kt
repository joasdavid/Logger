package com.joasvpereira.loggger

import com.joasvpereira.loggger.contract.DefaultDebugLog
import com.joasvpereira.loggger.contract.DefaultErrorLog
import com.joasvpereira.loggger.contract.DefaultInfoLog
import com.joasvpereira.loggger.contract.DefaultVerboseLog
import com.joasvpereira.loggger.contract.DefaultWarnLog
import com.joasvpereira.loggger.contract.LogContract

object Logger {

    private const val DEFAULT_TAG = "Logger"

    enum class LoggerChannel {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    var tag = DEFAULT_TAG
    private set

    var defaultChannel = LoggerChannel.VERBOSE
    private set

    private var debugLog = DefaultDebugLog()
    private var errorLog = DefaultErrorLog()
    private var infoLog = DefaultInfoLog()
    private var verboseLog = DefaultVerboseLog()
    private var warnLog = DefaultWarnLog()

    private val channelsState = HashMap<LoggerChannel, Boolean>()

    init {
        channelsState(
            isEnabled = true,
            listOfChannels = LoggerChannel.values().toList()
        )
    }

    fun config(
        defaultTag: String = DEFAULT_TAG,
        defaultChannel: LoggerChannel = Logger.defaultChannel,
        debugLog: DefaultDebugLog = Logger.debugLog,
        errorLog: DefaultErrorLog = Logger.errorLog,
        infoLog: DefaultInfoLog = Logger.infoLog,
        verboseLog: DefaultVerboseLog = Logger.verboseLog,
        warnLog: DefaultWarnLog = Logger.warnLog,
    ) {
        tag = defaultTag
        Logger.defaultChannel = defaultChannel
        Logger.debugLog = debugLog
        Logger.errorLog = errorLog
        Logger.infoLog = infoLog
        Logger.verboseLog = verboseLog
        Logger.warnLog = warnLog
    }

    fun log(tag: String, message: String) {
        getLogger(defaultChannel)?.log(tag, message)
    }

    fun log(channel: LoggerChannel, tag: String, message: String) {
        getLogger(channel)?.log(tag, message)
    }

    private fun getLogger(forChannel: LoggerChannel): LogContract? =
        if (channelsState[forChannel] == true) {
            when (forChannel) {
                LoggerChannel.DEBUG -> debugLog
                LoggerChannel.ERROR -> errorLog
                LoggerChannel.INFO -> infoLog
                LoggerChannel.VERBOSE -> verboseLog
                LoggerChannel.WARN -> warnLog
            }
        } else {
            null
        }

    fun channelsState(
        isEnabled: Boolean,
        listOfChannels: Iterable<LoggerChannel> = listOf(
            LoggerChannel.DEBUG,
            LoggerChannel.ERROR,
            LoggerChannel.INFO,
            LoggerChannel.VERBOSE,
            LoggerChannel.WARN
        )
    ) {
        listOfChannels.forEach {
            channelsState[it] =isEnabled
        }
    }
}