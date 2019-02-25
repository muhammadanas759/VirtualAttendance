package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private static final String TAG="SignUp";
    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;


    private String mStringName;
    private String mStringEmail;
    private String mStringPassword;
    private Button mSignUp;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseAuth mFirebaseAuth;

    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;
    private FirebaseFirestore mFirebaseFirestore=FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("message:", "onCreate: signUP");
        super.onCreate(savedInstanceState);
        Log.d("message:", "onCreate: signUP");
        setContentView(R.layout.activity_sign_up);


        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);
        mName = (EditText) findViewById(R.id.name);
        mSignUp = (Button) findViewById(R.id.sign_up);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference("User");
        mFirebaseAuth = FirebaseAuth.getInstance();


        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });
    }


    public void registerUser() {


        mStringName = mName.getText().toString().trim();
        mStringEmail = mEmail.getText().toString().trim();
        mStringPassword = mPassword.getText().toString().trim();
        boolean chk1 = false, chk2 = false, chk3 = false, chk4 = false, chk5 = false;
        if (mStringName.isEmpty()) {
            mName.setError("Enter your Name!");
            mName.requestFocus();


        } else {
            chk1 = !chk1;
        }
        if (mStringEmail.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(mStringEmail).matches()) {
            mEmail.setError("Enter your Email!");
            mEmail.requestFocus();


        } else
            chk3 = !chk3;
        if (mStringPassword.isEmpty() || mStringPassword.length() < 6) {
            mPassword.setError("Enter your Password!");
            mPassword.requestFocus();
            chk4 = false;

        } else {
            chk4 = !chk4;
        }
        if (chk1 && chk3 && chk4)
            Log.d(TAG, "registerUser: all true"+mStringEmail);

            mFirebaseAuth.createUserWithEmailAndPassword(mStringEmail, mStringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    {

                        if (task.isSuccessful()){
                            Map<String,Object> obj=new HashMap<>();
                            obj.put("name",mStringName);
                            obj.put("password",mStringEmail);

                            mFirebaseFirestore.collection("teacher").document(mStringEmail).set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(SignUp.this, "Added Successful", Toast.LENGTH_SHORT).show();

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUp.this, "Fail to added"+e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });



                            Log.d(TAG, "onComplete: is successful");
                            String id=mFirebaseAuth.getUid();
                            User currentUser = new User(mStringName,mStringPassword,mStringEmail);
                            mDatabaseReference = mDatabaseReference.child(id);
                            mDatabaseReference.setValue(currentUser);
//                            Intent intent = new Intent(getApplicationContext(), chk.class);
//                            intent.putExtra("name", currentUser.getName());
//                            intent.putExtra("email", currentUser.getEmail());
//
//                            startActivity(
//                                    intent
//                            );
                            finish();
                        }
                        else{
                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(SignUp.this, "Already signed in with this email", Toast.LENGTH_SHORT).show();
                            }
                            else{


                                Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("error",e.getMessage());
                }
            });
    }
}