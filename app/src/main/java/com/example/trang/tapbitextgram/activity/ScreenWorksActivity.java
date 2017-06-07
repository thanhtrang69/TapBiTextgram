package com.example.trang.tapbitextgram.activity;

import android.app.FragmentTransaction;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.ThumbnailUtils;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.manager.PicturesManager;
import com.example.trang.tapbitextgram.untils.MethodUtils;
import com.example.trang.tapbitextgram.view.fragment.FinishFragment;
import com.example.trang.tapbitextgram.view.viewpager.ShowAllViewPager;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class ScreenWorksActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Bitmap> bitmapArrayList;
    private ArrayList<Bitmap> bitmapArrayListBackground;
    private ArrayList<Bitmap> bitmapArrayListFilters;
    private ArrayList<Bitmap> bitmapArrayListSticker;
    private ArrayList<Bitmap> bitmapArrayListFrames;
    private ArrayList<Bitmap> bitmapArrayListFont;
    private PicturesManager picturesManager;
    private FinishFragment finishFragment;
    private ImageView tvBackMainActivity;
    private Button btNext;
    private ImageView ivShow;
    private Bitmap bm;
    private RelativeLayout view;
    private MethodUtils methodUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_works);
        readDataAssets();

        initView();
    }

    private void initView() {
        tabLayout = (TabLayout) findViewById(R.id.tl_show_function);
        viewPager = (ViewPager) findViewById(R.id.vp_show_all);
        ivShow = (ImageView) findViewById(R.id.iv_show);
        tvBackMainActivity = (ImageView) findViewById(R.id.iv_back_activity);
        btNext = (Button) findViewById(R.id.bt_next);
        FragmentManager fragmentManager = getSupportFragmentManager();
        ShowAllViewPager showAllViewPager = new ShowAllViewPager(fragmentManager);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(6);
        viewPager.setAdapter(showAllViewPager);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(showAllViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_book_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_image_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_backgrounds);
        tabLayout.getTabAt(3).setIcon(R.drawable.laugh);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_filter_vintage_black_24dp);
        tabLayout.getTabAt(5).setIcon(R.drawable.ic_filter_frames_black_24dp);


        tvBackMainActivity.setOnClickListener(this);
        btNext.setOnClickListener(this);
        autoAddImage();


    }

    private void autoAddImage() {
        Random random = new Random();
        String[] backgrounds;
        try {
            backgrounds = getAssets().list(Key.BACKGROUNDS_FACE);
            if (backgrounds[random.nextInt(bitmapArrayListBackground.size() - 1)].length() < 7) {
                InputStream inputStream = getAssets().open(Key.BACKGROUNDS_FACE + "/" + backgrounds[random.nextInt(bitmapArrayListBackground.size() - 1)]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivShow.setImageBitmap(bitmap);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readDataAssets() {
        bitmapArrayList = new ArrayList<>();
        try {
            String[] backgrounds = getAssets().list(Key.STICKERS);
            for (int i = 0; i < backgrounds.length; i++) {
                InputStream inputStream = getAssets().open(Key.STICKERS + "/" + backgrounds[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                bitmapArrayList.add(bitmap1);

            }
            picturesManager = PicturesManager.getInstance();
            picturesManager.setBitmapArrayList(bitmapArrayList);

        } catch (IOException e) {

            e.printStackTrace();
        }
        try {
            bitmapArrayListBackground = new ArrayList<>();
            String[] backgrounds = getAssets().list(Key.BACKGROUNDS);
            for (int i = 0; i < backgrounds.length; i++) {
                if (backgrounds[i].length() > 6) {
                    InputStream inputStream = getAssets().open(Key.BACKGROUNDS + "/" + backgrounds[i]);
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                    bitmapArrayListBackground.add(bitmap1);
                }

            }
            picturesManager.setBitmapArrayListBackground(bitmapArrayListBackground);

        } catch (IOException e) {

            e.printStackTrace();
        }
        bitmapArrayListFilters = new ArrayList<>();
        try {
            String[] filters = getAssets().list(Key.FILTERS);
            for (int i = 0; i < filters.length; i++) {
                InputStream inputStream = getAssets().open(Key.FILTERS + "/" + filters[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                bitmapArrayListFilters.add(bitmap1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        picturesManager.setBitmapArrayFilters(bitmapArrayListFilters);

        try {
            bitmapArrayListSticker = new ArrayList<>();
            String[] sticker = getAssets().list(Key.STICKERS);
            for (int i = 0; i < sticker.length; i++) {
                InputStream inputStream = getAssets().open(Key.STICKERS + "/" + sticker[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                bitmapArrayListSticker.add(bitmap1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        picturesManager.setBitmapArrayListSticker(bitmapArrayListSticker);

        try {
            bitmapArrayListFrames = new ArrayList<>();
            String[] sticker = getAssets().list(Key.FRAMES);
            for (int i = 0; i < sticker.length; i++) {
                InputStream inputStream = getAssets().open(Key.FRAMES + "/" + sticker[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                bitmapArrayListFrames.add(bitmap1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        picturesManager.setBitmapArrayFrames(bitmapArrayListFrames);
        try {
            bitmapArrayListFont = new ArrayList<>();
            String[] font = getAssets().list(Key.FONTS);
            for (int i = 0; i < font.length; i++) {
                InputStream inputStream = getAssets().open(Key.FONTS + "/" + font[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 200, 200);
                bitmapArrayListFont.add(bitmap1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        picturesManager.setBitmapArrayFont(bitmapArrayListFont);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_next:
                methodUtils = new MethodUtils();
                view = (RelativeLayout) findViewById(R.id.rl_iv_content);
                bm = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
                        Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bm);
                view.draw(canvas);
                methodUtils.SaveImage(this, bm);
                showSaveFinish(bm);
                break;
            case R.id.iv_back_activity:
                onBackPressed();
                break;
        }
    }

    private void showSaveFinish(Bitmap bitmap) {

        finishFragment = new FinishFragment(bitmap);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, finishFragment)
                .addToBackStack(FinishFragment.class.getName())
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    public void setImageCanvas(Bitmap bitmap) {
        ivShow.setImageBitmap(bitmap);
    }


}
