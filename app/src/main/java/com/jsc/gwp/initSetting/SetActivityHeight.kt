package com.jsc.gwp.initSetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jsc.gwp.R
import com.jsc.gwp.data.height
import kotlinx.android.synthetic.main.activity_set_height.*
import org.jetbrains.anko.startActivity

class SetActivityHeight : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_height)

        moveSetCurWeightBtn.setOnClickListener {
            //입력 값 없으면 토스트 출력
            if (inputHeight.text.isNotBlank()) {
                val _height: Int = inputHeight.text.toString().toInt()
                //키 입력 30이상 220 이하일때만 넘어감
                if (_height > 29 && _height < 221) {
                    height = _height
                    startActivity<SetActivityCurrentWeight>()
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