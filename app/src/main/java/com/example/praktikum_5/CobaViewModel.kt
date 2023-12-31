package com.example.praktikum_5

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.praktikum_5.Data.DataForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CobaViewModel:ViewModel() {
    var namauser : String by mutableStateOf("")
        private set
    var notelepon : String by mutableStateOf("")
        private set
    var jeniskelamin : String by mutableStateOf("")
        private set
    var alamat : String by mutableStateOf("")
        private set
    var email : String by mutableStateOf("")
        private set
    var status : String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataForm())
    val uiState: StateFlow<DataForm> = _uiState.asStateFlow()


    fun BacaData(nm:String,tlp:String,jk:String,almt:String, eml:String, stts:String){
        namauser=nm;
        notelepon=tlp;
        jeniskelamin=jk;
        alamat=almt;
        email=eml;
        status=stts;

    }

    fun setJenisKelamin(pilihjk:String){
        _uiState.update { currentState -> currentState.copy(sex = pilihjk) }
    }

    fun setStatusPernikahan(pilihsts:String){
        _uiState.update { currentState -> currentState.copy(status = pilihsts) }
    }

}