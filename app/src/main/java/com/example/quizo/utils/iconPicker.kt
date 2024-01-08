package com.example.quizo.utils

import com.example.quizo.R

object iconPicker {
    val icons = arrayOf(R.drawable.c__,R.drawable.java)
    var currentIconIndex = 0

    fun getColor():Int{
        currentIconIndex =(currentIconIndex+1)% icons.size
        return icons[currentIconIndex]
    }


}