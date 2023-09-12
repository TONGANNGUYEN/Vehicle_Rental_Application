package com.annguyen.riki;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private int mResource;
    private List<String> mItems;

    public CustomAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        mContext = context;
        mResource = resource;
        mItems = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Lấy ra item hiện tại
        String currentItem = mItems.get(position);

        // Tạo ra View mới hoặc sử dụng lại View đã tồn tại
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        }

        // Lấy ra các thành phần của item
        ImageView imageView = convertView.findViewById(R.id.image_view1);
        EditText nameTextView = convertView.findViewById(R.id.name_text_view);
        EditText descriptionTextView = convertView.findViewById(R.id.description_text_view);

        // Cập nhật các giá trị của các thành phần trong item
        // Ở đây, chúng ta giả sử chuỗi currentItem có định dạng "imagePath,name,description"
        String[] parts = currentItem.split(",");
        String imagePath = parts[0];
        String name = parts[1];
        String description = parts[2];

        // Sử dụng thư viện Picasso để tải hình ảnh vào ImageView
        Picasso.get().load(new File(imagePath)).into(imageView);

        // Đặt giá trị cho các TextView
        nameTextView.setText(name);
        descriptionTextView.setText(description);

        return convertView;
    }
}

//public class CustomAdapter extends ArrayAdapter<Item> {
//
//    private ArrayList<Item> items;
//
//    public CustomAdapter(Context context, int resource, ArrayList<Item> items) {
//        super(context, resource, items);
//        this.items = items;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//        }
//        Item item = items.get(position);
//        ImageView imageView = convertView.findViewById(R.id.image_view);
//        TextView nameTextView = convertView.findViewById(R.id.name_text_view);
//        TextView descriptionTextView = convertView.findViewById(R.id.description_text_view);
//        Picasso.get().load(new File(item.getImagePath())).into(imageView);
//        nameTextView.setText(item.getName());
//        descriptionTextView.setText(item.getDescription());
//        return convertView;
//    }
//}

