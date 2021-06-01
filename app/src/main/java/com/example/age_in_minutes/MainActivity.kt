package com.example.age_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{view->
            clickdate(view)
        }
    }
    fun clickdate(view: View){
       val mycalendar= Calendar.getInstance()
        val year = mycalendar.get(Calendar.YEAR)
        val month = mycalendar.get(Calendar.MONTH)
        val day = mycalendar.get(Calendar.DAY_OF_MONTH)
        val currentdate = "$day/${month+1}/$year"
        val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener {
                view, Selectedyear, Selectedmonth, SelecteddayOfMonth ->
            val Selected_Date = "$SelecteddayOfMonth/${Selectedmonth+1}/$Selectedyear"
            selectdate.setText(Selected_Date)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val select_date = sdf.parse(Selected_Date)
            val select_date_in_minutes = select_date!!.time/60000

            val current_date = sdf.parse(sdf.format(System.currentTimeMillis()))
            val current_date_in_minutes = current_date!!.time/6000

            val age_in_minutes = current_date_in_minutes - select_date_in_minutes
            ageinminute.setText("Age in minutes : $age_in_minutes")
            val age_in_year = year - Selectedyear
            var age_in_month = 0
            if(Selectedmonth>month) {
                 age_in_month = (Selectedmonth  - month )
            }
            else{
                age_in_month = (month - Selectedmonth)
            }
            var a = "You are $age_in_year year and $age_in_month month old"
            ageinold.setText(a)
        }
        ,year,month,day)
        dpd.datePicker.setMaxDate(Date().time-86400000) //miliseconds in a day
        //we restrict the use of future date selection
        dpd.show()

    }
}