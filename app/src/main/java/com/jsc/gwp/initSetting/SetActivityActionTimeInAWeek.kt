package com.jsc.gwp.initSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsc.gwp.R
import com.jsc.gwp.data.actionTimeInAWeek
import kotlinx.android.synthetic.main.activity_set_action_time_in_a_week.*
import org.jetbrains.anko.startActivity

class SetActivityActionTimeInAWeek : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_action_time_in_a_week)

        //활동량 적음 1.2, 활동량 보통 1.3, 활동량 많음 1.4, 활동량 매우 많음 1.5
        lowActionTimeBtn.setOnClickListener {
            setActionTimeInAWeekAndStartSetEatPerDayAndCanSleepTimeActivity(1.2F)
        }

        normalActionTimeBtn.setOnClickListener {
            setActionTimeInAWeekAndStartSetEatPerDayAndCanSleepTimeActivity(1.3F)
        }

        manyActionTimeBtn.setOnClickListener {
            setActionTimeInAWeekAndStartSetEatPerDayAndCanSleepTimeActivity(1.4F)
        }

        highActionTimeBtn.setOnClickListener {
            setActionTimeInAWeekAndStartSetEatPerDayAndCanSleepTimeActivity(1.5F)
        }
    }

    private fun setActionTimeInAWeekAndStartSetEatPerDayAndCanSleepTimeActivity(_actionTimeInAWeek: Float) {
        actionTimeInAWeek = _actionTimeInAWeek
        startActivity<SetActivityCanSleepTime>()
    }
}