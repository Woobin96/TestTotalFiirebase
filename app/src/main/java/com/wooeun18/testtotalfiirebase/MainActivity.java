package com.wooeun18.testtotalfiirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et= findViewById(R.id.et);
        tv= findViewById(R.id.tv);
    }

    public void clickSend(View view) {
        String text= et.getText().toString();

        FirebaseDatabase firebaseDatabase= FirebaseDatabase.getInstance();
        DatabaseReference rootRef= firebaseDatabase.getReference();

//        rootRef.setValue(text);
//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String data= snapshot.getValue(String.class);
//                tv.setText(data);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        String name= et.getText().toString();
        int age= 26;
        String address="KR";
        
        PersonVO personVO= new PersonVO(name, age, address);
        DatabaseReference perRef= rootRef.child("persons");
        perRef.push().setValue(personVO);


        perRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                
                StringBuffer buffer= new StringBuffer();
                for (DataSnapshot ss : snapshot.getChildren()){
                    PersonVO per= ss.getValue(PersonVO.class);
                    String name= per.name; 
                    int age= per.age; 
                    String address1= per.address;
                    
                    buffer.append(name+ ","+ age+ ","+ address1 + "\n");
                }
                tv.setText(buffer.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}//Main










