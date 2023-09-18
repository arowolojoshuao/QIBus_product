package app.iqonicthemes.qibus.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;
import app.iqonicthemes.qibus.ui.adapter.NotificationAdapter;
import app.iqonicthemes.qibus.utils.SwipeToDeleteCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationActivity extends BaseActivity implements View.OnClickListener, NotificationAdapter.onClickListener {

    /*variable declaration*/

    private TextView mTvDestination, mTvDuration, mTvStartTime, mTvTotalTime, mTvEndTime, mTvTicketNo, mTvPNRNo, mTvTotalFare, mTvSeatNo;
    private RecyclerView mRvNotification;
    private List<BookingModel> mNotificationList;
    private ImageView mIvBack, mIvClose;
    private NotificationAdapter mNotificationAdapter;
    private RelativeLayout mRlMain;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        initLayouts();
        initializeLayouts();
        enableSwipeToDeleteAndUndo();
    }

    /* init layout */
    private void initLayouts() {
        mRvNotification = findViewById(R.id.rvNotification);
        mIvBack = findViewById(R.id.ivBack);
        mRlMain = findViewById(R.id.rlMain);
    }

    /* initialize listener */
    private void initializeLayouts() {
        mIvBack.setOnClickListener(this);
        mNotificationList = new ArrayList<>();
        mRvNotification.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mNotificationList.add(new BookingModel(getString(R.string.lbl_DelhiToMubai), getString(R.string.lbl_booking_duration1),getString(R.string.lbl_may), getString(R.string.lbl_booking_starttime1), getString(R.string.lbl_booking_totaltime1), getString(R.string.lbl_booking_endtime1), getString(R.string.lbl_booking_SeatNo1), getString(R.string.lbl_booking_passengername1), getString(R.string.lbl_booking_ticketno1), getString(R.string.lbl_booking_pnr1), getString(R.string.lbl_booking_totalfare1), getString(R.string.text_confirmed)));
        mNotificationList.add(new BookingModel(getString(R.string.lbl_MumbaiToPune), getString(R.string.lbl_booking_duration2), getString(R.string.lbl_may),getString(R.string.lbl_booking_starttime2),  getString(R.string.lbl_booking_totaltime1), getString(R.string.lbl_booking_endtime2), getString(R.string.lbl_booking_SeatNo1), getString(R.string.lbl_booking_passengername1), getString(R.string.lbl_booking_ticketno2), getString(R.string.lbl_booking_pnr21), getString(R.string.lbl_booking_totalfare2), getString(R.string.text_confirmed)));

        mNotificationAdapter = new NotificationAdapter(this, mNotificationList);
        mRvNotification.setAdapter(mNotificationAdapter);
        mNotificationAdapter.setOnClickListener(this);
        RunLayoutAnimation(mRvNotification);
    }

    /* swipe to delete & undo */
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                BookingModel notificationModel;
                notificationModel = mNotificationAdapter.getData().get(position);

                mNotificationAdapter.removeItem(position);

                Snackbar snackbar = Snackbar.make(mRlMain, getString(R.string.text_remove), Snackbar.LENGTH_LONG);
                final BookingModel finalNotificationModel = notificationModel;
                snackbar.setAction(getString(R.string.text_undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mNotificationAdapter.restoreItem(finalNotificationModel, position);
                        mRvNotification.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(mRvNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack)
            onBackPressed();
    }

    /* onClick listener & open mDialog*/
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(BookingModel notificationModel) {

        final View dialogView = View.inflate(this, R.layout.layout_notification, null);

        mDialog = new Dialog(this);
        mDialog.setContentView(dialogView);
        mDialog.setCancelable(true);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(0));
        mDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        mTvDestination = mDialog.findViewById(R.id.tvDestination);
        mTvDuration = mDialog.findViewById(R.id.tvDuration);
        mTvStartTime = mDialog.findViewById(R.id.tvStartTime);
        mTvTotalTime = mDialog.findViewById(R.id.tvTotalTime);
        mTvEndTime = mDialog.findViewById(R.id.tvEndTime);
        mTvTicketNo = mDialog.findViewById(R.id.tvTicketNo);
        mTvPNRNo = mDialog.findViewById(R.id.tvPNRNo);
        mTvTotalFare = mDialog.findViewById(R.id.tvTotalFare);
        mIvClose = mDialog.findViewById(R.id.ivClose);

        mTvSeatNo = mDialog.findViewById(R.id.tvSeatNo);
        mTvDestination.setText(notificationModel.getDestination());
        mTvDuration.setText(String.format("%s %s", notificationModel.getDuration(), notificationModel.getMonth()));
        mTvStartTime.setText(notificationModel.getStartTime());
        mTvSeatNo.setText(notificationModel.getSeatNo());
        mTvTotalTime.setText(String.format("%s %s", notificationModel.getTotalTime(), this.getString(R.string.text_hour)));
        mTvEndTime.setText(notificationModel.getEndTime());
        mTvTicketNo.setText(notificationModel.getTicketNo());
        mTvPNRNo.setText(notificationModel.getPNRNo());
        mTvTotalFare.setText(String.format("%s%s", this.getString(R.string.rs), notificationModel.getTotalFare()));

        mIvClose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mDialog.hide();
                return false;
            }
        });

        mDialog.show();
    }

    /* onBackPressed*/
    @Override
    public void onBackPressed() {
        if(mDialog != null && mDialog.isShowing()){
            mDialog.dismiss();
            mDialog = null;
        }
        super.onBackPressed();
    }
}

