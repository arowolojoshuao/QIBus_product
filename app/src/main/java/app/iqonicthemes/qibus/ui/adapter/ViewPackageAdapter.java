package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.NewPackageModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPackageAdapter extends RecyclerView.Adapter<ViewPackageAdapter.PackageViewHolder> {
    /*variable declaration*/
    private Context mContext;
    private ArrayList<NewPackageModel> mPackageList;
    private onClickListener mListener;

    /*constructor*/
    public ViewPackageAdapter(Context aContext, ArrayList<NewPackageModel> aPackageList) {
        /* initialize parameter*/
        this.mContext = aContext;
        this.mPackageList = aPackageList;
    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public PackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PackageViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_viewpackage, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull final PackageViewHolder holder, int position) {

        final NewPackageModel model = mPackageList.get(position);

        holder.mTvDestination.setText(model.getDestination());
        holder.mTvDuration.setText(model.getDuration());
        holder.mTvNewPrice.setText(model.getNewprice());

        ((BaseActivity)mContext).setImage(model.getImage(),holder.mIvPlace);
        holder.mRating.setRating(Float.parseFloat(model.getRating()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    notifyDataSetChanged();
                    mListener.onClick(model);

                }
            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mPackageList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(NewPackageModel notificationModel);
    }

    /*view holder*/
    class PackageViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvDestination, mTvDuration, mTvNewPrice;
        private ImageView mIvPlace;
        private RatingBar mRating;
        PackageViewHolder(View itemView) {
            super(itemView);
            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvDuration = itemView.findViewById(R.id.tvDuration);
            mTvNewPrice = itemView.findViewById(R.id.tvLatestPrice);
            mIvPlace = itemView.findViewById(R.id.ivPlace);
            mRating = itemView.findViewById(R.id.ratingbar);
        }
    }

}


