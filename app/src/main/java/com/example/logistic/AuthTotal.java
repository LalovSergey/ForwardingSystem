package com.example.logistic;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AuthTotal {
    private static String strURL;
    private static String login;
    private static  String password;


    public  boolean checkConnect(String strUrl){
        this.strURL=strUrl;
        Boolean result = false;
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) new URL(strURL).openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return result;
    }

    public boolean checkAuth(String strURL,String login, String password) {
        this.strURL = strURL;
        this.login = login;
        this.password = password;
        try {
            URL url = null;
            url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("login=" + login + "&password=" + password);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            line = br.readLine();
            if (line.contains("connect_true")){
                System.out.println("Логин и пароль введен верно!");
                return true;
            } else {
                System.out.println("Логин и пароль введен неправильно!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Нет доступа к сайту");
            return false;
        }
    }
    public  ArrayList<Shipments> getListShipments(String strURL,String login, String password) {
        ArrayList<Shipments> listShipments;
        URL url;
        try {
            url = new URL(strURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("login=" + login + "&password=" + password);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
            StringBuilder SB = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = br.readLine()) != null)
                SB.append(line);
            br.close();
            String strJsonResult = SB.toString();
            Gson gson = new Gson();
            /// // Shipments[] shipmentsArray = gson.fromJson(strJsonResult, Shipments[].class);
            listShipments = gson.fromJson(strJsonResult, new TypeToken<ArrayList<Shipments>>() {
            }.getType());

           return listShipments;

        } catch (Exception e) {
           //e.printStackTrace();
            return null;
        }
    }


}
