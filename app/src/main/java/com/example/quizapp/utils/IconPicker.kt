package com.example.quizapp.utils

import com.example.quizapp.R

object IconPicker {
    var s= arrayOf(
        R.drawable.ic_icon1,
        R.drawable.ic_baseline_face_24,
        R.drawable.ic_baseline_favorite_24,
        R.drawable.ic_icon11,
        R.drawable.ic_icon2
    )
    var index=0
    fun getIcon(): Int {
        index=((index+1)%s.size)
        return s[index]
    }

}