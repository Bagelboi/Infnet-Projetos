package com.example.charactermakerrpg

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.max
import kotlin.math.min

class ClassFluffViewModel : ViewModel() {
    var charClasses = listOf<charclass>(
        CLASS_SNIPER,
        CLASS_WARRIOR,
        CLASS_MEDIC,
        CLASS_SOLDIER
    )

    var charFluffName : MutableLiveData<String> = MutableLiveData<String>("")
    var charFluffGender : MutableLiveData<String> = MutableLiveData<String>("")
    var charFluffAge : MutableLiveData<UInt> = MutableLiveData<UInt>(0U)
    var charFluffLevel : MutableLiveData<UInt> = MutableLiveData<UInt>(0U)
    var charCurClass : MutableLiveData<Int> = MutableLiveData<Int>(0)




}