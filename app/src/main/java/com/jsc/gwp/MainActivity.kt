package com.jsc.gwp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.jsc.gwp.data.*
import com.jsc.gwp.mealDiary.SetActivityMealDiary
import com.jsc.gwp.tips.TipsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {
    //private lateinit var drawerLayout: DrawerLayout
    private lateinit var toast: Toast

    private var backKeyPressedTime: Long = 0
    private var totalKcalNum: Int = 0
    private var needKcalToday by Delegates.notNull<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Default) {
            saveInitSetData()
            calculateNeedKcalToday()
        }

        btnTodaysTip.setOnClickListener {
            startActivity<TipsActivity>()
        }

        btnMealDiary.setOnClickListener {
            startActivity<SetActivityMealDiary>()
        }
    }

    private fun saveInitSetData() {
        val sharedPref = this.getSharedPreferences(
            getString(R.string.setting_nums_file_key),
            Context.MODE_PRIVATE
        )

        with(sharedPref.edit()) {
            putInt(getString(R.string.set_gender_key), gender)
            putInt(
                getString(R.string.set_birth_year_key), birthYear
            )
            putInt(
                getString(R.string.set_birth_month_key),
                birthMonth
            )
            putInt(
                getString(R.string.set_birth_day_key),
                birthDay
            )
            putInt(getString(R.string.set_height_key), height)
            putInt(
                getString(R.string.set_current_weight_key),
                curWeight
            )
            putInt(
                getString(R.string.set_desire_weight_key),
                desireWeight
            )
            putFloat(
                getString(R.string.set_action_time_in_week_key),
                actionTimeInAWeek
            )
            putInt(
                getString(R.string.set_can_sleep_time_hour_key),
                canSleepHour
            )
            putInt(
                getString(R.string.set_can_sleep_time_min_key),
                canSleepMin
            )
            commit()
        }
    }

    private fun calculateNeedKcalToday() {
        val rightNow = Calendar.getInstance()
        age = rightNow.get(Calendar.YEAR) - birthYear

        //생일 안지났으면 한살 내리기
        if (birthMonth * 100 + birthDay >
            rightNow.get(Calendar.MONTH) * 100 + rightNow.get(Calendar.DAY_OF_MONTH)
        ) {
            --age
        }

        //살 찌기 위해 필요한 칼로리 남녀 나눠서 계산하기, 1=남자 2=여자
        if (gender == 1) {
            //남자
            needCarl =
                (66.47 + (13.75 * curWeight) + (5 * height) - (6.76 * age) + 500) * actionTimeInAWeek
        } else if (gender == 2) {
            //여자
            needCarl =
                (655.1 + (9.56 * curWeight) + (1.85 * height) - (4.68 * age) + 500) * actionTimeInAWeek
        }
        needKcalToday = Math.round(needCarl)
    }

    override fun onBackPressed() {
        //뒤로 가기 버튼 누를 경우 메뉴가 열려져 있으면 닫음.
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START)
//        } else {
        //현재 시간을 통해서 만들기
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast = Toast.makeText(
                this,
                "'뒤로' 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT
            )
            toast.show()
            //토스트만 뛰우고 일단 함수 중료
            return
        }
        //2초안에 다시 누르면 앱종료
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast.cancel()
            this.finishAffinity()
        }
        //}
    }
}