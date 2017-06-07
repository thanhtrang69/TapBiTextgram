package com.example.trang.tapbitextgram.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.activity.ScreenWorksActivity;
import com.example.trang.tapbitextgram.untils.ColorPickerDialog;
import com.github.danielnilsson9.colorpickerview.view.ColorPanelView;
import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;

import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class BackgroundFragment extends Fragment implements View.OnClickListener, ColorPickerDialog.OnColorChangedListener {
    private View view;
    private ImageView ivBackground;
    private ImageView ivAddPhoto;
    private ImageView ivPickColor;
//    private static final int RESULT_LOAED_IMGAGE_BACKGROUND = 2;
    private Paint mPaint;
    private ColorPanelView mNewColor;
    private ImageView ivShow;
    private ColorPickerView.OnColorChangedListener mListener;
    private boolean checkedAlpha = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_background, container, false);
        initView();
        return view;
    }


    public BackgroundFragment() {

    }

    private void initView() {
        ivBackground = (ImageView) view.findViewById(R.id.iv_background);
        ivAddPhoto = (ImageView) view.findViewById(R.id.iv_add_photo);
        ivPickColor = (ImageView) view.findViewById(R.id.iv_pen);
        ivShow = (ImageView) getActivity().findViewById(R.id.iv_show);
        mNewColor = new ColorPanelView(getActivity());
        ivBackground.setOnClickListener(this);
        ivAddPhoto.setOnClickListener(this);
        ivPickColor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_background:
                if (!checkedAlpha) {
                    ivShow.setBackgroundColor(255);
                    ivShow.setAlpha(125);
                    checkedAlpha = true;
                } else {
                    ivShow.setAlpha(255);
                    checkedAlpha = false;
                }
                break;
            case R.id.iv_add_photo:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, CanvasFragment.RESULT_LOAD_IMAGE);
                break;
            case R.id.iv_pen:

                mPaint = new Paint();
                // on button click
                new ColorPickerDialog(getContext(), this, mPaint.getColor()).show();
                Toast.makeText(getContext(), "pen", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CanvasFragment.RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri uri = data.getData();

            String[] image = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContext().getContentResolver().query(uri, image, null, null, null);
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(image[0]);
            String path = cursor.getString(index);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeFile(path, options);
            Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 350, 350);

            ((ScreenWorksActivity) getActivity()).setImageCanvas(bitmap1);


        }
    }

    @Override
    public void colorChanged(int color) {
        mNewColor.setColor(color);
        if (mListener != null) {
            mListener.onColorChanged(color);
        }
        ivShow.setImageBitmap(null);
        ivShow.setBackgroundColor(color);
    }

}
