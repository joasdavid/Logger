package com.joasvpereira.loggger.contract


interface LogContract {
    fun log(tag : String, message : String)
}

interface DebugLogContract : LogContract

interface ErrorLogContract : LogContract

interface InfoLogContract : LogContract

interface VerboseLogContract : LogContract

interface WarnLogContract : LogContract