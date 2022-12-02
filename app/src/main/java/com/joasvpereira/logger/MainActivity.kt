package com.joasvpereira.logger

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.joasvpereira.loggger.Logger
import com.joasvpereira.loggger.contract.DebugLevelContract
import com.joasvpereira.loggger.extentions.logThis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Logger.apply {
            defaultLevel = Logger.Level.INFO
            defaultTag = "LoggerTest"
            debugLog = object : DebugLevelContract {
                override fun log(tag: String, message: String) {
                    Log.d(tag, "DEBUG MSG: [$message]")
                }
            }
        }

        Logger.levelsState(false, listOf(Logger.Level.VERBOSE))
        "--------------------------------------------------------------------".logThis()
        "------------------------------ Log Function ------------------------".logThis()
        "--------------------------------------------------------------------".logThis()
        Logger.log("Log 1")
        Logger.log(tag = "LoggerTest2", "Log 2")
        Logger.log(level = Logger.Level.ERROR, "Log 3")
        Logger.log(level = Logger.Level.ERROR, tag = "LoggerTest2", "Log 4")


        "--------------------------------------------------------------------".logThis()
        "------------------------------ Log Extensions ----------------------".logThis()
        "--------------------------------------------------------------------".logThis()
        // EXT
        "EXT 1".logThis()
        "EXT 2".logThis { "-> $it" }
        "EXT 3".logThis(tag = "LoggerTest2")
        "EXT 4".logThis(tag = "LoggerTest2") { "-> $it" }
        "EXT 5".logThis(tag = "LoggerTest2", Logger.Level.DEBUG)
        "EXT 6".logThis(tag = "LoggerTest2", Logger.Level.DEBUG) { "-> $it" }
        "EXT 7".logThis(level = Logger.Level.DEBUG)
        "EXT 8".logThis(level = Logger.Level.DEBUG) { "-> $it" }
    }
}