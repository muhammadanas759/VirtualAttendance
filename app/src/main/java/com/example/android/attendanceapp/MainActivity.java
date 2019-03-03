package com.example.android.attendanceapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
private static final String TAG="MainActivity";
    TextView mNavName,mNavEmail;

     private CartModel[] cartModel;
    teacher mTeacher;
    // recycler View
    RecyclerView recycler_menu;
    RecyclerView.LayoutManager layoutManager;
    adapter adapter;

//    FireStore
    int NumberOfOfferCourses;
private FirebaseFirestore mFirebaseFirestore;
private DocumentReference teacherCollectoin,coursesCollection,classCollection;
Session session;
    ArrayList<String> mShiftName=new ArrayList<>();
    ArrayList<String> mClassnames=new ArrayList<>();
//getEmailofuser
     private String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        session=new Session(MainActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
mFirebaseFirestore=FirebaseFirestore.getInstance();

teacherCollectoin=mFirebaseFirestore.collection("teacher").document("abc");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();


        View headerView=navigationView.getHeaderView(0);
        mNavName=(TextView)headerView.findViewById(R.id.nav_name);
        mNavEmail = (TextView)headerView.findViewById(R.id.nav_email);
//        Bundle bundle=getIntent().getExtras();
//        userEmail=bundle.get("email").toString();
        mNavName.setText( user.get(Session.KEY_NAME));
        mNavEmail.setText(user.get(Session.KEY_EMAIL));


        addListner();
//        mShiftName.add("Morning");
//        mShiftName.add("Evening");
//        mShiftName.add("Morning");
//        mShiftName.add("Evening");
//        mShiftName.add("Morning");
//        mClassnames.add("BSCS-15");
//        mClassnames.add("BSCS-13");
//        mClassnames.add("BSCS-16");
//        mClassnames.add("BSCS-17");
//        mClassnames.add("BSCS-19");
//        adapter=new adapter(mShiftName,mClassnames);

        Log.d(TAG, "onSuccess: "+cartModel);
        adapter=new adapter(cartModel);
        recycler_menu=(RecyclerView)findViewById(R.id.recylerview);
        recycler_menu.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        recycler_menu.setLayoutManager(layoutManager);
        recycler_menu.setAdapter(adapter);
}

private void addListner(){
        teacherCollectoin.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d(TAG, "onSuccess: ");
            }
        });
//teacherCollectoin.get().addOnSuccessListener(
//        new OnSuccessListener<DocumentSnapshot>() {
//    @Override
//    public void onSuccess(DocumentSnapshot documentSnapshot) {
//        Toast.makeText(MainActivity.this, "ok", Toast.LENGTH_SHORT).show();
////        mTeacher = documentSnapshot.toObject(teacher.class);
////        NumberOfOfferCourses = Integer.parseInt(documentSnapshot.get("noOfCourseOffer").toString());
////        Log.d(TAG, "onSuccess: "+NumberOfOfferCourses );
// }
//        }).addOnFailureListener(new OnFailureListener() {
//    @Override
//    public void onFailure(@NonNull Exception e) {
//        Toast.makeText(MainActivity.this, "eror mssaege"+e.getMessage(), Toast.LENGTH_SHORT).show();
//    }
//});
    Log.d(TAG, "addListner: teacher");
        cartModel=new CartModel[3];
        for (int a=0;a<3;a++){
            cartModel[a]=new CartModel("bscs","A","20","morn","3","mathh");

        }

////        classCollection=mFirebaseFirestore.collection("classes").document((String) documentSnapshot.get(" BSCS-2015-Morning-A"));
////        coursesCollection=mFirebaseFirestore.collection("courses").document(mTeacher.getCourseOffer().get(i));
//
//        for ( int i=0;i<mTeacher.getNoOfCourseOffer();i++){
//            final int ii=i;
//            cartModel[i]=new CartModel();
////cartModel[i].setClassname(mTeacher.getClassOffer().get(0).toString());
////cartModel[i].setSubject(mTeacher.getCourseOffer().get(i));
//            coursesCollection=mFirebaseFirestore.collection("courses").document( " BSCS-2015-Morning-A");
////             final int finalI = i;
////            coursesCollection.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                @Override
////                public void onSuccess(DocumentSnapshot documentSnapshot) {
////                    Log.d(TAG, "onSuccess: course colloction"+documentSnapshot);
////                    cartModel[ii].setCredit((String) documentSnapshot.get("creditHour"));
//
////                }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////
////                }
////            });
////            classCollection=mFirebaseFirestore.collection("classes").document("PC");
////            final int I = i;
////            classCollection.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
////                @Override
////                public void onSuccess(DocumentSnapshot documentSnapshot) {
////                    Log.d(TAG, "onSuccess: class collection"+documentSnapshot);
////                    cartModel[I].setSection((String) documentSnapshot.get("section"));
////                    cartModel[I].setShiftOfmodel((String) documentSnapshot.get("shift"));
////                    cartModel[I].setNoOfStudent(String.valueOf(documentSnapshot.get("noOfStudents")));
//
//              }
////            }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NonNull Exception e) {
////
////                }
////            });
////}
//    }
//});
////    adapter=new adapter(cartModel);
}

//    /*private void addListener() {
//        reference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Category item=dataSnapshot.getValue(Category.class);
//                names.add(item.getName());
//                images.add(item.getImage());
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }*/

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_attendance) {
            startActivity(new Intent(MainActivity.this,tab.class));
            // Handle the camera action
        } else if (id == R.id.nav_chk_att) {

        } else if (id == R.id.nav_logout) {
new Session(MainActivity.this).logoutUser();


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public  void onclick(int pos){
        Toast.makeText(this, "   "+pos, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,tab.class));

//        Intent foodlist=new Intent(Home.this,FoodList.class);
//        //foodlist.putExtra("CategoryId",pos);
//        String position=String.valueOf(pos);
//        foodlist.putExtra("CategoryId",position);
//        startActivity(foodlist);

    }
}
class adapter extends RecyclerView.Adapter<adapter.ViewHolder>{
    private static final String TAG="adapter";
    CartModel[] cm;

    public adapter(CartModel[] cartmodel) {
 //       cm=new CartModel[3];
        this.cm=cartmodel;
    }


    @Override
    public adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(adapter.ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: "+position);
        //        final String name=this.mClassname.get(position);
//        final String shift=this.mShiftname.get(position);
        holder.txtMenuName.setText(cm[position].getClassname());
        holder.txtMenuClass.setText(cm[position].getShiftOfmodel());
        holder.mcartsub.setText(cm[position].getSubject());
        holder.mcartstu.setText(cm[position].getNoOfStudent());
        holder.mcartcredit.setText(cm[position].getCredit());
        holder.mcartsec.setText(cm[position].getSection());
//        Picasso.get().load(image).into(holder.imageView);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)view.getContext()).onclick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount:3 ");
        return cm.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public TextView txtMenuName,txtMenuClass;
        //TextView For Cart
        TextView mcartsub,mcartsec,mcartstu,mcartcredit;

        //        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            txtMenuName=(TextView)itemView.findViewById(R.id.menu_name);
            txtMenuClass=(TextView)itemView.findViewById(R.id.menu_class);

            mcartcredit=(TextView)itemView.findViewById(R.id.cart_credit);
            mcartsec=(TextView)itemView.findViewById(R.id.cart_section);
            mcartstu=(TextView)itemView.findViewById(R.id.cart_std);
            mcartsub=(TextView)itemView.findViewById(R.id.cart_sub);
//            imageView=(ImageView)itemView.findViewById(R.id.menu_image);
        }
    }
}