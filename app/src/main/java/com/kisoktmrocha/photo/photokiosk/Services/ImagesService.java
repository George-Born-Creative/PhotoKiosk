package com.kisoktmrocha.photo.photokiosk.Services;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kisoktmrocha.photo.photokiosk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tmrocha on 11/11/2016.
 */

public class ImagesService {

    private Context context;
    private ImageView imageView;
    private Handler handler;
    private ImageSwitcher imageSwitcher;
    final private static int DELAY = 1000 * 20;

    private int imageIndex;
    private List<String> listOfAllImages;


    public ImagesService(Context context,View view){
        this.context = context;
        imageIndex = 0;
        listOfAllImages = new ArrayList<>();
        getAllShownImagesPath();
        imageView = (ImageView) view.findViewById(R.id.imageView);
        handler = new Handler(Looper.getMainLooper());
        imageSwitcher = new ImageSwitcher();
        handler.postDelayed(imageSwitcher, 0);

    }


    private void getAllShownImagesPath() {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = context.getContentResolver().query(uri, projection, null, null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);

            listOfAllImages.add(absolutePathOfImage);
        }
    }

    private class ImageSwitcher implements Runnable{


        @Override
        public void run() {
            if(imageIndex >= listOfAllImages.size()){
                imageIndex = 0;
            }
            Glide.with(context)
                    .load(listOfAllImages.get(imageIndex))
                    .skipMemoryCache(true)
                    .crossFade(5000)
                    .into(imageView);
            imageIndex++;
            handler.postDelayed(imageSwitcher, DELAY);
        }
    }
}
