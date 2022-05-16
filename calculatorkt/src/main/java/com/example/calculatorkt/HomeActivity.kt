package com.example.calculatorkt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.calculatorkt.databinding.ActivityHomeBinding

open class HomeActivity : AppCompatActivity() {
    private var opPressed: Boolean = true; private var hasDot: Boolean = false;
    private var clrPressed: Boolean = false
    private var currAns: String = ""
    private val calc = Calculator()

    private val b by lazy { ActivityHomeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(b.root)

        val btnNumbers = arrayOf(
            b.btn0, b.btn1, b.btn2, b.btn3, b.btn4, b.btn5, b.btn6, b.btn7, b.btn8, b.btn9, b.btnDot
        )

        val btnOperators = arrayOf(b.btnAdd, b.btnSub, b.btnMul, b.btnDiv)

        // Number Buttons
        for (a in btnNumbers) {
            a.setOnClickListener {
                if (opPressed) {
                    b.tvInput.text = ""
                    b.btnClr.text = "C"
                    opPressed = false
                }
                if (a.text == b.btnDot.text){
                    if (!hasDot){
                        b.tvInput.append(a.text)
                        hasDot = true
                    }
                } else{
                    b.tvInput.append(a.text)
                }
            }
        }

        // Operator Buttons
        for (a in btnOperators) {
            a.setOnClickListener {
                calc.op = a.text.toString()
                if (!opPressed) {
                    if (calc.isLeftEmpty()){
                        calc.l = b.tvInput.text.toString()
                    }else {
                        b.btnEql.callOnClick()
                    }
                    opPressed = true
                    clrPressed = false
                }else{
                    calc.r = b.tvInput.text.toString()
                }
            }
        }

        b.btnEql.setOnClickListener {
            if (!opPressed){
                calc.r = b.tvInput.text.toString()
                opPressed = true
            }
            currAns = calc.solve()
            b.tvInput.text = currAns
            calc.l = currAns
        }

        b.btnClr.setOnClickListener {
            if (!clrPressed){
                clrPressed = true
                b.btnClr.text = "AC"
            }else {
                calc.reset()
                b.btnClr.text = "AC"
            }
            b.tvInput.text = "0"
            opPressed = true
            hasDot = false
        }
    }
}