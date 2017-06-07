package com.example.trang.tapbitextgram.manager;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.example.trang.tapbitextgram.enity.ImageLoadSdCard;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Trang on 6/7/2017.
 */

public class ImageManager {
    private static ImageManager instance;
    private ArrayList<ImageLoadSdCard> imageArrayList = null;
    private String name;
    private File file;

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    public ArrayList<ImageLoadSdCard> loadImageExternalStorage() {

        imageArrayList = new ArrayList<>();
        file = new File(Environment.getExternalStorageDirectory().getPath() +
                "/" + "Beautiful");
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                String path = files[i].getAbsolutePath();
                name = path.substring(path.length() - 17, path.length());
                if (!files[i].isDirectory()) {
                    imageArrayList.add(new ImageLoadSdCard(Uri.parse(path), name));
                }
            }
        }

        return imageArrayList;

    }

    public void deleteItemImage(String namePosition) {
        new File(Environment.getExternalStorageDirectory().getPath() +
                "/" + "Beautiful" + "/" + "Beautiful" + "-" + namePosition).delete();

    }
}

