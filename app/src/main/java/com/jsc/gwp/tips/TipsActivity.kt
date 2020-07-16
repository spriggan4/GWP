package com.jsc.gwp.tips

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsc.gwp.R
import kotlinx.android.synthetic.main.activity_tips.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class TipsActivity : AppCompatActivity() {
    val tips = arrayOf(
        "11시 이전에 잠드세요",
        "유산소 운동도 중요합니다",
        "식사일기를 쓰세요.",
        "음식을 여러번 드세요",
        "과식을 피하세요"
    )
    val reasons = arrayOf(
        "수면의 질을 높여주는 호르몬인 멜라토닌은 11시에서 2시 사이에 활발하게 분비됩니다.",
        "유산소 운동은 신진대사기능을 올려 소화율을 높여줍니다.",
        "마른 사람은 자신의 생각보다 적게 먹는 경우가 많습니다. 자신의 식단을 체크해 보세요",
        "소화력이 약하기 때문에 한번에 많이 먹기 보다는 나눠서 드시는게 좋습니다.",
        "조급함으로 이뤄지는 일은 없습니다. 식사일기를 계획을 세워 천천히 나간다면 반드시 이뤄집니다."
    )

    private var setTipDay = 0
    private var today = 0
    private var randNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)
        val sharedPref = getSharedPreferences(
            getString(R.string.setting_nums_file_key),
            Context.MODE_PRIVATE
        )

        //sharedPref로 설정된 alarmToday 불러오기
        setTipDay = sharedPref.getInt(getString(R.string.set_tip_today_key), 100)

        val date = Date()
        val sdf = SimpleDateFormat("dd", Locale.KOREA)
        today = sdf.format(date).toInt()

        if (setTipDay != today) {
            randNum = Random.nextInt(0, tips.size)
            setTipDay = today
            with(sharedPref.edit()) {
                putInt(getString(R.string.set_tip_today_key), setTipDay)
                putInt(getString(R.string.set_tip_rand_num_key), randNum)
                commit()
            }
        } else {
            randNum=sharedPref.getInt(getString(R.string.set_tip_rand_num_key),0)
        }

        tvTip.text = tips[randNum]
        tvReason.text = reasons[randNum]
    }
}