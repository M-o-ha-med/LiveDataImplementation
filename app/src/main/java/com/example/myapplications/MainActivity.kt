package com.example.myapplications

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var lengthData : EditText
    private lateinit var heightData : EditText
    private lateinit var widthData : EditText
    private lateinit var buttonCalc : Button
    private lateinit var resultText : TextView
    private val viewModel : myViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        lengthData= findViewById(R.id.length_data)
        heightData=findViewById(R.id.Height_data)
        widthData=findViewById(R.id.Width_data)
        buttonCalc=findViewById(R.id.Button_calc)
        resultText=findViewById(R.id.Hasil)

        val VolumeObserver = Observer<Double>{currvolume -> resultText.text = currvolume.toString()}
        buttonCalc.setOnClickListener({v ->
            if(v?.id == R.id.Button_calc){
                val length = lengthData.text.toString().trim()
                val height = heightData.text.toString().trim()
                val width  = widthData.text.toString().trim()
                var isEmptyFields = false

                if(length.isEmpty()) {
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
                    val result = viewModel.calculateVolume(length.toDouble(),height.toDouble(),width.toDouble())
                    viewModel.currentVolume.setValue(result)
                }
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.currentVolume.observe(this,VolumeObserver)

    }



}