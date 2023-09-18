package app.iqonicthemes.qibus.ui.activity;


import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.DroppingModel;
import app.iqonicthemes.qibus.ui.adapter.DroppingAdapter;
import java.util.ArrayList;
import java.util.List;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DroppingPointActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    public static String mPickup, mDropping;
    private TextView mTvPickup, mTvDropping;
    private RecyclerView mRvPickup, mRvDropping;
    private List<DroppingModel> mPickUpList, mDroppingList;
    private DroppingAdapter mPickupAdapter, mDroppingAdapter;
    private ImageView mIvBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dropping_point);
        initLayouts();
        initializeListeners();

    }

    /* initialize listener */
    private void initializeListeners() {
        mTvPickup.setOnClickListener(this);
        mTvDropping.setOnClickListener(this);
        mIvBack.setOnClickListener(this);

        mRvPickup.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mRvDropping.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mPickUpList = new ArrayList<>();
        mDroppingList = new ArrayList<>();

        mPickUpList.add(new DroppingModel(getString(R.string.lbl_pickup1), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));
        mPickUpList.add(new DroppingModel(getString(R.string.lbl_pickup2), getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));

        mDroppingList.add(new DroppingModel(getString(R.string.lbl_dropping1),  getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));
        mDroppingList.add(new DroppingModel(getString(R.string.lbl_droppin2),  getString(R.string.lbl_location1), getString(R.string.lbl_duration1)));

        mPickupAdapter = new DroppingAdapter(this, mPickUpList);
        mDroppingAdapter = new DroppingAdapter(this, mDroppingList);

        mRvPickup.setAdapter(mPickupAdapter);
        mRvDropping.setAdapter(mDroppingAdapter);


        mPickupAdapter.setOnClickListener(new DroppingAdapter.onClickListener() {
            @Override
            public void onClick(int i1, final String name) {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch_select));
                                                  mTvDropping.setTextColor(getResources().getColor(R.color.white));
                                                  mTvPickup.setTextColor(getResources().getColor(R.color.textheader));
                                                  mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch));
                                                  hideView(mRvPickup);
                                                  showView(mRvDropping);
                                                  mPickup = name;
                                              }
                                          },
                        500);
            }

        });
        mDroppingAdapter.setOnClickListener(new DroppingAdapter.onClickListener() {
            @Override
            public void onClick(int i, final String name) {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {

                                                  mDropping = name;
                                                  startActivity(PassengerDetailActivity.class);
                                              }
                                          },
                        500);
            }
        });
        RunLayoutAnimation(mRvPickup);
        RunLayoutAnimation(mRvDropping);

    }

    /* init layout */
    private void initLayouts() {
        mTvPickup = findViewById(R.id.tvPickup);
        mTvDropping = findViewById(R.id.tvDropping);
        mRvPickup = findViewById(R.id.rvPickup);
        mRvDropping = findViewById(R.id.rvDropping);
        mIvBack = findViewById(R.id.ivBack);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {

        if (v == mTvPickup) {
            mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch_select));
            mTvPickup.setTextColor(getResources().getColor(R.color.white));

            mTvDropping.setTextColor(getResources().getColor(R.color.textheader));
            mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch));

            hideView(mRvDropping);
            showView(mRvPickup);
            RunLayoutAnimation(mRvPickup);


        } else if (v == mTvDropping) {
            mTvDropping.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch_select));
            mTvDropping.setTextColor(getResources().getColor(R.color.white));
            mTvPickup.setTextColor(getResources().getColor(R.color.textheader));
            mTvPickup.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch));

            hideView(mRvPickup);
            showView(mRvDropping);
            RunLayoutAnimation(mRvDropping);

        } else if (v == mIvBack) onBackPressed();
    }
}
