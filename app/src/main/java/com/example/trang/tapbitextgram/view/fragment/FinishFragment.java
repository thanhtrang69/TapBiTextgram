package com.example.trang.tapbitextgram.view.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.enity.ImageLoadSdCard;
import com.example.trang.tapbitextgram.manager.ImageManager;

import java.util.ArrayList;

/**
 * Created by Trang on 6/5/2017.
 */

public class FinishFragment extends Fragment implements View.OnClickListener {
    private View view;
    private Button btSavePicture;
    private Button btSaveDesign;
    private ImageView ivResult;
    private ImageManager imageManager;
    private ArrayList<ImageLoadSdCard> arrayList;
    private Bitmap bitmap;

    public FinishFragment(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_saved_finish, container, false);
        initView();
        return view;
    }

    private void initView() {
        btSaveDesign = (Button) view.findViewById(R.id.bt_save_design);
        btSavePicture = (Button) view.findViewById(R.id.bt_save_picture);
        ivResult = (ImageView) view.findViewById(R.id.iv_show_finish);

        btSaveDesign.setOnClickListener(this);
        btSavePicture.setOnClickListener(this);
        imageManager = ImageManager.getInstance();

        arrayList = imageManager.loadImageExternalStorage();

        ivResult.setImageBitmap(bitmap);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_save_design:

                Toast.makeText(getActivity(), "saved design ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.bt_save_picture:

                Toast.makeText(getActivity(), "auto saved ", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
