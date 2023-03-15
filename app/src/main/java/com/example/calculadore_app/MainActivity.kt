package com.example.calculadore_app

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculadore_app.databinding.ActivityMainBinding
import android.os.Vibrator
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var text : TextView
    var result = 0.0
    var isNewOP=true
    var oldNumber=""
    var op="+"
    var res1=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val enlace = ActivityMainBinding.inflate(layoutInflater)
        text=enlace.editText
        text.isFocusable = false
        text.isFocusableInTouchMode = false
        text.isEnabled = false
        setContentView(enlace.root)
        //setContentView(R.layout.activity_main)

    }

    fun numberEvent (View: View){
        if (text.text.toString()=="0.0")
            text.setText("")
        val text1 = text.text.toString()
        if(isNewOP)
            text.setText(text1)
        isNewOP=false
        var buclik:String= text.text.toString()
        var buselect:Button=View as Button
        when(buselect.id){
            R.id.bu1->{buclik+="1"}
            R.id.bu2->{buclik+="2"}
            R.id.bu3->{buclik+="3"}
            R.id.bu4->{buclik+="4"}
            R.id.bu5->{buclik+="5"}
            R.id.bu6->{buclik+="6"}
            R.id.bu7->{buclik+="7"}
            R.id.bu8->{buclik+="8"}
            R.id.bu9->{buclik+="9"}
            R.id.bu0->{buclik+="0"}
            R.id.buDot->{buclik+="."}
            R.id.buPlusMinus->{buclik="-$buclik"}
        }
        text.setText(buclik)
    }

    fun operadorEvent(view: View){

        val text1 = text.text.toString()
        oldNumber=text1.toString()
        var buselect:Button= view as Button
        when(buselect.id){
            R.id.buMultypli->{op="*"
                val newText = text1.plus("*" as CharSequence)
                text.setText(newText)}
            R.id.buPlus->{op="+"
                val newText = text1.plus("+" as CharSequence)
                text.setText(newText)}
            R.id.buMinus->{op="-"
                val newText = text1.plus("-" as CharSequence)
                text.setText(newText)}
            R.id.buDivide->{op="/"
                val newText = text1.plus("/" as CharSequence)
                text.setText(newText)}
        }
        isNewOP=false
    }
    fun equalEvent(view: View){
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val operators = listOf("+", "-", "*", "/")
        val newnumber= text.text.toString()
        val res=text.text.isNotEmpty()
        val n1 = newnumber.split(operators.find { newnumber.contains(it) } ?: "")
        val pattern = "[0-9]+[+\\-*\\/][0-9]+".toRegex()
        val hasTwoNumbers = pattern.containsMatchIn(newnumber)
        if(res && n1[1]!="" && hasTwoNumbers && n1[1].toDoubleOrNull()!=null){

            when (op){
                "+" -> {
                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() + newn.toDouble()
                }
                "*" -> {
                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() * newn.toDouble()
                }
                "-" -> {

                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() - newn.toDouble()
                }
                "/" -> {

                    val newn = n1[1].trim()
                    result = oldNumber.toDouble() / newn.toDouble()
                }

            }
            text.setText(result.toString())
            res1 = true
        }else{
            vibrator.vibrate(longArrayOf(0, 100, 100, 100), -1)
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show()
        }
    }
    fun acEvent(view: View){
        text.setText("")
        isNewOP=true
        result=0.0
    }
    fun percentEvent(view: View){
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        var no= text.text.toString()
        val hasMultiplication = no.contains("*")
        val n1=no.split("*")
        if(hasMultiplication && n1[1]!="") {
            val result = n1[0].toDouble() * n1[1].toDouble() / 100
            text.setText(result.toString())
        }else{
            vibrator.vibrate(longArrayOf(0, 100, 100, 100), -1)
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show()
        }
        isNewOP=true
    }
}