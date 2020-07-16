package com.jsc.gwp.initSetting

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jsc.gwp.R
import com.jsc.gwp.data.gender
import kotlinx.android.synthetic.main.activity_set_gender.*
import org.jetbrains.anko.startActivity

class SetActivityGender : AppCompatActivity() {
    private var backKeyPressedTime: Long = 0
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_gender)

        manChoiceBtn.setOnClickListener {
            //남자 선택시 1번 저장
            saveGenderAndStartActivity(1)
        }
        girlChoiceBtn.setOnClickListener {
            //여자 선택시 2번 저장
            saveGenderAndStartActivity(2)
        }
    }

    private fun saveGenderAndStartActivity(_gender:Int){
        gender = _gender
        startActivity<SetActivityBirth>()
    }

    override fun onBackPressed() {
        //현재 시간을 통해서 만들기
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            toast= Toast.makeText(
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
            finishAffinity()
        }
    }
}