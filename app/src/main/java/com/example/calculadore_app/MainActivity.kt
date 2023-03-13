package com.example.calculadore_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.calculadore_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var text : TextView


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
        if(isNewOP)
            text.setText("")
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
        oldNumber=text.text.toString()
        var buselect:Button= view as Button
        when(buselect.id){
            R.id.buMultypli->{op="*"}
            R.id.buPlus->{op="+"}
            R.id.buMinus->{op="-"}
            R.id.buDivide->{op="/"}
        }
    }
    fun equalEvent(view: View){
        var newnumber= text.text.toString()
        var result =0.0
        when(op){
            "+" -> {result =oldNumber.toDouble() + newnumber.toDouble()}
            "*" -> {result =oldNumber.toDouble() * newnumber.toDouble()}
            "-" -> {result =oldNumber.toDouble() - newnumber.toDouble()}
            "/" -> {result =oldNumber.toDouble() / newnumber.toDouble()}
        }
        text.setText(result.toString())
    }
    fun acEvent(view: View){
        text.setText("0")
        isNewOP=true
    }
    fun percentEvent(view: View){
        var no= text.text.toString().toDouble()/100
        text.setText(no.toString())
        isNewOP=true
    }
}