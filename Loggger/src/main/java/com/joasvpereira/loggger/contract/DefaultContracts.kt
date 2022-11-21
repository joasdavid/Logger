package com.joasvpereira.loggger.contract

import android.util.Log

class DefaultDebugLog() : DebugLogContract {
    override fun log(tag: String, message: String) {
        Log.d(tag, message)
    }
}

class DefaultErrorLog() : ErrorLogContract {
    override fun log(tag: String, message: String) {
        Log.e(tag, message)
    }
}

class DefaultInfoLog() : InfoLogContract {
    override fun log(tag: String, message: String) {
        Log.i(tag, message)
    }
}

class DefaultVerboseLog() : VerboseLogContract {
    override fun log(tag: String, message: String) {
        Log.v(tag, message)
    }
}

class DefaultWarnLog() : WarnLogContract {
    override fun log(tag: String, message: String) {
        Log.w(tag, message)
    }
}