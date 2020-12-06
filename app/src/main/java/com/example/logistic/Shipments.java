package com.example.logistic;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Shipments {
    public long shipment_id;
    public String shipment;
    public String pol;
    public String pod;
    public String custom;
    public String warehouse;
    public Date etd_pol;
    public Date eta_pod;
    public Date eta_custom;
    public Date etd_custom;
    public Date eta_warehouse;
    public int shipment_complete;
    public String status;


    public String getShipmentId(){
        return  String.valueOf(shipment_id);
    }
    public String getShipment(){
        return this.shipment;
    }

    public String getPol(){
        return this.pol;
    }

    public String getPod(){
        return this.pod;
    }

    public String getEtaWarehouse(){
        SimpleDateFormat shortDate = new SimpleDateFormat("dd.MM.yyyy");
        return shortDate.format(eta_warehouse);
    }
    public String getShipmentComplete(){
        if  (shipment_complete==1) {
            return "Yes";
        } else{
            return "No";
        }
    }








}
