package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class Game : ComponentActivity() {
    private val words = listOf("android", "kotlin", "developer", "studio", "mobile")
    private lateinit var scrambledWord: String
    private lateinit var originalWord: String

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game)

        val wordTextView = findViewById<TextView>(R.id.wordTextView)
        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val checkButton = findViewById<Button>(R.id.checkButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        startNewGame(wordTextView)

        checkButton.setOnClickListener {
            val userInput = inputEditText.text.toString().trim()
            if (userInput.equals(originalWord, ignoreCase = true)) {
                resultTextView.text = "Правильно! Нове слово..."
                startNewGame(wordTextView)
                inputEditText.text.clear()
            } else {
                resultTextView.text = "Неправильно, спробуй ще раз!"
            }
        }
    }

    private fun startNewGame(wordTextView: TextView) {
        originalWord = words.random()
        scrambledWord = originalWord.toCharArray().apply { shuffle() }.concatToString()
        wordTextView.text = scrambledWord
    }
}
