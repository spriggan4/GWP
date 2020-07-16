package com.jsc.gwp

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jsc.gwp.data.CHANNELID
import com.jsc.gwp.receiver.NeedWaterAlarmReceiver
import com.jsc.gwp.receiver.TipAlarmReceiver
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class SetActivityAlarm : AppCompatActivity() {
    private var alarmMgr: AlarmManager? = null

    private val waterTip = "충분한 수분은 각 장기의 기능을 원할하게 해서 신진대사율을 높여줍니다."
    private val exerciseTips = arrayOf(
        "꾸준히 운동하세요. 처음에는 조깅같은 운동으로 기초체력을 다진 다음 고강도 운동도 추가하세요.",
        "유산소 운동은 신진대사기능을 올려 소화율을 높여줍니다."
    )

    //하루에 6번 띄울 생각이니깐 6개
    private val waterTipAlarmRequestCodeArray = arrayOf(1, 2, 3, 4, 5, 6)
    private val exerciseTipAlarmRequestCode = 7

    private var waterTipAlarmIntentList = mutableListOf<PendingIntent?>()

    //팁은 하루에 한개만 띄울꺼니깐 하나만 설정
    private var exerciseTipAlarmIntent: PendingIntent? = null

    private var setAlarmDay = 0
    private var today = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPref = getSharedPreferences(
            getString(R.string.setting_nums_file_key),
            Context.MODE_PRIVATE
        )

        //sharedPref로 설정된 alarmToday 불러오기
        setAlarmDay = sharedPref.getInt(getString(R.string.set_alarm_today_key), 100)

        val date = Date()
        val sdf = SimpleDateFormat("dd", Locale.KOREA)
        today = sdf.format(date).toInt()

        //설정이 된적이 없다면
        if (setAlarmDay != today) {
            // API 26+에서만 NotificationChannel을 작성.
            // NotificationChannel 클래스는 예전 지원 라이브러리에 없다.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "GWP"
                val importance = NotificationManager.IMPORTANCE_HIGH
                val channel = NotificationChannel(CHANNELID, name, importance)

                // 시스템에 등록하기
                val notificationManager: NotificationManager =
                    this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                notificationManager.createNotificationChannel(channel)
            }

            alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager

            for (i in 0 until waterTipAlarmRequestCodeArray.size) {
                waterTipAlarmIntentList.add(
                    Intent(
                        this,
                        NeedWaterAlarmReceiver::class.java
                    ).let { intent ->
                        intent.putExtra("textTitle", "수분 섭취 시간이에요")
                        intent.putExtra("textContent", waterTip)
                        intent.putExtra("ID", i)
                        PendingIntent.getBroadcast(
                            this,
                            waterTipAlarmRequestCodeArray[i],
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                        )
                    })
            }
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[0], 8, 0)
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[1], 10, 0)
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[2], 13, 0)
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[3], 15, 0)
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[4], 18, 0)
            setNotificationAlarm(alarmMgr, waterTipAlarmIntentList[5], 21, 0)

            val randNum = Random.nextInt(0, exerciseTips.size)
            exerciseTipAlarmIntent = Intent(
                this,
                TipAlarmReceiver::class.java
            ).let { intent ->
                intent.putExtra("textTitle", "운동이 필요한 이유")
                intent.putExtra("textContent", exerciseTips[randNum])
                //water알림 intent과 5로 끝나기에 6 설정
                intent.putExtra("ID", 6)
                PendingIntent.getBroadcast(
                    this,
                    exerciseTipAlarmRequestCode,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            }
            setNotificationAlarm(alarmMgr, exerciseTipAlarmIntent,19,0)

            setAlarmDay = today
            with(sharedPref.edit()) {
                putInt(getString(R.string.set_alarm_today_key), setAlarmDay)
                commit()
            }

            startActivity<MainActivity>()
        }

        //이전에 설정이 된적이 있다면
        else {
            startActivity<MainActivity>()
        }
    }

    private fun setNotificationAlarm(
        alarmMgr: AlarmManager?,
        alarmIntent: PendingIntent?,
        mHour: Int,
        mMin: Int
    ) {
//    // 여기서 알림 시간 설정
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, mHour)
            set(Calendar.MINUTE, mMin)
        }

        // 이미 지난 시간을 지정했다면 다음날 같은 시간으로 설정
        if (calendar.before(Calendar.getInstance())) {
            calendar.add(Calendar.DATE, 1)
        }

        alarmMgr?.set(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            alarmIntent
        )
    }
}