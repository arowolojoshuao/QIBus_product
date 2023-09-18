package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    /*variable declaration*/
    private Context mCtx;
    private List<BookingModel> mBookList;

    /*constructor*/
    public BookingAdapter(Context aCtx, List<BookingModel> aBookList) {
        /* initialize parameter*/
        this.mCtx = aCtx;
        this.mBookList = aBookList;

    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookingViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_booking, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull final BookingViewHolder holder1, int position) {
        final BookingModel mBookingModel = mBookList.get(position);

        holder1.mTvDestination.setText(mBookingModel.getDestination());
        holder1.mTvDuration.setText(mBookingModel.getDuration());
        holder1.mTvStartTime.setText(mBookingModel.getStartTime());
        holder1.mTvSeatNo.setText(mBookingModel.getSeatNo());
        holder1.mTvTotalTime.setText(String.format("%s %s", mBookingModel.getTotalTime(), mCtx.getString(R.string.text_hour)));
        holder1.mTvEndTime.setText(mBookingModel.getEndTime());
        holder1.mTvTicketNo.setText(mBookingModel.getTicketNo());
        holder1.mTvPNRNo.setText(mBookingModel.getPNRNo());
        holder1.mTvTotalFare.setText(String.format("%s%s", mCtx.getString(R.string.rs), mBookingModel.getTotalFare()));

        holder1.mRlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)mCtx).showView(holder1.mRlShowMore);
                ((BaseActivity)mCtx).hideView(holder1.mIvShowMore);
                ((BaseActivity)mCtx).hideView(holder1.mTvConfirm);


            }
        });
        holder1.mRlShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)mCtx).showView(holder1.mIvShowMore);
                ((BaseActivity)mCtx).hideView(holder1.mRlShowMore);
                ((BaseActivity)mCtx).fadeOutIn(holder1.mIvShowMore);
                ((BaseActivity)mCtx).showView(holder1.mTvConfirm);
                ((BaseActivity)mCtx).fadeOutIn(holder1.mTvConfirm);
            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    /*view holder*/
    class BookingViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDestination, mTvDuration, mTvStartTime, mTvTotalTime, mTvEndTime, mTvTicketNo, mTvPNRNo, mTvTotalFare, mTvSeatNo, mTvConfirm;
        private RelativeLayout mRlShowMore, mRlContent;
        private ImageView mIvShowMore;

        BookingViewHolder(View itemView) {
            super(itemView);
            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvDuration = itemView.findViewById(R.id.tvDuration);
            mTvStartTime = itemView.findViewById(R.id.tvStartTime);
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime);
            mTvEndTime = itemView.findViewById(R.id.tvEndTime);
            mTvTicketNo = itemView.findViewById(R.id.tvTicketNo);
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo);
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare);
            mIvShowMore = itemView.findViewById(R.id.ivShowMore);
            mRlShowMore = itemView.findViewById(R.id.rlShowMore);
            mRlContent = itemView.findViewById(R.id.rlContent);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mTvConfirm = itemView.findViewById(R.id.tvConfirmed);
        }
    }
}
