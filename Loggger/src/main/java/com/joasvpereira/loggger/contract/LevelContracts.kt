package com.joasvpereira.loggger.contract


interface LevelContract {
    fun log(tag : String, message : String)
}

interface DebugLevelContract : LevelContract

interface ErrorLogContract : LevelContract

interface InfoLogContract : LevelContract

interface VerboseLogContract : LevelContract

interface WarnLogContract : LevelContract