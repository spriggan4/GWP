package com.jsc.gwp.initSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jsc.gwp.R
import com.jsc.gwp.data.desireWeight
import kotlinx.android.synthetic.main.activity_set_desire_weight.*
import org.jetbrains.anko.startActivity

class SetActivityDesireWeight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_desire_weight)

        moveSetActionTimeInAWeek.setOnClickListener {
            //입력 값 없으면 토스트 출력
            if (inputDesireWeight.text.isNotBlank()) {
                val weight: Int = inputDesireWeight.text.toString().toInt()
                //키 입력 30이상 220 이하일때만 넘어감
                if (weight > 29 && weight < 221) {
                    desireWeight = weight
                    startActivity<SetActivityActionTimeInAWeek>()
                } else {
                    Toast.makeText(
                        this,
                        "설정 범위는 30에서 220까지만 가능합니다.", Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "값을 입력해 주세요.", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}