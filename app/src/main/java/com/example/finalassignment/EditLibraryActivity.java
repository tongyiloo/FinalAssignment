package com.example.finalassignment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.jetbrains.annotations.NotNull;

public class EditLibraryActivity extends AppCompatActivity {

    private ImageView pImageView;
    private TextInputEditText pTitleEt, pDescriptionEt;
    Button btnSave;
    ActionBar actionBar;

    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int STORAGE_REQUEST_CODE = 101;

    private static final int IMAGE_PICK_CAMERA_CODE = 102;
    private static final int IMAGE_PICK_GALLERY_CODE = 103;

    private String[] cameraPermission;
    private String[] storagePermission;

    private Uri imageUri;

    private String lid, title, description, addTimeStamp, updateTimeStamp;
    private boolean editMode = false;
    private DatabaseHelperLibrary dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_library);
        actionBar = getSupportActionBar();
        actionBar.setTitle("My Library");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //Link XML file component to java by calling findViewById() method
        pImageView = findViewById(R.id.imageviewDetail);
        pTitleEt = findViewById(R.id.inputTitle);
        pDescriptionEt = findViewById(R.id.inputDetail);

        btnSave = findViewById(R.id.btnSaveInfo);

        // method is for get the data(key) which is send by intent.putExtra("editMode", false)
        // getExtra() fetches data which was added using putExtra()
        Intent intent = getIntent();
        editMode = intent.getBooleanExtra("editMode", editMode);
        lid = intent.getStringExtra("ID");
        title = intent.getStringExtra("TITLE");
        description = intent.getStringExtra("DESCRIPTION");
        imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
        addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
        updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

        if(editMode){
            actionBar.setTitle("Update Information");
            editMode = intent.getBooleanExtra("editMode", editMode);
            lid = intent.getStringExtra("ID");
            title = intent.getStringExtra("TITLE");
            description = intent.getStringExtra("DESCRIPTION");
            imageUri = Uri.parse(intent.getStringExtra("IMAGE"));
            addTimeStamp = intent.getStringExtra("ADD_TIMESTAMP");
            updateTimeStamp = intent.getStringExtra("UPDATE_TIMESTAMP");

            // set data from database to display on TextInputEditText
            pTitleEt.setText(title);
            pDescriptionEt.setText(description);

            // set data from database and display on TextInputEditText
            // if imageUri is null, setImageResource
            if(imageUri.toString().equals("null")){
                pImageView.setImageResource(R.drawable.ic_baseline_add_a_photo_24);
            }
            // set Image to display on ImageView
            else {
                pImageView.setImageURI(imageUri);
            }
        }

        else {
            actionBar.setTitle("Add Information");
        }

        //required to be able to access the camera device
        //allows app to write to external storage
        cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        //initiate database object in main function
        dbHelper = new DatabaseHelperLibrary(this);

        //Attach a listener to the imageView
        pImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePickDialog();

            }
        });

        //Attach a listener to the imageView
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when click on save button insert the data to db
                getData();
                startActivity(new Intent(EditLibraryActivity.this, MyLibraryActivity.class));
                Toast.makeText(EditLibraryActivity.this, "Update successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //get data title, description
    private void getData() {
        title = ""+pTitleEt.getText().toString().trim();
        description = ""+pDescriptionEt.getText().toString().trim();

        // if update data
        if (editMode) {
            String newUpdateTime = ""+System.currentTimeMillis();
            dbHelper.updateInfo(
                    ""+lid,
                    ""+title,
                    ""+description,
                    ""+imageUri,
                    ""+addTimeStamp,
                    ""+updateTimeStamp
            );
        }
        //else insert new data
        else {
            String timeStamp = "" + System.currentTimeMillis();
            dbHelper.insertInfo(
                    "" + title,
                    "" + description,
                    "" + imageUri,
                    "" + timeStamp,
                    "" + timeStamp
            );
        }

    }
    // pick image from storage function
    private void imagePickDialog() {

        String[] options = {"Camera", "Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //dialog show options to choose "Camera" and "Gallery"
        builder.setTitle("Select for image");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(which ==0){
                    // if 0 then open camera and also check the permission of camera
                    if(!checkCameraPermission()){
                        requestCameraPermission();
                    }
                    else {
                        pickFromCamera();
                    }
                }
                else if(which == 1){
                    if (!checkStoragePermission()){
                        requestStoragePermission();
                    }
                    else {
                        pickFromStorage();
                    }
                }

            }
        });
        builder.create().show();
    }

    private void pickFromStorage() {

        //this function grab image from gallery/data source and return what was selected
        Intent galleryIntent = new Intent(Intent.ACTION_PICK);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, IMAGE_PICK_GALLERY_CODE);
    }

    //  takes  cameraIntent as an input and returns an ActivityResult
    private void pickFromCamera() {
        // get image from camera
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "Image title");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Image description");

        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(cameraIntent, IMAGE_PICK_CAMERA_CODE);
    }

    // Determine whether have been granted write external storage permission
    //if have the permission returns permission granted result
    private boolean checkStoragePermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);
        return result;
    }

    //and request for permission if not granted already.
    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this, storagePermission, STORAGE_REQUEST_CODE);
    }

    // Determine whether have been granted write external storage and camera permission
    // if have the permission returns permission granted result
    private boolean checkCameraPermission(){
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == (PackageManager.PERMISSION_GRANTED);

        boolean result1 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == (PackageManager.PERMISSION_GRANTED);

        return result && result1;
    }

    //and request for permission if not granted already.
    private void requestCameraPermission(){
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE);
    }

    //Callback for the result from requesting permissions.
    //The grant results for the corresponding permissions which is PackageManager.PERMISSION_GRANTED
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){

            case CAMERA_REQUEST_CODE: {

                if(grantResults.length>0){

                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean storageAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    // if permission granted access camera, capture image and write storage
                    if (cameraAccepted && storageAccepted){
                        pickFromCamera();
                    }
                    else {
                        Toast.makeText(this, "Camera Permission required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            break;

            case STORAGE_REQUEST_CODE: {
                if (grantResults.length>0){
                    boolean storageAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (storageAccepted){
                        pickFromStorage();
                    }
                    else {
                        Toast.makeText(this, "Storage Permission required", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }

    //start an activity for result and get the result from the activity select image, the result will set to imageUri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {

        // first add image crop library
        if (resultCode == RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                CropImage.activity(data.getData())
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1, 1)
                        .start(this);
            }
            else if (requestCode == IMAGE_PICK_CAMERA_CODE){

                CropImage.activity(imageUri)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setAspectRatio(1,1)
                        .start(this);
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK){
                    Uri resultUri = result.getUri();
                    imageUri = resultUri;
                    pImageView.setImageURI(resultUri);
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception error = result.getError();
                    Toast.makeText(this, ""+error, Toast.LENGTH_SHORT).show();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public boolean onSupportNavigateUp() {
        // this function moves the addlibrary activity to previous activity when back button pressed.
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}