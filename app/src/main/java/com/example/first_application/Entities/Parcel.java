package com.example.first_application.Entities;
import java.util.Date;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.example.first_application.Entities.ParcelStatus.SEND;


@Entity(tableName="parcel_table")
public class Parcel {
    @PrimaryKey(autoGenerate=true)
    long id;
    ParcelType parcelType;
    Receptor receptor;
    boolean fragile;
    ParcelWeight parcelWeight;
    Date sendParcelDate;
    ParcelStatus parcelStatus = SEND;
    String senderName = "NO";

    
//Location location

    //constructor
    public Parcel(){};//pour main Activity

    public Parcel(long id,boolean fragile, ParcelType parcelType, ParcelWeight parcelWeight, Receptor receptor, Date sendParcelDate,  ParcelStatus parcelStatus, String senderName)
    {
        buildInstance(id,fragile, parcelType, parcelWeight, receptor, sendParcelDate);
        this.parcelStatus= parcelStatus;
        this.senderName= senderName;
    }

    public Parcel(long id, boolean fragile, ParcelType parcelType, ParcelWeight parcelWeight, Receptor receptor, Date sendParcelDate, String senderName)
    {
        buildInstance(id,fragile, parcelType, parcelWeight, receptor, sendParcelDate);
        this.senderName= senderName;
    }

    public Parcel(long id, boolean fragile, ParcelType parcelType, ParcelWeight parcelWeight, Receptor receptor, Date sendParcelDate,  ParcelStatus parcelStatus)
    {
        buildInstance(id,fragile, parcelType, parcelWeight, receptor, sendParcelDate);
        this.parcelStatus= parcelStatus;
    }

    public Parcel(long id,boolean fragile, ParcelType parcelType, ParcelWeight parcelWeight, Receptor receptor, Date sendParcelDate)
    {
        buildInstance(id,fragile, parcelType, parcelWeight, receptor, sendParcelDate);
    }

    private void buildInstance(long id, boolean fragile, ParcelType parcelType, ParcelWeight parcelWeight, Receptor receptor, Date sendParcelDate)
    {
        this.id=id;
        this.fragile = fragile;
        this.parcelType = parcelType;
        this.parcelWeight = parcelWeight;
        this.receptor = receptor;
        this.sendParcelDate= sendParcelDate;
    }


    //setter and getter
    public boolean isFragile() { return fragile; }
    public void setFragile(boolean fragile) { this.fragile = fragile; }

    public ParcelType getParcelType() { return parcelType; }
    public void setParcelType(ParcelType parcelType) { this.parcelType = parcelType; }

    public ParcelWeight getParcelWeight() { return parcelWeight; }
    public void setParcelWeight(ParcelWeight parcelWeight) { this.parcelWeight = parcelWeight; }

    public Receptor getReceptor() { return receptor; }
    public void setReceptor(Receptor receptor) { this.receptor = receptor; }

    public Date getSendParcel() { return sendParcelDate; }
    public void setSendParcel(Date sendParcelDate) { this.sendParcelDate = sendParcelDate; }

    public ParcelStatus getParcelStatus() { return parcelStatus; }
    public void setParcelStatus(ParcelStatus parcelStatus) { this.parcelStatus = parcelStatus; }

    public String getSenderName() { return senderName; }
    public void setSenderName(String senderName) { this.senderName = senderName; }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    //voir quoi faire pr la date !!!


    @Override
    public String toString() {
        return "[" + getId() + "] " + receptor.getName();
    }


}
