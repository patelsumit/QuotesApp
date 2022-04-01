package com.sample.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    private val text: TextView get() = findViewById(R.id.quoteText)
    private val author: TextView get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityViewModel = ViewModelProvider(this, MainActivityViewModelFactory(application)).get(MainActivityViewModel::class.java)
        setQuote(mainActivityViewModel.getQuote())
    }

    fun setQuote(quotes: Quotes) {
        text.text = quotes.text
        author.text = quotes.author
    }

    fun onNext(view: View) {
        setQuote(mainActivityViewModel.nextQuote())
    }

    fun onPrevious(view: View) {
        setQuote(mainActivityViewModel.previousQuote())
    }

    fun onShare(view: View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, mainActivityViewModel.getQuote().text)
        startActivity(intent)
    }
}