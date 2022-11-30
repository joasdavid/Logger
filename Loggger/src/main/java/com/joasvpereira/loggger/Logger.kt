package com.joasvpereira.loggger

import com.joasvpereira.loggger.contract.DefaultDebugLog
import com.joasvpereira.loggger.contract.DefaultErrorLog
import com.joasvpereira.loggger.contract.DefaultInfoLog
import com.joasvpereira.loggger.contract.DefaultVerboseLog
import com.joasvpereira.loggger.contract.DefaultWarnLog
import com.joasvpereira.loggger.contract.LevelContract

object Logger {

    private const val DEFAULT_TAG = "Logger"

    enum class Level {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    var tag = DEFAULT_TAG
    private set

    var defaultLevel = Level.VERBOSE
    private set

    private var debugLog = DefaultDebugLog()
    private var errorLog = DefaultErrorLog()
    private var infoLog = DefaultInfoLog()
    private var verboseLog = DefaultVerboseLog()
    private var warnLog = DefaultWarnLog()

    private val levelsState = HashMap<Level, Boolean>()

    init {
        levelsState(
            isEnabled = true,
            listOflevels = Level.values().toList()
        )
    }

    fun config(
        defaultTag: String = DEFAULT_TAG,
        defaultLevel: Level = Logger.defaultLevel,
        debugLog: DefaultDebugLog = Logger.debugLog,
        errorLog: DefaultErrorLog = Logger.errorLog,
        infoLog: DefaultInfoLog = Logger.infoLog,
        verboseLog: DefaultVerboseLog = Logger.verboseLog,
        warnLog: DefaultWarnLog = Logger.warnLog,
    ) {
        tag = defaultTag
        Logger.defaultLevel = defaultLevel
        Logger.debugLog = debugLog
        Logger.errorLog = errorLog
        Logger.infoLog = infoLog
        Logger.verboseLog = verboseLog
        Logger.warnLog = warnLog
    }

    fun log(message: String) {
        getLogger(defaultLevel)?.log(tag, message)
    }

    fun log(tag: String = this.tag, message: String) {
        getLogger(defaultLevel)?.log(tag, message)
    }

    fun log(level: Level, tag: String, message: String) {
        getLogger(level)?.log(tag, message)
    }

    private fun getLogger(forLevel: Level): LevelContract? =
        if (levelsState[forLevel] == true) {
            when (forLevel) {
                Level.DEBUG -> debugLog
                Level.ERROR -> errorLog
                Level.INFO -> infoLog
                Level.VERBOSE -> verboseLog
                Level.WARN -> warnLog
            }
        } else {
            null
        }

    fun levelsState(
        isEnabled: Boolean,
        listOflevels: Iterable<Level> = listOf(
            Level.DEBUG,
            Level.ERROR,
            Level.INFO,
            Level.VERBOSE,
            Level.WARN
        )
    ) {
        listOflevels.forEach {
            levelsState[it] =isEnabled
        }
    }
}