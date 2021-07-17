package com.example.finalassignment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;


public class FragmentProfile extends Fragment implements View.OnClickListener{


    public FragmentProfile(){}

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        CardView c1,c2,c3;

        //find CardView and assign object
        c1 = view.findViewById(R.id.cardView1);
        c2 = view.findViewById(R.id.cardView2);
        c3 = view.findViewById(R.id.cardView3);

        //assign onClickListener to every object
        c1.setOnClickListener((View.OnClickListener) this);
        c2.setOnClickListener((View.OnClickListener) this);
        c3.setOnClickListener((View.OnClickListener) this);

        return view;
    }

    //This method will use switch case and intent, will open switch case
    //whenever click on particular view it will get the id of the view to the activity
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.cardView1: intent = new Intent (v.getContext(), AboutActivity.class);startActivity(intent); break;
            case R.id.cardView2: intent = new Intent (v.getContext(),HelpActivity.class);startActivity(intent); break;
            case R.id.cardView3: intent = new Intent (v.getContext(),LoginActivity.class);startActivity(intent); break;
        }
    }
}