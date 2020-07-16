package com.jsc.gwp.initSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jsc.gwp.R
import com.jsc.gwp.data.curWeight
import kotlinx.android.synthetic.main.activity_set_current_weight.*
import org.jetbrains.anko.startActivity

class SetActivityCurrentWeight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_current_weight)

        moveSetDesireWeightBtn.setOnClickListener {
            //입력 값 없으면 토스트 출력
            if (inputCurWeightText.text.isNotBlank()) {
                val weight: Int = inputCurWeightText.text.toString().toInt()
                //현재 몸무게 입력 30이상 200 이하일때만 넘어감
                if (weight > 29 && weight < 201) {
                    curWeight = weight
                    startActivity<SetActivityDesireWeight>()
                } else {
                    Toast.makeText(
                        this,
                        "설정 범위는 30에서 200까지만 가능합니다.", Toast.LENGTH_SHORT
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