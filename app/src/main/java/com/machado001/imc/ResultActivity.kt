package com.machado001.imc

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.machado001.imc.model.UserInfo

class ResultActivity : AppCompatActivity() {

    private lateinit var resultView: TextView
    private lateinit var textView: TextView

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result_activity)

        val bundle = intent.extras
        val user = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle?.getParcelable("User", UserInfo::class.java)
        } else {
            bundle?.getParcelable("User")
        }

        val userWeight = user?.weight
        val userHeight = user?.height

        val imcResult = calculateIMC(userWeight, userHeight)
        resultView = findViewById<TextView?>(R.id.result_view).apply {
            text = getString(R.string.user_imc_result_text, imcResult)
        }

        textView = findViewById<TextView?>(R.id.state_view).apply {
            text = getString(R.string.user_imc_state_text, checkBoundaries(imcResult))
        }

        findViewById<Button?>(R.id.button_share_result).setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(
                    Intent.EXTRA_TEXT, getString(
                        R.string.intent_send_result_text,
                        userWeight.toString(),
                        userHeight.toString(),
                        imcResult.toString(),
                        checkBoundaries(imcResult)
                    )
                )
            }
            startActivity(sendIntent)
        }
    }
}

