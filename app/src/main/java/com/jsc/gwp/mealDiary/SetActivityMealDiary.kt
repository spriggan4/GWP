package com.jsc.gwp.mealDiary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsc.gwp.R
import kotlinx.android.synthetic.main.activity_set_meal_diary.*

class SetActivityMealDiary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_meal_diary)

        //날짜를 클릭하면 해당 정보를 가지고 editActiviryMealDiary로 이동
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            
        }
    }
}