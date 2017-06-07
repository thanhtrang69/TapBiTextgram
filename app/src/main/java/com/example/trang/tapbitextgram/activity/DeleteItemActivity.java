package com.example.trang.tapbitextgram.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.enity.ImageLoadSdCard;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.ImageManager;
import com.example.trang.tapbitextgram.view.fragment.PictruresFragment;

import java.util.ArrayList;

public class DeleteItemActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton ibDelete;
    private ImageView ivback;
    private ImageView ivImage;
    private int position;
    private ImageManager imageManager;
    private ArrayList<ImageLoadSdCard> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_item);
        initView();
    }

    private void initView() {

        position = getIntent().getIntExtra(Key.POSITION, 0);
        imageManager = ImageManager.getInstance();
        arrayList = imageManager.loadImageExternalStorage();
        ibDelete = (ImageButton) findViewById(R.id.ib_delete);
        ivback = (ImageView) findViewById(R.id.iv_back_fragment_picture);
        ivImage = (ImageView) findViewById(R.id.iv_item_delete);
        ivImage.setImageURI(arrayList.get(position).getUrlImage());
        ivback.setOnClickListener(this);
        ibDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_delete:
                imageManager.deleteItemImage(arrayList.get(position).getName());
                Intent intent = new Intent(this, PictruresFragment.class);
                intent.putExtra(Key.RESULT,15);
                setResult(RESULT_OK,intent);
                onBackPressed();
                break;
            case R.id.iv_back_fragment_picture:
                onBackPressed();
                break;
        }
    }


}
