package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BusModel;
import app.iqonicthemes.qibus.ui.activity.BookingActivity;
import app.iqonicthemes.qibus.utils.Constants;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemBusAdapter extends RecyclerView.Adapter<ItemBusAdapter.BusitemViewHolder> {
    /*variable declaration*/
    private Context mContext;
    private List<BusModel> mBusList;

    /*constructor*/

    public ItemBusAdapter(Context aCtx, List<BusModel> aBusList) {
        /* initialize parameter*/
        this.mContext = aCtx;
        this.mBusList = aBusList;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public ItemBusAdapter.BusitemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemBusAdapter.BusitemViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_available_bus, null));
    }

    /*bind viewholder*/
    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ItemBusAdapter.BusitemViewHolder holder1, int position) {

        final BusModel mBusModel = mBusList.get(position);
        holder1.mTvTravellerName.setText(mBusModel.getTravelerName());
        holder1.mTvStartTime.setText(mBusModel.getStarttime());
        holder1.mTvTypeCoach.setText(mBusModel.getType_coach());
        holder1.mTvEndTime.setText(mBusModel.getEnddtime());
        holder1.mTvTotalDuration.setText(mBusModel.getTotalDuration());
        holder1.mTvStartTimeAA.setText(mBusModel.getmStartTimeAA());
        holder1.mTvEndTimeAA.setText(mBusModel.getmEndTimeAA());
        holder1.mTvHold.setText(String.format(mContext.getString(R.string.text_addhold), mBusModel.getHold()));
        holder1.mTvPrice.setText(String.format("$ %s", mBusModel.getPrice()));
        holder1.mTvRatingBar.setText(String.format(mContext.getString(R.string.text_add5), mBusModel.getRate()));

        holder1.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, BookingActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra(Constants.intentdata.TRAVELLERNAME, mBusModel.getTravelerName());
                i.putExtra(Constants.intentdata.TYPECOACH, mBusModel.getType_coach());
                i.putExtra(Constants.intentdata.PRICE, mBusModel.getPrice().replace("$", ""));
                i.putExtra(Constants.intentdata.HOLD, mBusModel.getHold());
                mContext.startActivity(i);
            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mBusList.size();
    }

    /*view holder*/
    class BusitemViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTravellerName, mTvStartTime, mTvEndTime, mTvTotalDuration, mTvHold, mTvPrice, mTvTypeCoach, mTvRatingBar,mTvStartTimeAA,mTvEndTimeAA;
        private RelativeLayout mRelativeLayout;

        BusitemViewHolder(View itemView) {
            super(itemView);
            mTvTravellerName = itemView.findViewById(R.id.tvTravellerName);
            mTvStartTime = itemView.findViewById(R.id.tvStartTime);
            mTvEndTime = itemView.findViewById(R.id.tvEndTime);
            mTvTotalDuration = itemView.findViewById(R.id.tvTotalDuration);
            mTvHold = itemView.findViewById(R.id.tvHold);
            mTvRatingBar = itemView.findViewById(R.id.tvRatingbar);
            mTvPrice = itemView.findViewById(R.id.tvPrice);
            mTvTypeCoach = itemView.findViewById(R.id.tvTypeCoach);
            mRelativeLayout = itemView.findViewById(R.id.rlMain);
            mTvStartTimeAA = itemView.findViewById(R.id.tvStartTimeAA);
            mTvEndTimeAA = itemView.findViewById(R.id.tvEndTimeAA);
        }

    }


}