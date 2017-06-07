package com.example.trang.tapbitextgram.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.view.StickerView;
import com.example.trang.tapbitextgram.untils.FileUtils;
import com.example.trang.tapbitextgram.view.viewpager.MainSreenViewPager;
import com.getbase.floatingactionbutton.FloatingActionsMenu;


public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StickerView mCurrentView;
    private RelativeLayout mContentRootView;

    private FloatingActionsMenu mMultipleActions;
    private View mAddSticker;
    private View mAddBubble;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }



    private void initView() {
        mContentRootView = (RelativeLayout) findViewById(R.id.rl_content_root);

        tabLayout = (TabLayout) findViewById(R.id.tl_activity);
        viewPager = (ViewPager) findViewById(R.id.vp_activity);
        FragmentManager fragmentManager = getSupportFragmentManager();
        MainSreenViewPager mainSreenViewPager = new MainSreenViewPager(fragmentManager);
        viewPager.setAdapter(mainSreenViewPager);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(mainSreenViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.pictures);
        tabLayout.getTabAt(1).setIcon(R.drawable.art);
        tabLayout.setPadding(0, 10, 0, 0);

        mMultipleActions = (FloatingActionsMenu) findViewById(R.id.multiple_actions);
        mAddSticker = findViewById(R.id.action_add_sticker);
        mAddBubble = findViewById(R.id.action_add_bubble);

        mAddSticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScreenWorksActivity.class);
                startActivityForResult(intent, 123);
                mMultipleActions.collapse();

            }
        });
        mAddBubble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Canvas", Toast.LENGTH_SHORT).show();
                mMultipleActions.collapse();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_complete) {
            mCurrentView.setInEdit(false);
            generateBitmap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void generateBitmap() {

        Bitmap bitmap = Bitmap.createBitmap(mContentRootView.getWidth(),
                mContentRootView.getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mContentRootView.draw(canvas);

        String iamgePath = FileUtils.saveBitmapToLocal(bitmap, this);
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra("image", iamgePath);
        startActivity(intent);
    }
}
