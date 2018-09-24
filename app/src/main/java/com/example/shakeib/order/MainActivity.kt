package com.example.shakeib.order

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {

    var name:String = ""
    var quantity = 0
    var price = "$"
    var priceOfWhippedCream = 10
    var priceOfMilk = 5
    var priceOfCoffee = 20
    var finalPrice = 0
    var milkCheck = false
    var creamCheck = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val incButton = findViewById(R.id.buttonPlus) as Button
        val decButton = findViewById(R.id.buttonMinus) as Button
        val orderButton = findViewById(R.id.submitOrderButton) as Button

        incButton.setOnClickListener { increament() }
        decButton.setOnClickListener { decreament() }
        orderButton.setOnClickListener { sendOrder() }
    }

    fun increament(){
        val quantityOfCoffee =findViewById(R.id.quantity) as TextView
        quantity++
        quantityOfCoffee.setText(quantity.toString())
        displayPrice(quantity)
    }

    fun decreament(){
        val quantityOfCoffee =findViewById(R.id.quantity) as TextView

        if(quantity==0){
            Toast.makeText(applicationContext,"Invalid quantity",Toast.LENGTH_SHORT).show()
        }else{
            quantity--
            quantityOfCoffee.setText(quantity.toString())
            displayPrice(quantity)
        }



    }

    private fun displayPrice(quantity:Int){
        val cream = findViewById(R.id.checkBoxCream) as CheckBox
        val milk = findViewById(R.id.checkBoxMilk) as CheckBox
        val price = findViewById(R.id.priceDisplay) as TextView
        if(milk.isChecked || cream.isChecked){
            creamCheck = true
            milkCheck = true

        }
        finalPrice = quantity*priceOfCoffee
        price.setText("$${finalPrice}")
    }

    fun sendOrder(){
        val nameOfSender = findViewById(R.id.editText) as EditText
        name = nameOfSender.text.toString()
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "anyone@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order from ${name}")
        intent.putExtra(Intent.EXTRA_TEXT, """
            COFFEE ORDER:
            NAME: $name
            QUANTITY: $quantity
            MILK: $milkCheck
            CREAM: $creamCheck
            PRICE: $finalPrice
        """.trimIndent())
        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent)
    }

}
