package com.example.first_application.Data;

import com.example.first_application.Entities.Parcel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class ParcelDataSource {

    public interface IAction<T>
            // to receive an information about the activity of a function
    {
        void onSuccess(T obj);
        void onFailure(Exception exception);
        void onProgress(String status, double percent);
    }

    public interface INotifyDataChange<T>
            //to update data
    {
        void OnDataChanged(T obj);
        void onFailure(Exception exception);
    }

    static FirebaseDatabase database = FirebaseDatabase.getInstance();
    static DatabaseReference ParcelRef = database.getReference("Parcels");
    static List<Parcel> parcelList = new ArrayList<>();


    public static void addParcel(final Parcel parcel, final IAction<Long> action) {
        String key = String.valueOf(parcel.getId());
        ParcelRef.child(key).setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>(){
               /* database.getReference("Parcels").push().setValue(parcel).addOnSuccessListener(new OnSuccessListener<Void>() {*/
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(parcel.getId());
                action.onProgress("upload parcel data", 100);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
                action.onProgress("error upload parcel data", 100);
            }
        });
    }

    public static void removeParcel(final Parcel parcel, final IAction<Long> action) {
      String key=  String.valueOf(parcel.getId());
        ParcelRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>(){
        /*database.getReference("Parcels").push().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {*/
            @Override
            public void onSuccess(Void aVoid) {
                action.onSuccess(parcel.getId());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                action.onFailure(e);
            }
        });

    }

   /*public static void updateParcel(final Parcel toUpdate, final IAction<Boolean> action) {
        final String key = ((Long) toUpdate.getId()).toString();

        removeParcel(toUpdate.getId(), new Action<Long>() {
            @Override
            public void onSuccess(Long obj) {
                addStudent(toUpdate, action);
            }

            @Override
            public void onFailure(Exception exception) {
                action.onFailure(exception);
            }

            @Override
            public void onProgress(String status, double percent) {
                action.onProgress(status, percent);
            }
        });*/

   private static ChildEventListener parcelRefChildEventListener;

   public static void notifyToParcelList(final INotifyDataChange<List<Parcel>> notifyDataChange)
   {
       if (notifyDataChange != null)
     {

       if (parcelRefChildEventListener != null) {
           notifyDataChange.onFailure(new Exception("first unNotify student list"));
           return;
       }
       parcelList.clear();

       parcelRefChildEventListener = new ChildEventListener() {
           @Override
           public void onChildAdded(DataSnapshot dataSnapshot, String s) {
               Parcel parcel = dataSnapshot.getValue(Parcel.class);
               String id = dataSnapshot.getKey();
               parcel.setId(Long.parseLong(id));
               parcelList.add(parcel);


               notifyDataChange.OnDataChanged(parcelList);
           }


           @Override
           public void onChildRemoved(DataSnapshot dataSnapshot) {
               Parcel parcel = dataSnapshot.getValue(Parcel.class);
               Long id = Long.parseLong(dataSnapshot.getKey());
               parcel.setId(id);

               for (int i = 0; i < parcelList.size(); i++) {
                   if (parcelList.get(i).getId() == id) {
                       parcelList.remove(i);
                       break;
                   }
               }
               notifyDataChange.OnDataChanged(parcelList);
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               notifyDataChange.onFailure(databaseError.toException());
           }

           @Override
           public void onChildMoved(DataSnapshot dataSnapshot, String s) {
           }

           @Override
           public void onChildChanged(DataSnapshot dataSnapshot, String s) { }
       };
       ParcelRef.addChildEventListener(parcelRefChildEventListener);

   }
   }

    public static void stopNotifyToStudentList() {
        if (parcelRefChildEventListener != null) {
            ParcelRef.removeEventListener(parcelRefChildEventListener);
            parcelRefChildEventListener = null;
        }
    }
}