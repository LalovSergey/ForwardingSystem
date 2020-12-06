package com.example.logistic;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class ShipAdapter extends RecyclerView.Adapter <ShipAdapter.ShipViewHolder>{

   ArrayList<Shipments> shipments;

    public ShipAdapter(ArrayList<Shipments> shipments){
        this.shipments=shipments;
    }
    public class ShipViewHolder extends RecyclerView.ViewHolder{
        TextView shipment_id;
        TextView shipment;
        TextView status;
        TextView pol;
        TextView pod;
        TextView custom;
        TextView warehouse;
        TextView shipment_complete;
        CardView cv;

        public ShipViewHolder (View view){
            super(view);
            shipment_id=view.findViewById(R.id.shipment_id);
            shipment=view.findViewById(R.id.shipment);
            status=view.findViewById(R.id.status);
            pol=view.findViewById(R.id.pol);
            pod=view.findViewById(R.id.pod);
            custom=view.findViewById(R.id.custom);
            warehouse=view.findViewById(R.id.warehouse);
            shipment_complete=view.findViewById(R.id.shipment_complete);
            cv=view.findViewById(R.id.cv_shipments);
        }
    }
    @NonNull
    @Override
    public ShipViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_shipment_row,parent,false);
        return new ShipViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ShipViewHolder holder, int position) {
        holder.shipment_id.setText(shipments.get(position).getShipmentId());
        holder.shipment.setText(shipments.get(position).shipment);
        holder.status.setText(shipments.get(position).status);
        holder.pol.setText(shipments.get(position).pol);
        holder.pod.setText(shipments.get(position).pod);
        holder.warehouse.setText(shipments.get(position).warehouse);
        holder.custom.setText(shipments.get(position).custom);
        holder.shipment_complete.setText(shipments.get(position).getShipmentComplete());

    }
    @Override
    public int getItemCount() {
        return shipments.size();
    }


}
