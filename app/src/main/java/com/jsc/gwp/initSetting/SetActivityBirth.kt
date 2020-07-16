package com.jsc.gwp.initSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jsc.gwp.R
import com.jsc.gwp.data.birthDay
import com.jsc.gwp.data.birthMonth
import com.jsc.gwp.data.birthYear
import kotlinx.android.synthetic.main.activity_set_birth.*
import org.jetbrains.anko.startActivity

class SetActivityBirth : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_birth)

        moveSetHeightBtn.setOnClickListener {
            saveBirth(
                datePicker.year,
                datePicker.month + 1,
                datePicker.dayOfMonth
            )
        }
    }

    //year,month,day를 각각 받아 bundle에 넣어서 인텐트로 넘긴다.
    private fun saveBirth(year: Int, month: Int, day: Int) {
        birthYear = year
        birthMonth = month
        birthDay = day
        startActivity<SetActivityHeight>()
    }
}