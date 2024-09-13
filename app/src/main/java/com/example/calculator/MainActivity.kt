package com.example.calculator


import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private var operation: String? = null
    private var firstOperand: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)

        val buttonClear: Button = findViewById(R.id.buttonClear)
        val buttonEnter: Button = findViewById(R.id.buttonEnter)
        val buttonEquals: Button = findViewById(R.id.buttonEquals)

        val buttons = mapOf(
            "0" to findViewById<Button>(R.id.button0),
            "1" to findViewById<Button>(R.id.button1),
            "2" to findViewById<Button>(R.id.button2),
            "3" to findViewById<Button>(R.id.button3),
            "4" to findViewById<Button>(R.id.button4),
            "5" to findViewById<Button>(R.id.button5),
            "6" to findViewById<Button>(R.id.button6),
            "7" to findViewById<Button>(R.id.button7),
            "8" to findViewById<Button>(R.id.button8),
            "9" to findViewById<Button>(R.id.button9)
        )

        val operations = mapOf(
            "+" to findViewById<Button>(R.id.buttonAdd),
            "-" to findViewById<Button>(R.id.buttonSubtract),
            "*" to findViewById<Button>(R.id.buttonMultiply),
            "/" to findViewById<Button>(R.id.buttonDivide)
        )

        buttons.forEach { (number, button) ->
            button.setOnClickListener { appendToInput(number) }
        }

        operations.forEach { (op, button) ->
            button.setOnClickListener { setOperation(op) }
        }

        buttonClear.setOnClickListener {
            editTextInput.text.clear()
            operation = null
            firstOperand = null
        }

        buttonEnter.setOnClickListener {
            firstOperand = editTextInput.text.toString().toDoubleOrNull()
            editTextInput.text.clear()
        }

        buttonEquals.setOnClickListener {
            val secondOperand = editTextInput.text.toString().toDoubleOrNull()
            if (firstOperand != null && secondOperand != null && operation != null) {
                val result = when (operation) {
                    "+" -> firstOperand!! + secondOperand
                    "-" -> firstOperand!! - secondOperand
                    "*" -> firstOperand!! * secondOperand
                    "/" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else Double.NaN
                    else -> null
                }
                result?.let { editTextInput.setText(it.toString()) }
            }
        }
    }

    private fun appendToInput(value: String) {
        editTextInput.append(value)
    }

    private fun setOperation(op: String) {
        operation = op
        firstOperand = editTextInput.text.toString().toDoubleOrNull()
        editTextInput.text.clear()
    }
}
