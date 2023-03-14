package com.example.calculadore_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.calculadore_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var text : TextView

    var result = 0.0
    var isNewOP=true
    var oldNumber=""
    var op="+"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val enlace= ActivityMainBinding.inflate(layoutInflater)
        text=enlace.editText

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
        isNewOP=true
        val text1 = text.text.toString()
        oldNumber=text.text.toString()
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
    }
    fun equalEvent(view: View){
        result = result
        val operators = listOf("+", "-", "*", "/")
        val newnumber= text.text.toString()
        val res=(text.text.isNotEmpty() && operators.any { newnumber.contains(it) }) &&result==0.0
        if(res){

            when (op){
                "+" -> {
                    val n1=newnumber.split("+")
                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() + newn.toDouble()
                }
                "*" -> {
                    val n1=newnumber.split("*")
                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() * newn.toDouble()
                }
                "-" -> {
                    val n1=newnumber.split("-")
                    val newn= n1[1].trim()
                    result = oldNumber.toDouble() - newn.toDouble()
                }
                "/" -> {
                    val n1 = newnumber.split("/")
                    val newn = n1[1].trim()
                    result = oldNumber.toDouble() / newn.toDouble()
                }
            }
            text.setText(result.toString())
        }else{

        }
    }
    fun acEvent(view: View){
        text.setText("")
        isNewOP=true
        result=0.0
    }
    fun percentEvent(view: View){
        var no= text.text.toString().toDouble()/100
        text.setText(no.toString())
        isNewOP=true
    }
}