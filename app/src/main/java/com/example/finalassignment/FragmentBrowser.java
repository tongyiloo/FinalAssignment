package com.example.finalassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;


public class FragmentBrowser extends Fragment {

    public  FragmentBrowser(){}

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_browser, container, false);

        Button btnGoogle = (Button) view.findViewById(R.id.btnGoogle);
        Button btnGoogleSch = (Button) view.findViewById(R.id.btnGoogleScholar);

        // Attach a listener to button for browser google website
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab the data from Uri.parse
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/"));
                startActivity(intent);
            }
        });

        // Attach a listener to button for browser google scholar website
        btnGoogleSch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //grab the data from Uri.parse
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://scholar.google.com/"));
                startActivity(intent);
            }
        });

        return view;
    }
}