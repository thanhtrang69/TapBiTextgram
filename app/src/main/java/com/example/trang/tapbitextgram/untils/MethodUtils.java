package com.example.trang.tapbitextgram.untils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;

import com.example.trang.tapbitextgram.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Created by Trang on 6/7/2017.
 */

public class MethodUtils {
    public MethodUtils() {
    }

    public static void SaveImage(final Context context, Bitmap bitmap) {
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();
        File myDir = new File(root + context.getString(R.string.nameFolder));
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setMessage("Saving...");
        dialog.show();
        String fname = "Beautiful" + "-" + Calendar.getInstance().getTime().getTime() + ".png";
        File file = new File(myDir, fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            MethodUtils.refreshGallery(context, file);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    dialog.dismiss();
                    Toast.makeText(context, (context.getString(R.string.saved)), Toast.LENGTH_SHORT).show();
                }
            }, 1000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void refreshGallery(Context context, File file) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        mediaScanIntent.setData(Uri.fromFile(file));
        context.sendBroadcast(mediaScanIntent);
    }
}
