package com.joasvpereira.loggger

import com.joasvpereira.loggger.contract.DebugLevelContract
import com.joasvpereira.loggger.contract.DefaultDebugLog
import com.joasvpereira.loggger.contract.DefaultErrorLog
import com.joasvpereira.loggger.contract.DefaultInfoLog
import com.joasvpereira.loggger.contract.DefaultVerboseLog
import com.joasvpereira.loggger.contract.DefaultWarnLog
import com.joasvpereira.loggger.contract.ErrorLogContract
import com.joasvpereira.loggger.contract.InfoLogContract
import com.joasvpereira.loggger.contract.LevelContract
import com.joasvpereira.loggger.contract.VerboseLogContract
import com.joasvpereira.loggger.contract.WarnLogContract

object Logger {

    private const val DEFAULT_TAG = "Logger"

    enum class Level {
        DEBUG,
        ERROR,
        INFO,
        VERBOSE,
        WARN
    }

    // Tag to be used by default when logging
    var defaultTag = DEFAULT_TAG
    // Leve of log to be used by default
    var defaultLevel = Level.VERBOSE

    // Provide an implementation of logging to the level debug
    var debugLog : DebugLevelContract = DefaultDebugLog()
    // Provide an implementation of logging to the level error
    var errorLog : ErrorLogContract = DefaultErrorLog()
    // Provide an implementation of logging to the level info
    var infoLog : InfoLogContract = DefaultInfoLog()
    // Provide an implementation of logging to the level verbose
    var verboseLog : VerboseLogContract = DefaultVerboseLog()
    // Provide an implementation of logging to the level warning
    var warnLog : WarnLogContract = DefaultWarnLog()

    // Hold the state of each level of logging
    private val levelsState = HashMap<Level, Boolean>()

    /**
     * Initialise the Logger levels states
     */
    init {
        levelsState(
            isEnabled = true,
            listOfLevels = Level.values().toList()
        )
    }

    /**
     * Will log the String provided by parameter with the default tag and level.
     * @param message String to be logged
     */
    fun log(message: String) {
        getLogger(defaultLevel)?.log(defaultTag, message)
    }

    /**
     * Will log the String with the tag provided by parameters on the default level.
     * @param tag
     * @param message
     */
    fun log(tag: String = this.defaultTag, message: String) {
        getLogger(defaultLevel)?.log(tag, message)
    }

    /**
     * Will log the String with the level provided by parameters with the default tag.
     * @param level
     * @param message
     */
    fun log(level: Level, message: String) {
        getLogger(level)?.log(defaultTag, message)
    }

    /**
     * Will log the String with the level and tag provided by parameters.
     * @param level
     * @param message
     */
    fun log(level: Level, tag: String, message: String) {
        getLogger(level)?.log(tag, message)
    }

    /**
     * Will return LevelContract for a the level passed as parameter
     */
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

    /**
     * Will update the state of the levels based on the values passed as parameters.</br>
     * If <b>listOfLevels</b> is null the state will be applied to all levels.
     *
     * @param isEnabled Define state as enable or disabled.
     * @param listOfLevels Iterable object of the levels that the state should be set.
     */
    fun levelsState(
        isEnabled: Boolean,
        listOfLevels: Iterable<Level>? = listOf(
            Level.DEBUG,
            Level.ERROR,
            Level.INFO,
            Level.VERBOSE,
            Level.WARN
        )
    ) {
        listOfLevels?.forEach {
            levelsState[it] =isEnabled
        } ?: levelsState(
            isEnabled = isEnabled,
            listOfLevels = Level.values().toList()
        )
    }
}