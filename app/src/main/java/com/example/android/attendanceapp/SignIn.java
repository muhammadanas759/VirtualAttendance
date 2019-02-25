package com.example.android.attendanceapp;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {
//button

    Session session;

    private TextView mSignUp;
    private Button mSignIn;
    private EditText mPassword;
    private EditText mEmail;

    private String mStringEmail;
    private String mStringPassword;
    private String mUserID;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildEventListener;
    public static SharedPreferences mSharedPrefrences;
    public static SharedPreferences.Editor mEditor;
    private ProgressBar mProgressBar;
    private LinearLayout mLinearLayout;
    Button mlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);

        session =new Session(SignIn.this);

        if(session.isLoggedIn()){
            startActivity(new Intent(SignIn.this,MainActivity.class));

        }
        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
//mProgressBar=new ProgressBar(SignIn.this);
        mEmail=(EditText)findViewById(R.id.emailSignIn);
        mPassword=(EditText)findViewById(R.id.passwordSignIn);
        mFirebaseAuth= FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        mDatabaseReference=mFirebaseDatabase.getReference("User");
        mPassword.setText("123456");
        mEmail.setText("a@gmail.com");


        mSignUp=(TextView)findViewById(R.id.signupAtlogin);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(
                        new Intent(getApplicationContext(),
                                SignUp.class)
                );
                finish();
            }
        });
        mSignIn=(Button)findViewById(R.id.login);
        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });
    }

    private void loginUser() {
        mProgressBar.setVisibility(View.VISIBLE);

        //        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);
//        mLinearLayout=(LinearLayout)findViewById(R.id.l_layout_2);

        mStringEmail = mEmail.getText().toString().trim();
        mStringPassword = mPassword.getText().toString().trim();

        boolean chk1=false,chk2=false;


        if(mStringEmail.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(mStringEmail).matches()){

            mEmail.setError("Enter your Email!");
            mEmail.requestFocus();


        }else{
            chk1=!chk1;
        }
        if(mStringPassword.isEmpty()||mStringPassword.length()<6)
        {
            mPassword.setError("Enter your Password!");
            mPassword.requestFocus();
        }else{
            chk2=!chk2;
        }
        if(chk1&&chk2){

            mFirebaseAuth.signInWithEmailAndPassword(mStringEmail,mStringPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){
                        attachListener();
                    }
                    else{

                        mProgressBar.setVisibility(View.GONE);
                        Toast.makeText(SignIn.this, "No User Found", Toast.LENGTH_SHORT).show();
                    }
//
//                startActivity(
//                        new Intent (
//                                getApplicationContext(),
//                                ParkingPlaces.class
//                        )
//                );
//                finish();
                }

            });
        }
            else{
            mProgressBar.setVisibility(View.GONE);

        }
    }
private static final String TAG="SignIn";

    private void attachListener() {
        Log.d(TAG, "attachListener: ");
        mUserID=mFirebaseAuth.getUid();

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren())
                    if(snap.getKey().toString().equals(mUserID)) {

                        User currentUser = snap.getValue(User.class);

                        session.createLoginSession(currentUser.getName(),mStringEmail);


                        Toast.makeText(getApplicationContext(), "Login Successfull!", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("name",currentUser.getName());
                        intent.putExtra("email",currentUser.getEmail());

                        mProgressBar.setVisibility(View.GONE);
                        startActivity(intent);
                        finish();


                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
