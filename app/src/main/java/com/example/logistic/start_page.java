package com.example.logistic;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class start_page extends AppCompatActivity {
    private Button btn_auth;
    private EditText fld_log;
    private EditText fld_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        fld_log = findViewById(R.id.fld_log);
        fld_pass = findViewById(R.id.fld_pass);
        btn_auth = findViewById(R.id.btn_auth);

        btn_auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View view) {

                if (TextUtils.isEmpty(fld_log.getText().toString())||TextUtils.isEmpty(fld_pass.getText().toString())){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Введите логин и пароль!",
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    GoTwo goTwo = new GoTwo();
                    goTwo.execute();

                }
            }
        });
    }


private class GoTwo extends AsyncTask <Void, Void, Boolean> {
    final static String strUrl="http://192.168.43.189/logistic/index.php";
    private String log;
    private String pass;

     @Override
     protected void onPreExecute() {
         super.onPreExecute();
         log=fld_log.getText().toString();
         pass=fld_pass.getText().toString();
     }

     @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);

        if (result==true){
        Toast toast = Toast.makeText(getApplicationContext(),
                "Добро пожаловать!",
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
Intent page_two = new Intent(getApplicationContext(), two_page.class);
            // указываем первым параметром ключ, а второе значение
            // по ключу мы будем получать значение с Intent
            page_two.putExtra("login", fld_log.getText().toString());
            page_two.putExtra("password", fld_pass.getText().toString());
            startActivity(page_two);
        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Нет доступа!",
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }
    @Override
    protected Boolean doInBackground(Void...voids) {
        AuthTotal authTotal=new AuthTotal();
        if (authTotal.checkConnect(strUrl)==true && authTotal.checkAuth(strUrl,log,pass)==true){
            return true;
        } else {
            return false;
        }
    }
}
}


