package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.NewPackageModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.NewPackageViewHolder> {

    /*variable declaration*/
    private Context mCtx;
    private List<NewPackageModel> mPackagelist;
    private onClickListener mListener;

    /*constructor*/
    public PackageAdapter(Context aCtx, List<NewPackageModel> aPackageList) {
        /* initialize parameter*/
        this.mCtx = aCtx;
        this.mPackagelist = aPackageList;

    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public PackageAdapter.NewPackageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PackageAdapter.NewPackageViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_package, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull final PackageAdapter.NewPackageViewHolder holder1, int position) {

        final NewPackageModel mPackageModel = mPackagelist.get(position);
        holder1.mTvDestination.setText(mPackageModel.getDestination());
        holder1.mTvDuration.setText(mPackageModel.getDuration());
        holder1.mTvNewPrice.setText(mPackageModel.getNewprice());
        ((BaseActivity)mCtx).setImage(mPackageModel.getImage(),holder1.mIvPlace);

        holder1.mRating.setRating(Float.parseFloat(mPackageModel.getRating()));
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    notifyDataSetChanged();
                    mListener.onClick(mPackageModel);

                }
            }
        });


    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mPackagelist.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(NewPackageModel notificationModel);
    }

    /*view holder*/
    class NewPackageViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDestination, mTvDuration, mTvNewPrice;
        private ImageView mIvPlace;
        private RatingBar mRating;


        NewPackageViewHolder(View itemView) {
            super(itemView);
            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvDuration = itemView.findViewById(R.id.tvDuration);
            mTvNewPrice = itemView.findViewById(R.id.tvLatestPrice);
            mIvPlace = itemView.findViewById(R.id.ivPlace);
            mRating = itemView.findViewById(R.id.ratingbar);

        }

    }

}
