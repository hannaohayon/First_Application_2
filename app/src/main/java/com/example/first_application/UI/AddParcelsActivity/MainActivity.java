package com.example.first_application.UI.AddParcelsActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.first_application.Data.ParcelDataSource;
import com.example.first_application.Entities.Parcel;
import com.example.first_application.Entities.ParcelStatus;
import com.example.first_application.Entities.ParcelType;
import com.example.first_application.Entities.ParcelWeight;
import com.example.first_application.Entities.Receptor;
import com.example.first_application.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    //ne pas oublier progress bar!!


    private EditText sender_name;
    private EditText receptor_name;
    private EditText address;
    private EditText phone_number;
    private EditText email;
    private EditText expedition_date;
    private EditText parcel_id;
    private CheckBox fragile_or_not;
    private Spinner parcel_type;
    private Spinner parcel_weight;
    private Spinner parcel_status;
    private Button Add_parcel;
    private Button History_parcels;

    private void findViews()
    {
        sender_name= (EditText) findViewById(R.id.sender_name);
        receptor_name=(EditText) findViewById(R.id.receptor_name);
        address=(EditText) findViewById(R.id.address);
        phone_number=(EditText) findViewById(R.id.phone_number);
        email=(EditText) findViewById(R.id.email);
        expedition_date=(EditText) findViewById(R.id.expedition_date);
        parcel_id=(EditText)findViewById(R.id.parcel_id);

        fragile_or_not=(CheckBox) findViewById(R.id.fragile_or_not);

        Add_parcel=(Button) findViewById(R.id.Add_parcel);
        History_parcels=(Button) findViewById(R.id.History_parcels);

        parcel_type=(Spinner) findViewById(R.id.parcel_type);
        parcel_type.setAdapter(new ArrayAdapter<ParcelType>(this,android.R.layout.simple_list_item_1,ParcelType.values()));

        parcel_weight=(Spinner) findViewById(R.id.parcel_weight);
        parcel_weight.setAdapter(new ArrayAdapter<ParcelWeight>(this,android.R.layout.simple_list_item_1,ParcelWeight.values()));

        parcel_status=(Spinner) findViewById(R.id.parcel_status);
        parcel_status.setAdapter(new ArrayAdapter<ParcelStatus>(this,android.R.layout.simple_list_item_1,ParcelStatus.values()));

    }
 //onClick for the buttons
    public void onClick(View v)
    {
        if (v==Add_parcel)
        {
            addParcel();
        }
    }

    //onclick for CheckBoxes
    public void onCheckBoxClick(View v)
    {
        if (fragile_or_not.isChecked()) {
            Toast.makeText(getApplicationContext(), "Fragile is selected", Toast.LENGTH_LONG).show();
            fragile_or_not.setChecked(true);
        }
        else fragile_or_not.setChecked(false);
    }


    private void addParcel() {
        try {

            Parcel parcel = getParcel();
            Add_parcel.setEnabled(false);

            ParcelDataSource.addParcel(parcel, new ParcelDataSource.IAction<Long>() {
                @Override
                public void onSuccess(Long obj) {
                    Toast.makeText(getBaseContext(), "insert id " + obj, Toast.LENGTH_LONG).show();
                   Add_parcel.setEnabled(true);
                }

                @Override
                public void onFailure(Exception exception) {
                    Toast.makeText(getBaseContext(), "Error \n" + exception.getMessage(), Toast.LENGTH_LONG).show();
                    Add_parcel.setEnabled(true);
                }

                @Override
                public void onProgress(String status, double percent) {
                    if (percent != 100)
                        Add_parcel.setEnabled(false);
                  //  addStudentProgressBar.setProgress((int) percent);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), "Error ", Toast.LENGTH_LONG).show();
            Add_parcel.setEnabled(true);
        }

    }

    private Parcel getParcel(){
        Parcel parcel = new Parcel();
        Receptor receptor=new Receptor();

        //parcel.setFragile(this.fragile_or_not.get);
        parcel.setSenderName(this.sender_name.getText().toString());

        receptor.setName(this.receptor_name.getText().toString());

        receptor.setAddress(this.address.getText().toString());

        receptor.setPhoneNumber(this.phone_number.getText().toString());

        receptor.setEmail(this.email.getText().toString());

        Long parcel_id=Long.valueOf(this.parcel_id.getText().toString());
        parcel.setId(parcel_id);

        ParcelType parcelType = ((ParcelType) parcel_type.getSelectedItem());
        parcel.setParcelType(parcelType);

        ParcelWeight parcelWeight = ((ParcelWeight) parcel_weight.getSelectedItem());
        parcel.setParcelWeight(parcelWeight);

        ParcelStatus parcelStatus = ((ParcelStatus) parcel_status.getSelectedItem());
        parcel.setParcelStatus(parcelStatus);

        parcel.setReceptor(receptor);

       parcel.setFragile(onCheckBoxClick());

        return parcel;
        //on s'est arrété ici 15.12
        //FAIRE CHECK BOX!!!!!

    }
}


