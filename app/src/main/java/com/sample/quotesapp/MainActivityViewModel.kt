package com.sample.quotesapp

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import java.nio.charset.Charset

class MainActivityViewModel(val context: Context): ViewModel() {
    private var quotesList : Array<Quotes> = emptyArray()
    private var index = 0
    init {
        quotesList = loadFromAssets()
    }

    private fun loadFromAssets(): Array<Quotes> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quotes>::class.java)
    }

    fun getQuote() = quotesList[index]

    fun nextQuote(): Quotes {
        if (index == 0) {
            ++index
            Log.e("next Index ", index.toString())
            return quotesList[index]
        } else if (index == quotesList.size -1){
            index = 0
            Log.e("else next Index ", index.toString())
            return quotesList[index]
        } else{
            ++index
            Log.e("next else Index ", index.toString())
            return quotesList[index]
        }
    }

    fun previousQuote(): Quotes {
        if (index == 0) {
            index = quotesList.size-1
            Log.e("pre Index ", index.toString())
            return quotesList[index]
        } else {
            --index
            Log.e("pre Index ", index.toString())
            return quotesList[index]
        }

    }
}