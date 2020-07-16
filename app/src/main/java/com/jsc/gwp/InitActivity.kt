package com.jsc.gwp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsc.gwp.data.*
import com.jsc.gwp.initSetting.SetActivityGender
import com.jsc.gwp.receiver.TipAlarmReceiver
import org.jetbrains.anko.startActivity
import java.util.*

class InitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_init)



        val sharedPref =
            getSharedPreferences(
                getString(R.string.setting_nums_file_key),
                Context.MODE_PRIVATE
            )

        //이전에 저장된 값이 없으면 세팅 액티비티, 있다면 바로 본 화면
        if (sharedPref.getInt(getString(R.string.set_can_sleep_time_hour_key), 100) == 100) {
            startActivity<SetActivityGender>()
        } else {
            gender = sharedPref.getInt(getString(R.string.set_gender_key), 100)
            birthYear = sharedPref.getInt(getString(R.string.set_birth_year_key), 100)
            birthMonth = sharedPref.getInt(getString(R.string.set_birth_month_key), 100)
            birthDay = sharedPref.getInt(getString(R.string.set_birth_day_key), 100)
            height = sharedPref.getInt(getString(R.string.set_height_key), 100)
            curWeight = sharedPref.getInt(getString(R.string.set_current_weight_key), 100)
            desireWeight = sharedPref.getInt(getString(R.string.set_desire_weight_key), 100)
            actionTimeInAWeek =
                sharedPref.getFloat(getString(R.string.set_action_time_in_week_key), 100F)
            canSleepHour =
                sharedPref.getInt(getString(R.string.set_can_sleep_time_hour_key), 100)
            canSleepMin = sharedPref.getInt(getString(R.string.set_can_sleep_time_min_key), 100)

            startActivity<SetActivityAlarm>()
        }
    }
}