package app.iqonicthemes.qibus.ui.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BusModel;
import app.iqonicthemes.qibus.ui.adapter.ItemBusAdapter;
import app.iqonicthemes.qibus.utils.Constants;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BusListActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/
    private RecyclerView mRvBuses;
    private List<BusModel> mBusList;
    private ImageView mIvBack, mIvFilter, mIvPrevious, mIvNext;
    private TextView mTvDate,mTvTitle;
    private Calendar mDepartDateCalendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_list);

        initLayouts();
        initializeListeners();

    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mIvFilter.setOnClickListener(this);
        mIvPrevious.setOnClickListener(this);
        mIvNext.setOnClickListener(this);

        String mTitle=getIntent().getStringExtra(Constants.intentdata.TRIP_KEY);
        String mSearchTitle=getIntent().getStringExtra(Constants.intentdata.SEARCH_BUS);
        String mPackageTitle=getIntent().getStringExtra(Constants.intentdata.PACKAGE_NAME);

        if(mTitle!=null) {
            mTvTitle.setText(mTitle);
        }
        if(mSearchTitle!=null)
        {
            mTvTitle.setText(mSearchTitle);
        }
        if(mPackageTitle!=null)
        {
            mTvTitle.setText(mPackageTitle);
        }

        mBusList = new ArrayList<>();
        mRvBuses.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mBusList.add(new BusModel(getString(R.string.lbl_travelname), getString(R.string.lbl_starttime1), getString(R.string.text_am),getString(R.string.lbl_endtime1),getString(R.string.text_pm) ,getString(R.string.lbl_totalDuration), getString(R.string.lbl_hold), getString(R.string.lbl_type1), 3, getString(R.string.lbl_price1)));
        mBusList.add(new BusModel(getString(R.string.lbl_travelname), getString(R.string.lbl_starttime1),getString(R.string.text_am),getString(R.string.lbl_endtime1),getString(R.string.text_pm), getString(R.string.lbl_totalDuration), getString(R.string.lbl_hold), getString(R.string.lbl_type1), 3, getString(R.string.lbl_price2)));
        mBusList.add(new BusModel(getString(R.string.lbl_travelname), getString(R.string.lbl_starttime1), getString(R.string.text_am),getString(R.string.lbl_endtime1),getString(R.string.text_pm),getString(R.string.lbl_totalDuration), getString(R.string.lbl_hold), getString(R.string.lbl_type2), 3, getString(R.string.lbl_price1)));


        mRvBuses.setAdapter(new ItemBusAdapter(this, mBusList));
        RunLayoutAnimation(mRvBuses);

        mDepartDateCalendar = Calendar.getInstance();
        mTvDate.setText(Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar.getTime()));

    }

    /* init layout */
    private void initLayouts() {
        mRvBuses = findViewById(R.id.rvBus);
        mIvBack = findViewById(R.id.ivBack);
        mIvFilter = findViewById(R.id.ivFilter);
        mIvPrevious = findViewById(R.id.ivPrevious);
        mIvNext = findViewById(R.id.ivNext);
        mTvDate = findViewById(R.id.tvDate);
        mTvTitle = findViewById(R.id.tvTitle);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            onBackPressed();
        } else if (v == mIvPrevious) {
            mDepartDateCalendar.add(Calendar.DATE, -1);
            mTvDate.setText(Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar.getTime()));
        } else if (v == mIvNext) {
            mDepartDateCalendar.add(Calendar.DATE, 1);
            mTvDate.setText(Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar.getTime()));

        } else if (v == mIvFilter) {
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_filter);
            dialog.setCancelable(true);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));


            final TextView tvMax = dialog.findViewById(R.id.endprice);
            final Button mBtnApply = dialog.findViewById(R.id.btnApply);
            ImageView mIvClose = dialog.findViewById(R.id.ivClose);

            mBtnApply.setStateListAnimator(null);
            mBtnApply.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            AppCompatSeekBar rangeSeekbar1 = dialog.findViewById(R.id.rangeSeekbar1);
            rangeSeekbar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressChangedValue = 100;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressChangedValue = progress;
                    tvMax.setText(String.valueOf(progressChangedValue));
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    tvMax.setText(String.valueOf(progressChangedValue));
                }
            });

            mIvClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }

    }
}
