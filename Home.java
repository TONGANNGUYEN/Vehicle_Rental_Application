package com.annguyen.riki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class Home extends AppCompatActivity {

    private ListView listView;
    private FloatingActionButton fab;
    private List<Item> itemList = new ArrayList<>();
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);

        listView = findViewById(R.id.list_view);
        fab = findViewById(R.id.fab);

        customAdapter = new CustomAdapter(this, R.layout.list_item, itemList);
        listView.setAdapter(customAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, fabutton.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String imagePath = data.getStringExtra("imagePath");
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");

            Item item = new Item(imagePath, name, description);
            itemList.add(item);
            customAdapter.notifyDataSetChanged();
        }
    }

    private class CustomAdapter extends ArrayAdapter<Item> {

        private LayoutInflater inflater;
        private List<Item> itemList;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Item> objects) {
            super(context, resource, objects);
            this.itemList = objects;
            this.inflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item, parent, false);
            }

            Item item = itemList.get(position);

            ImageView imageView = convertView.findViewById(R.id.image_view1);
            EditText nameTextView = convertView.findViewById(R.id.name_text_view);
            EditText descriptionTextView = convertView.findViewById(R.id.description_text_view);

            Picasso.get().load(new File(item.getImagePath())).into(imageView);
            nameTextView.setText(item.getName());
            descriptionTextView.setText(item.getDescription());

            return convertView;
        }
    }
}


//public class Home extends AppCompatActivity {
//
//    private ListView listView;
//    private ArrayAdapter adapter;
//    private ArrayList<Item> itemList = new ArrayList<>();
//    private ImageView mImageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home2);
//
//        listView = findViewById(R.id.listView);
//        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, itemList);
//        listView.setAdapter(adapter);
//
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, fabutton.class);
//                startActivityForResult(intent, 1);
//            }
//        });
//
//        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                String selectedItem = itemList.get(position);
//                Intent intent = new Intent(Home.this, new.class);
//                intent.putExtra("selectedItem", selectedItem);
//                intent.putExtra("position", position);
//                startActivityForResult(intent, 2);
//            }
//        });
//
//        Button deleteButton = findViewById(R.id.delete_button);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = listView.getCheckedItemPosition();
//                if (position != ListView.INVALID_POSITION) {
//                    itemList.remove(position);
//                    adapter.notifyDataSetChanged();
//                }
//            }
//        });*/
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//
//
//
//
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
////            String imageUrl = data.getStringExtra("imageUri");
////            Uri imageUri = Uri.parse(imageUrl);
////            try {
////                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
////                mImageView.setImageBitmap(bitmap);
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//            String name = data.getStringExtra("name");
//            String description = data.getStringExtra("description");
//            Item item = new Item(name, description);
//            itemList.add(item);
//            adapter.notifyDataSetChanged();
//        }
//
//    }
//}



