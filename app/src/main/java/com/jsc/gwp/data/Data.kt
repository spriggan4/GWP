package com.jsc.gwp.data

import kotlin.properties.Delegates

var actionTimeInAWeek by Delegates.notNull<Float>()
var birthYear by Delegates.notNull<Int>()
var birthMonth by Delegates.notNull<Int>()
var birthDay by Delegates.notNull<Int>()
var canSleepHour by Delegates.notNull<Int>()
var canSleepMin by Delegates.notNull<Int>()
var curWeight by Delegates.notNull<Int>()
var desireWeight by Delegates.notNull<Int>()
var gender by Delegates.notNull<Int>()
var height by Delegates.notNull<Int>()
var needCarl by Delegates.notNull<Double>()
var age by Delegates.notNull<Int>()

val CHANNELID = "GWP"