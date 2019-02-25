package com.example.android.attendanceapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Attendees {

    public String names;
    public String date;

    public Attendees() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Attendees(String names, String date) {
        this.names  = names;
        this.date = date;
    }

}

public class Tab1Fragment extends Fragment{

    private List<String> commitList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_tab1, container, false);

//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView recyclerView =rootview.findViewById(R.id.recyclerView);

        Button commitButton = (Button) rootview.findViewById(R.id.button);
        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                commit();
            }
        });
//
//        Toolbar toolbar = (Toolbar)getActivity().findViewById(R.id.app_bar);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//
//        if(getSupportActionBar() != null) {
//            getSupportActionBar().setTitle("Review and Mark");
//        }

        List<String> reviewList = Arrays.asList("anas","taha");

        ReviewListAdapter reviewListAdapter = new ReviewListAdapter(getContext(), reviewList);
       
        recyclerView.setAdapter(reviewListAdapter);

        //Setting LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        /*//For adding dividers in the list
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this, R.drawable.line_divider));
        recyclerView.addItemDecoration(dividerItemDecoration);*/
        return rootview;

    }


    public void onItemClick(String name) {
        if(commitList.contains(name))
            commitList.remove(name);
        else
            commitList.add(name);
    }

    public void commit() {

        if(commitList.size() != 0) {
//            Enable firebase and then uncomment the following lines

//            FirebaseDatabase database = FirebaseDatabase.getInstance();
//            DatabaseReference myRef = database.getReference("attendence");

//            convert to a comma separated string
//            this has to be the worst way to push data to a db
//            StringBuilder sb = new StringBuilder();
//            for (String s : commitList) {
//                sb.append(s);
//                sb.append(",");
//            }

//            Attendees at = new Attendees(sb.toString(), (new Date()).toString());
//            String key = myRef.push().getKey();
//            myRef.child(key).setValue(at);

            Toast.makeText(getContext(), "Enable firebase for this to work", Toast.LENGTH_LONG).show();
//            finish();


//            System.out.println(sb.toString());

        }
        else {
            Toast.makeText(getContext(), "Please select at least one student", Toast.LENGTH_SHORT).show();
        }
    }

    }


