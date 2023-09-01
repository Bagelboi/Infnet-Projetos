package com.danielgomeslipkin.smp.at.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoteVm : ViewModel() {
    var updated = MutableLiveData<Boolean>(true)

    fun updateList() {
        updated.value = true
    }
}