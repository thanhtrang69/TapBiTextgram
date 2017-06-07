package com.example.trang.tapbitextgram.view.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.trang.tapbitextgram.adapter.FontAdapter;
import com.example.trang.tapbitextgram.adapter.PicturesAdapter;
import com.example.trang.tapbitextgram.manager.PicturesManager;
import com.example.trang.tapbitextgram.untils.FontProvider;
import com.example.trang.tapbitextgram.R;
import com.example.trang.tapbitextgram.activity.DisplayActivity;
import com.example.trang.tapbitextgram.activity.ScreenWorksActivity;
import com.example.trang.tapbitextgram.adapter.CanvasAdapter;
import com.example.trang.tapbitextgram.enity.Key;
import com.example.trang.tapbitextgram.untils.FileUtils;
import com.example.trang.tapbitextgram.view.BubbleInputDialog;
import com.example.trang.tapbitextgram.view.BubbleTextView;
import com.example.trang.tapbitextgram.view.StickerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Trang on 6/5/2017.
 */

public class CanvasFragment extends Fragment implements FontAdapter.onClickItemImage, View.OnClickListener, CanvasAdapter.onClickItemImage {
    private View view;
    private CanvasAdapter canvasAdapter;
    private RecyclerView recyclerView;
    public static final int RESULT_LOAD_IMAGE = 1;
    private ArrayList<View> mView;
    private RelativeLayout mContentRootView;
    private BubbleTextView mCurrentEditTextView;
    private StickerView mCurrentView;
    private BubbleInputDialog mBubbleInputDialog;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private ImageButton imageButton;
    private FontProvider fontProvider;
    private PicturesManager picturesManager;
    private ArrayList<Bitmap> bitmapArrayList;
    private FontAdapter fontAdapter;
    private RecyclerView recyclerViewSetFont;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_canvas, container, false);
        initView();
        return view;
    }


    public CanvasFragment() {
    }

    private void initView() {
        mContentRootView = (RelativeLayout) getActivity().findViewById(R.id.rl_iv_content);
        mView = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_canvas);

        viewPager = (ViewPager) getActivity().findViewById(R.id.vp_show_all);
        linearLayout = (LinearLayout) getActivity().findViewById(R.id.rl_set_font);
        imageButton = (ImageButton) getActivity().findViewById(R.id.ib_back_status_left);
        canvasAdapter = new CanvasAdapter(getActivity());
        GridLayoutManager gis = new GridLayoutManager(getActivity(), 4);
        gis.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(gis);
        recyclerView.setAdapter(canvasAdapter);
        canvasAdapter.setClickItemImage(this);

        viewPager.setVisibility(View.VISIBLE);
        linearLayout.setVisibility(View.GONE);

        imageButton.setOnClickListener(this);
        mBubbleInputDialog = new BubbleInputDialog(getActivity());
        mBubbleInputDialog.setCompleteCallBack(new BubbleInputDialog.CompleteCallBack() {
            @Override
            public void onComplete(View bubbleTextView, String str) {
                ((BubbleTextView) bubbleTextView).setText(str);
            }
        });

    }

    @Override
    public void onClickItem(int position) {
        switch (position) {
            case 0:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
            case 1:
                addBubble();

                break;
            case 2:
                Toast.makeText(getActivity(), "CanVas", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Random random = new Random();
                String[] backgrounds;
                try {
                    backgrounds = getActivity().getAssets().list(Key.BACKGROUNDS_FACE);
                    if (backgrounds[random.nextInt(56)].length() < 7) {
                        InputStream inputStream = getActivity().getAssets().open(Key.BACKGROUNDS_FACE + "/" + backgrounds[random.nextInt(56)]);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        ((ScreenWorksActivity) getActivity()).setImageCanvas(bitmap);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }


    private void addBubble() {
        final BubbleTextView bubbleTextView = new BubbleTextView(getActivity(),
                Color.WHITE, 0);
        bubbleTextView.setImageResource(R.mipmap.bubble_7_rb);
        bubbleTextView.setOperationListener(new BubbleTextView.OperationListener() {
            @Override
            public void onDeleteClick() {
                mView.remove(bubbleTextView);
                mContentRootView.removeView(bubbleTextView);
            }

            @Override
            public void onEdit(BubbleTextView bubbleTextView) {
                if (mCurrentView != null) {
                    mCurrentView.setInEdit(false);
                }
                mCurrentEditTextView.setInEdit(false);
                mCurrentEditTextView = bubbleTextView;
                mCurrentEditTextView.setInEdit(true);
            }

            @Override
            public void onClick(BubbleTextView bubbleTextView) {
                mBubbleInputDialog.setBubbleTextView(bubbleTextView);
                mBubbleInputDialog.show();
            }

            @Override
            public void onTop(BubbleTextView bubbleTextView) {
                int position = mView.indexOf(bubbleTextView);
                if (position == mView.size() - 1) {
                    return;
                }
                BubbleTextView textView = (BubbleTextView) mView.remove(position);
                mView.add(mView.size(), textView);
            }

            @Override
            public void onChangeFont() {
                change();

            }
        });
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mContentRootView.addView(bubbleTextView, lp);
        mView.add(bubbleTextView);
        setCurrentEdit(bubbleTextView);
    }

    private void change() {
        recyclerViewSetFont = (RecyclerView) getActivity().findViewById(R.id.rl_set_font_list);
        picturesManager = PicturesManager.getInstance();
        bitmapArrayList = picturesManager.getBitmapArrayFont();
        fontAdapter = new FontAdapter(getContext(), bitmapArrayList);

        GridLayoutManager gis = new GridLayoutManager(getContext(), 1);
        gis.setOrientation(GridLayoutManager.HORIZONTAL);
        recyclerViewSetFont.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSetFont.setLayoutManager(gis);
        recyclerViewSetFont.setAdapter(fontAdapter);
        fontProvider = new FontProvider(getResources());
        viewPager.setVisibility(View.GONE);
        linearLayout.setVisibility(View.VISIBLE);

        fontAdapter.setClickItemImage(new PicturesAdapter.onClickItemImage() {
            @Override
            public void onClickItem(int position) {

                List<String> list = fontProvider.getFontNames();
                mCurrentEditTextView.setTypeface(fontProvider.getTypeface(list.get(position)));

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == getActivity().RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
            Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap, 350, 350);
            ((ScreenWorksActivity) getActivity()).setImageCanvas(bitmap1);

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        getActivity().getMenuInflater().inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_complete) {
            mCurrentView.setInEdit(false);
            generateBitmap();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setCurrentEdit(BubbleTextView bubbleTextView) {
        if (mCurrentView != null) {
            mCurrentView.setInEdit(false);
        }
        if (mCurrentEditTextView != null) {
            mCurrentEditTextView.setInEdit(false);
        }
        mCurrentEditTextView = bubbleTextView;
        mCurrentEditTextView.setInEdit(true);
    }

    private void generateBitmap() {

        Bitmap bitmap = Bitmap.createBitmap(mContentRootView.getWidth(),
                mContentRootView.getHeight()
                , Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        mContentRootView.draw(canvas);

        String iamgePath = FileUtils.saveBitmapToLocal(bitmap, getActivity());
        Intent intent = new Intent(getActivity(), DisplayActivity.class);
        intent.putExtra("image", iamgePath);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_status_left:
                viewPager.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.GONE);
                break;
        }
    }
}
