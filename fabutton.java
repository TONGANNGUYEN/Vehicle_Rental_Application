package com.annguyen.riki;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;


//public class fabutton extends AppCompatActivity {
//    private EditText mNameEditText;
//    private EditText mDescriptionEditText;
//    private Button mAddImageButton;
//    private Button mSaveButton;
//    private ImageView mImageView;
//
//    private static final int PICK_IMAGE_REQUEST = 1;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fabutton);
//
//        mNameEditText = findViewById(R.id.nameEditText);
//        mDescriptionEditText = findViewById(R.id.descriptionEditText);
//        mSaveButton = findViewById(R.id.saveButton);
////        mAddImageButton = findViewById(R.id.addImageButton);
////        mImageView = findViewById(R.id.imageView);
//
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String name = mNameEditText.getText().toString();
//                String description = mDescriptionEditText.getText().toString();
//
//                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(description)) {
//                    Intent intent = new Intent();
//                    intent.putExtra("name", name);
//                    intent.putExtra("description", description);
//                    setResult(RESULT_OK, intent);
//                    finish();
////                    mAddImageButton.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////                            Intent intent = new Intent();
////                            intent.setType("image/*");
////                            intent.setAction(Intent.ACTION_GET_CONTENT);
////                            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
////                        }
////                    });
//
//                }
////                else {
////                    Toast.makeText(fabutton.this, "Please enter name and description", Toast.LENGTH_SHORT).show();
////                }
//            }
//        });
//
//
//
//
//    }
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////
////        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
////            Uri uri = data.getData();
////            try {
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
////                mImageView.setImageBitmap(bitmap);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////            // đặt Uri của hình ảnh vào trong Intent
////            Intent intent = new Intent(this, Home.class);
////            intent.putExtra("imageUri", uri.toString());
////            startActivity(intent);
////
////        }
////    }
//}
public class fabutton extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private ImageView mImageView;
    private Button mSelectImageButton;
    private Button mSaveButton;
    private String mImagePath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fabutton);

        mNameEditText = findViewById(R.id.edit_text_name);
        mDescriptionEditText = findViewById(R.id.edit_text_description);
        mImageView = findViewById(R.id.image_view);
        mSelectImageButton = findViewById(R.id.button_choose_image);
        mSaveButton = findViewById(R.id.button_save);

        mSelectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();
                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("imagePath", mImagePath);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            mImagePath = getRealPathFromURI(selectedImageUri);
            Picasso.get().load(new File(mImagePath)).into(mImageView);
        }
        else {
            mImageView.setImageResource(R.drawable.avatar);
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
}


//hãy tạo cho tôi 2 activity trong android studio. Trong đó, activity 1 gồm 1 listView và 1 floating action button. Activity 2 gồm 2 textView điền name và description và 1 save button để lưu thành 1 List Item trong activity 1
