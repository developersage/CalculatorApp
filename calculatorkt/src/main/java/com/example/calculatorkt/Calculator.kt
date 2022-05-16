package com.example.calculatorkt

open class Calculator(var l: String = "0", var r: String = "0", var op: String = "+") {

    fun isLeftEmpty(): Boolean { return l == "0" }

    fun reset() { l = "0"; r = "0"; }

    fun solve(): String {
        when (op) {
            "+" -> return (l.toDouble() + r.toDouble()).toString()
            "-" -> return (l.toDouble() - r.toDouble()).toString()
            "x" -> return (l.toDouble() * r.toDouble()).toString()
            "/" -> return (l.toDouble() / r.toDouble()).toString()
        }
        return ""
    }
}