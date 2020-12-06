package com.example.logistic;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class list_shipments extends AppCompatActivity  {
    String login;
    String password;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_shipments);
        recyclerView = findViewById(R.id.rcShipments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
// Принимаем логин из стартовой активити
         login = getIntent().getStringExtra("login");
        // Принимаем пароль из стартовой активити
         password = getIntent().getStringExtra("password");
        ShowShipments showShipments=new ShowShipments();
        showShipments.execute();
    }
    private class ShowShipments extends AsyncTask<Void, Void, ArrayList<Shipments>> {

        String strUrl="http://192.168.43.189/logistic/shipments.php";
        String log;
        String pass;
        AuthTotal authTotal=new AuthTotal();
        ArrayList<Shipments> shipments;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            log=login;
            pass=password;
        }
        @Override
        protected void  onPostExecute(ArrayList<Shipments> shipments) {
            super.onPostExecute(shipments);

           ShipAdapter shipAdapter = new ShipAdapter(shipments);
           recyclerView.setAdapter(shipAdapter);

        }
        @Override
        protected ArrayList<Shipments> doInBackground(Void...voids) {

            shipments= authTotal.getListShipments(strUrl,login,password);

            return shipments;
        }
    }
}