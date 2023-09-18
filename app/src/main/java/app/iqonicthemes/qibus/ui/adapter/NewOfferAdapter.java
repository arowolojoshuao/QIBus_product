package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.NewOfferModel;

import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewOfferAdapter extends RecyclerView.Adapter<NewOfferAdapter.NewofferViewHolder> {

    /*variable declaration*/
    private Context mContext;
    private ArrayList<NewOfferModel> mOfferList;
    private onClickListener mListener;

    /*constructor*/
    public NewOfferAdapter(Context aContext, ArrayList<NewOfferModel> aOfferList) {
        /* initialize parameter*/
        this.mContext = aContext;
        this.mOfferList = aOfferList;
    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public NewOfferAdapter.NewofferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewOfferAdapter.NewofferViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_newoffers, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull NewOfferAdapter.NewofferViewHolder holder, final int position) {

        NewOfferModel newOfferModel = mOfferList.get(position);

        holder.mTvSpecial.setText(newOfferModel.getUsecode());

        int[] androidColors = mContext.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.mRlOfferBackground.setBackgroundColor(randomAndroidColor);

        if (position % 2 == 0) {
            holder.ivImageSrc.setImageResource(R.drawable.ic_sale_1);
        } else {
            holder.ivImageSrc.setImageResource(R.drawable.ic_sale_2);
        }

        holder.mLlOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity)mContext).showToast(mContext.getString(R.string.txt_copy));

            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mOfferList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(int i);
    }

    /*view holder*/
    class NewofferViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvSpecial;
        private ImageView ivImageSrc;
        private MaterialCardView mRlOfferBackground;
        private LinearLayout mLlOffer;

        NewofferViewHolder(View itemView) {
            super(itemView);
            mTvSpecial = itemView.findViewById(R.id.tvSpecial);

            ivImageSrc = itemView.findViewById(R.id.ivImageSrc);
            mRlOfferBackground = itemView.findViewById(R.id.rlOfferBackground);
            mLlOffer = itemView.findViewById(R.id.llOffer);
        }
    }

}

