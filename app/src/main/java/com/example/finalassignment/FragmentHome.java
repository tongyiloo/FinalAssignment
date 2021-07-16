package com.example.finalassignment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;


public class FragmentHome extends Fragment{

    public  FragmentHome(){}


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        VideoView videoView = view.findViewById(R.id.videoView);
        String videoPath = "android.resource://" +getContext().getPackageName() + "/" + R.raw.librarium_intro;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(getContext());
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        CardView cvBooks = view.findViewById(R.id.cardViewBooks);
        CardView cvLibrary = view.findViewById(R.id.cardViewLibrary);

        cvBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), BooksActivity.class);
                startActivity(intent);
            }
        });

        cvLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), MyLibraryActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }


}