package com.example.logistic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class two_page extends AppCompatActivity {
Button btn_check;
Button btn_shipment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_page);
        btn_check=findViewById(R.id.btn_check);
        btn_shipment=findViewById(R.id.btn_shipment);

        // Принимаем логин из стартовой активити
        final String login = getIntent().getStringExtra("login");
        // Принимаем пароль из стартовой активити
        final String password = getIntent().getStringExtra("password");




        btn_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Переданный логин: "+login+". Переданный пароль: "+password,
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });


        btn_shipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent list_shipments = new Intent(getApplicationContext(), list_shipments.class);
                // указываем первым параметром ключ, а второе значение
                // по ключу мы будем получать значение с Intent
                list_shipments.putExtra("login", login);
                list_shipments.putExtra("password", password);
                startActivity(list_shipments);
            }
        });






    }
}
