package com.example.first_application.Entities;

import android.app.DatePickerDialog;

public class Receptor {
    private String Address;
    private String Name;
    private String PhoneNumber;
    private DatePickerDialog ReceiveDateParcel;
    private String Email;

    //getter and setter
    public void setPhoneNumber(String phoneNumber) { PhoneNumber = phoneNumber; }
    public String getPhoneNumber() { return PhoneNumber; }

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
    public Receptor(){} //pour main activity

    public Receptor(String address, String name, String phoneNumber, DatePickerDialog receiveDateParcel, String email) {
        Address = address;
        Name=name;
        PhoneNumber = phoneNumber;
        ReceiveDateParcel= receiveDateParcel;
        Email=email;
    }




}
