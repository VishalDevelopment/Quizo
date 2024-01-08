package com.example.quizo.utils


    object colorpicker{
        val colors = arrayOf("#ffb6c1", "#ffa07a", "#FF7276")
        var currentColorIndex = 0
        fun getColor(): String? {
            currentColorIndex =(currentColorIndex+1)%colors.size
            return colors[currentColorIndex]
        }
    }
