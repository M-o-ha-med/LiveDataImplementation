package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var lengthData : EditText
    private lateinit var heightData : EditText
    private lateinit var widthData : EditText
    private lateinit var buttonCalc : Button
    private lateinit var resultText : TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lengthData= findViewById(R.id.length_data)
        heightData=findViewById(R.id.Height_data)
        widthData=findViewById(R.id.Width_data)
        buttonCalc=findViewById(R.id.Button_calc)
        resultText=findViewById(R.id.Hasil)
        buttonCalc.setOnClickListener(this)



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            resultText.text = result
        }

    }



    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT,resultText.text.toString())
    }



    override fun onClick(v: View?) {
        if(v?.id == R.id.Button_calc){
            val lenght = lengthData.text.toString().trim()
            val height = heightData.text.toString().trim()
            val width  = widthData.text.toString().trim()
            var isEmptyFields = false

            if(lenght.isEmpty()) {
                isEmptyFields= true
                lengthData.error = "Field ini harus diisi"
            }
            if(height.isEmpty()) {
                isEmptyFields= true
                heightData.error = "Field ini harus diisi"
            }
            if(width.isEmpty()) {
                isEmptyFields= true
                widthData.error = "Field ini harus diisi"
            }

            if(!isEmptyFields){
                val result= lenght.toDouble() * height.toDouble() * width.toDouble()
                resultText.text= result.toString()
            }
        }
    }

}