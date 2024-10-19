package com.example.myapplications

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class myViewModel : ViewModel(){
    val currentVolume : MutableLiveData<Double> by lazy{
        MutableLiveData<Double>()
    }
    var Volume = 0.0
    fun calculateVolume(length : Double,height : Double , width : Double)  : Double{
        Volume = length*height*width
        return Volume
    }
}