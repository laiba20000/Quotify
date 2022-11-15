package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MainViewModel(val context: Context) : ViewModel() {
    private  var quotelist: Array<Quote> = emptyArray()
    private var index=0

    init {
        quotelist=loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
     val inputSream =context.assets.open("quotes.json")
        val  size= inputSream.available()
        val buffer=ByteArray(size)
        inputSream.read(buffer)
        inputSream.close()
        val json= String(buffer, Charsets.UTF_8)
        val  gson =Gson()
       return  gson.fromJson(json, Array<Quote>::class.java)
    }
    fun getQuote()=quotelist[index]
    fun  nextQuote()=quotelist[++index]
    fun previousQuote()=quotelist[--index]
}