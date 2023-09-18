package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import app.iqonicthemes.qibus.R;import app.iqonicthemes.qibus.model.DroppingModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DroppingAdapter extends RecyclerView.Adapter<DroppingAdapter.ViewHolder> {
    /*variable declaration*/
    private Context mCtx;
    private List<DroppingModel> mList;
    private onClickListener mListener;
    private int mLastSelectedPosition = -1;

    /*constructor*/
    public DroppingAdapter(Context aCtx, List<DroppingModel> aList) {
        /* initialize parameter*/
        this.mCtx = aCtx;
        this.mList = aList;

    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public DroppingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DroppingAdapter.ViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_dropping1, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull final DroppingAdapter.ViewHolder holder1, final int position) {
        final DroppingModel mModel = mList.get(position);

        // set the data in items
        holder1.mTvTravelName.setText(mModel.getTravelName());
        holder1.mTvTime.setText(mModel.getDuration());
        holder1.mTvLocation.setText(mModel.getLoction());

        if (mLastSelectedPosition == -1) {
            holder1.mCard.setBackground(mCtx.getResources().getDrawable(R.drawable.bg_square));
        } else {
            if (mLastSelectedPosition == position) {

                holder1.mCard.setBackground(mCtx.getResources().getDrawable(R.drawable.bg_border));


            } else {
                holder1.mCard.setBackground(mCtx.getResources().getDrawable(R.drawable.bg_square));
            }
        }
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {

                        mLastSelectedPosition = position;
                        notifyDataSetChanged();
                        mListener.onClick(position, mModel.getTravelName());

                }
            }

        });

    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(int i, String name);
    }

    /*view holder*/
    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTravelName, mTvTime, mTvLocation;
        private MaterialCardView mCard;


        ViewHolder(View itemView) {
            super(itemView);
            mTvTravelName = itemView.findViewById(R.id.tvTravelName);
            mTvTime = itemView.findViewById(R.id.tvTime);
            mTvLocation = itemView.findViewById(R.id.tvLocation);
            mCard = itemView.findViewById(R.id.relative);
        }

    }

}
