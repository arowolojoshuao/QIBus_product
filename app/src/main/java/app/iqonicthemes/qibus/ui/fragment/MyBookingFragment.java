package app.iqonicthemes.qibus.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;
import app.iqonicthemes.qibus.ui.activity.DashboardActivity;
import app.iqonicthemes.qibus.ui.adapter.BookingAdapter;
import app.iqonicthemes.qibus.ui.adapter.CancelAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyBookingFragment extends Fragment implements View.OnClickListener {
    /*variable declaration*/
    public static final String mTitle = "My Booking";
    private TextView mTvCompleted, mTvCancel;
    private RecyclerView mRvCompleted, mRvCancelled;
    private List<BookingModel> mBookList, mCancelList;
    /* create view */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mybooking, null);

        initView(view);
        initializeListeners();
        return view;
    }

    /* init view */
    private void initView(View view) {
        mRvCompleted = view.findViewById(R.id.rvBooking);
        mRvCancelled = view.findViewById(R.id.rvCancelled);
        mTvCompleted = view.findViewById(R.id.tvCompleted);
        mTvCancel = view.findViewById(R.id.tvCancelled);
    }

    /* initialize listener */
    private void initializeListeners() {

        mTvCompleted.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);

        mBookList = new ArrayList<>();
        mCancelList = new ArrayList<>();
        mRvCompleted.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
        mRvCancelled.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        mBookList.add(new BookingModel(getString(R.string.lbl_DelhiToMubai), getString(R.string.lbl_booking_date1), getString(R.string.lbl_booking_starttime1), getString(R.string.lbl_booking_totaltime1), getString(R.string.lbl_booking_endtime1), getString(R.string.lbl_booking_SeatNo1), getString(R.string.lbl_booking_passengername1), getString(R.string.lbl_booking_ticketno1), getString(R.string.lbl_booking_pnr1), getString(R.string.lbl_booking_totalfare1), getString(R.string.text_confirmed)));
        mBookList.add(new BookingModel(getString(R.string.lbl_MumbaiToPune), getString(R.string.lbl_booking_date2), getString(R.string.lbl_booking_starttime2),  getString(R.string.lbl_booking_totaltime1), getString(R.string.lbl_booking_endtime2), getString(R.string.lbl_booking_SeatNo1), getString(R.string.lbl_booking_passengername1), getString(R.string.lbl_booking_ticketno2), getString(R.string.lbl_booking_pnr21), getString(R.string.lbl_booking_totalfare2), getString(R.string.text_confirmed)));

        mRvCompleted.setAdapter(new BookingAdapter(getActivity(), mBookList));

        mCancelList.add(new BookingModel(getString(R.string.lbl_MumbaiToPune), getString(R.string.lbl_booking_date1), getString(R.string.lbl_booking_starttime2),  getString(R.string.lbl_booking_totaltime1), getString(R.string.lbl_booking_endtime2), getString(R.string.lbl_booking_SeatNo1), getString(R.string.lbl_booking_passengername1), getString(R.string.lbl_booking_ticketno2), getString(R.string.lbl_booking_pnr21), getString(R.string.lbl_booking_totalfare2), getString(R.string.text_confirmed)));
        mRvCancelled.setAdapter(new CancelAdapter(getActivity(), mCancelList));

        ((DashboardActivity)(Objects.requireNonNull(getActivity()))).RunLayoutAnimation(mRvCompleted);
        ((DashboardActivity)(getActivity())).RunLayoutAnimation(mRvCancelled);

    }
    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mTvCompleted) {
            mTvCompleted.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch_select));
            mTvCompleted.setTextColor(getResources().getColor(R.color.white));

            mTvCancel.setTextColor(getResources().getColor(R.color.textheader));
            mTvCancel.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch));

            ((DashboardActivity)(Objects.requireNonNull(getActivity()))).showView(mRvCompleted);
            ((DashboardActivity)(getActivity())).RunLayoutAnimation(mRvCompleted);
            ((DashboardActivity)(getActivity())).hideView(mRvCancelled);


        } else if (v == mTvCancel) {
            mTvCancel.setBackground(getResources().getDrawable(R.drawable.bg_rightswitch_select));
            mTvCancel.setTextColor(getResources().getColor(R.color.white));
            mTvCompleted.setTextColor(getResources().getColor(R.color.textheader));
            mTvCompleted.setBackground(getResources().getDrawable(R.drawable.bg_leftswitch));

            ((DashboardActivity)(Objects.requireNonNull(getActivity()))).hideView(mRvCompleted);
            ((DashboardActivity)(getActivity())).showView(mRvCancelled);
            ((DashboardActivity)(getActivity())).RunLayoutAnimation(mRvCancelled);

        }
    }
}