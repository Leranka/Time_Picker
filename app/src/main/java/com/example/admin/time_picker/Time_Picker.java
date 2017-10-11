package com.example.admin.time_picker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class Time_Picker extends AppCompatActivity {
    DateFormat formatDateTime = DateFormat.getDateTimeInstance();
    Calendar dateTime = Calendar.getInstance();
    private TextView text;
    private TextView textout;
    private Button btn_date;
    private Button btn_time;
    private Button btn_time_out;

    //validate
    int date = 0;
    int month = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time__picker);
        text = (TextView) findViewById(R.id.txt_TextDateTime);
        textout = (TextView)findViewById(R.id.txt_TextDateTimeOut);
        btn_date = (Button) findViewById(R.id.btn_datePicker);
        btn_time = (Button) findViewById(R.id.btn_timePicker);
        btn_time_out = (Button) findViewById(R.id.btn_timePickerOut);

        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDate();
            }
        });

        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTime();
            }
        });
        btn_time_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateTimeOut();
            }
        });

        updateTextLabel();
    }

    private void updateDate(){
        new DatePickerDialog(this, d, dateTime.get(Calendar.YEAR),dateTime.get(Calendar.MONTH),dateTime.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void updateTime(){
        new TimePickerDialog(this, t, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    private void updateTimeOut(){
        new TimePickerDialog(this, out, dateTime.get(Calendar.HOUR_OF_DAY), dateTime.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            month=Calendar.getInstance().get(Calendar.MONTH);
            if(month<monthOfYear || month==monthOfYear) {
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, monthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextLabel();
            }else {
                Toast.makeText(Time_Picker.this, " Incorrect month", Toast.LENGTH_SHORT).show();
            }

            if (monthOfYear<dayOfMonth || month == dayOfMonth){
                dateTime.set(Calendar.YEAR, year);
                dateTime.set(Calendar.MONTH, monthOfYear);
                dateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateTextLabel();
            }
            else {
                Toast.makeText(Time_Picker.this, "Enter valid date", Toast.LENGTH_SHORT).show();
            }
        }
    };



    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

              dateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
              dateTime.set(Calendar.MINUTE, minute);
            date= hourOfDay;
              updateTextLabel();

        }

    };


    TimePickerDialog.OnTimeSetListener out = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hours, int mins) {
            dateTime.set(Calendar.HOUR_OF_DAY, hours);
            dateTime.set(Calendar.MINUTE, mins);
            if(date <hours) {
                updateTimePicker();
            }else {
                Toast.makeText(Time_Picker.this, "Re enter time "+date+" "+hours, Toast.LENGTH_SHORT).show();
            }

        }
    };

    private void updateTextLabel(){
        text.setText(formatDateTime.format(dateTime.getTime()));

    }

    private void updateTimePicker(){

            textout.setText(formatDateTime.format(dateTime.getTime()));


    }

}