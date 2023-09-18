package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    /*variable declaration*/
    private Context mContext;
    private List<BookingModel> mNotificationList;
    private onClickListener mListener;

    /*constructor*/
    public NotificationAdapter(Context aCtx, List<BookingModel> aNotificationList) {
        /* initialize parameter*/
        this.mContext = aCtx;
        this.mNotificationList = aNotificationList;
    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotificationViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_notification, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder1, int position) {
        final BookingModel mNotificationModel = mNotificationList.get(position);

        holder1.mTvDestination.setText(mNotificationModel.getDestination());
        holder1.mTvStatus.setText(mNotificationModel.getStatus());
        holder1.mTvDate.setText(mNotificationModel.getDuration());
        holder1.mTvSeatNo.setText(mNotificationModel.getSeatNo());
        holder1.mTvPNR.setText(mNotificationModel.getPNRNo());
        holder1.mTvMonth.setText(mNotificationModel.getMonth());
        holder1.tvTicketNo.setText(mNotificationModel.getTicketNo());
        holder1.mTvTotalFare.setText(mNotificationModel.getTotalFare());
        holder1.mTvStartTime.setText(mNotificationModel.getStartTime());
        holder1.mTvEndTime.setText(mNotificationModel.getEndTime());
        holder1.mTvTotalTime.setText(mNotificationModel.getTotalTime());
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mListener != null) {
                    notifyDataSetChanged();
                    mListener.onClick(mNotificationModel);

                }
            }
        });
    }

    /*  remove item */
    public void removeItem(int position) {
        mNotificationList.remove(position);
        notifyItemRemoved(position);
    }

    /*  restore item */
    public void restoreItem(BookingModel item, int position) {
        mNotificationList.add(position, item);
        notifyItemInserted(position);
    }

    /*  get data */
    public List<BookingModel> getData() {
        return mNotificationList;
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(BookingModel notificationModel);
    }

    /*view holder*/
    class NotificationViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDestination, mTvStatus, mTvDate, mTvMonth, mTvSeatNo, mTvPNR, tvTicketNo, mTvTotalFare, mTvStartTime, mTvEndTime, mTvTotalTime;


        NotificationViewHolder(View itemView) {
            super(itemView);
            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvStatus = itemView.findViewById(R.id.tvStatus);
            mTvDate = itemView.findViewById(R.id.tvDate);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mTvMonth = itemView.findViewById(R.id.tvMonth);
            mTvPNR = itemView.findViewById(R.id.tvPNR);
            tvTicketNo = itemView.findViewById(R.id.tvTicketNo);
            mTvTotalFare = itemView.findViewById(R.id.totalfare);
            mTvStartTime = itemView.findViewById(R.id.starttime);
            mTvEndTime = itemView.findViewById(R.id.endtime);
            mTvTotalTime = itemView.findViewById(R.id.totaltime);
        }

    }
}
