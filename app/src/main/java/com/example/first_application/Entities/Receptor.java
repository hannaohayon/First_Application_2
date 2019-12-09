package com.example.first_application.Entities;

import android.app.DatePickerDialog;

public class Receptor {
    private String Address;
    private String Name;
    private double PhoneNumber;
    private DatePickerDialog ReceiveDateParcel;
    private String Email;

    //getter and setter
    public void setPhoneNumber(double phoneNumber) { PhoneNumber = phoneNumber; }
    public double getPhoneNumber() { return PhoneNumber; }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }

    public String getAddress(){return Address;}
    public void setAddress(String address) {
        Address = address;
    }

    public DatePickerDialog getReceiveDateParcel() { return ReceiveDateParcel; }
    public void setReceiveDateParcel(DatePickerDialog receiveDateParcel) { ReceiveDateParcel = receiveDateParcel; }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    //constructor
    public Receptor(String address, String name, double phoneNumber, DatePickerDialog receiveDateParcel, String email) {
        Address = address;
        Name=name;
        PhoneNumber = phoneNumber;
        ReceiveDateParcel= receiveDateParcel;
        Email=email;
    }
}
