package com.machado001.imc

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.machado001.imc.model.UserInfo

class MainActivity : AppCompatActivity(),
    OnClickListener {

    lateinit var weightField: TextInputEditText
    lateinit var heightField: TextInputEditText
    lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate = findViewById(R.id.result_button)
        weightField = findViewById(R.id.weight_field)
        heightField = findViewById(R.id.height_field)

        weightField.addTextChangedListener(textWatcher)
        heightField.addTextChangedListener(textWatcher)

        buttonCalculate.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val user = UserInfo(
            weightField.text.toString().toFloat(),
            heightField.text.toString().toFloat()
        )
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("User", user)
        }
        startActivity(intent)
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            // Check if both weight and height fields have non-empty values
            val weightNotEmpty = !weightField.text.isNullOrBlank()
            val heightNotEmpty = !heightField.text.isNullOrBlank()
            buttonCalculate.isEnabled = weightNotEmpty && heightNotEmpty
        }
    }

}



