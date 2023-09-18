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

public class CancelAdapter extends RecyclerView.Adapter<CancelAdapter.CancelViewHolder> {
    /*variable declaration*/
    private Context mCtx;
    private List<BookingModel> mCancelList;

    /*constructor*/
    public CancelAdapter(Context aCtx, List<BookingModel> aCancelList) {

        /* initialize parameter*/
        this.mCtx = aCtx;
        this.mCancelList = aCancelList;

    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public CancelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CancelViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_cancel, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull final CancelViewHolder holder1, int position) {
        final BookingModel mBookingModel = mCancelList.get(position);

        holder1.mTvDestination.setText(mBookingModel.getDestination());
        holder1.mTvDuration.setText(mBookingModel.getDuration());
        holder1.mTvStartTime.setText(mBookingModel.getStartTime());
        holder1.mTvTotalTime.setText(String.format("%s %s", mBookingModel.getTotalTime(), mCtx.getString(R.string.text_hour)));
        holder1.mTvEndTime.setText(mBookingModel.getEndTime());
        holder1.mTvTicketNo.setText(mBookingModel.getTicketNo());
        holder1.mTvPNRNo.setText(mBookingModel.getPNRNo());
        holder1.mTvSeatNo.setText(mBookingModel.getSeatNo());
        holder1.mTvTotalFare.setText(String.format("%s%s", mCtx.getString(R.string.rs), mBookingModel.getTotalFare()));

        holder1.mRlContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((BaseActivity)mCtx).showView(holder1.mRlShowMore);
                ((BaseActivity)mCtx).hideView(holder1.mIVShowMore);
                ((BaseActivity)mCtx).hideView(holder1.mTvCancel);

            }
        });
        holder1.mRlShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)mCtx).showView(holder1.mIVShowMore);
                ((BaseActivity)mCtx).showView(holder1.mTvCancel);
                ((BaseActivity)mCtx).hideView(holder1.mRlShowMore);
                ((BaseActivity)mCtx).fadeOutIn(holder1.mIVShowMore);
                ((BaseActivity)mCtx).fadeOutIn(holder1.mTvCancel);

            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mCancelList.size();
    }

    /*view holder*/
    class CancelViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDestination, mTvDuration, mTvStartTime, mTvTotalTime, mTvEndTime, mTvTicketNo, mTvPNRNo, mTvTotalFare, mTvSeatNo, mTvCancel;
        private RelativeLayout mRlShowMore, mRlContent;
        private ImageView mIVShowMore;


        CancelViewHolder(View itemView) {
            super(itemView);
            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvDuration = itemView.findViewById(R.id.tvDuration);
            mTvStartTime = itemView.findViewById(R.id.tvStartTime);
            mTvTotalTime = itemView.findViewById(R.id.tvTotalTime);
            mTvEndTime = itemView.findViewById(R.id.tvEndTime);
            mTvTicketNo = itemView.findViewById(R.id.tvTicketNo);
            mTvPNRNo = itemView.findViewById(R.id.tvPNRNo);
            mTvTotalFare = itemView.findViewById(R.id.tvTotalFare);
            mIVShowMore = itemView.findViewById(R.id.ivShowMore);
            mRlShowMore = itemView.findViewById(R.id.rlShowMore);
            mRlContent = itemView.findViewById(R.id.rlContent);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mTvCancel = itemView.findViewById(R.id.tvCancelled);

        }

    }

}
