package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.CardModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.viewcardViewHolder> {
    /*variable declaration*/
    private Context mCtx;
    private ArrayList<CardModel> mCardList;
    private onClickListener mListener;

    /*constructor*/
    public CardsAdapter(Context aCtx, ArrayList<CardModel> aCardList) {
        /* initialize parameter*/
        this.mCtx = aCtx;
        this.mCardList = aCardList;

    }
    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  remove item */
    public void removeItem(int position) {
        mCardList.remove(position);
        notifyItemRemoved(position);
    }

    /*  restore item */
    public void restoreItem(CardModel item, int position) {
        mCardList.add(position, item);
        notifyItemInserted(position);
    }

    /*  get data */
    public List<CardModel> getData() {
        return mCardList;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public CardsAdapter.viewcardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardsAdapter.viewcardViewHolder(LayoutInflater.from(mCtx).inflate(R.layout.item_card, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.viewcardViewHolder holder1, final int position) {

        final CardModel mCardModel = mCardList.get(position);
        holder1.mTvDigit1.setText(mCardModel.getTxtDigit1());
        holder1.mTvDigit2.setText(mCardModel.getTxtDigit2());
        holder1.mTvDigit3.setText(mCardModel.getTxtDigit3());
        holder1.mTvDigit4.setText(mCardModel.getTxtDigit4());
        holder1.mTvHolderName.setText(mCardModel.getTxtHolderName());
        holder1.mTvValidDate.setText(mCardModel.getmValidDate());
        holder1.mTvType.setText(mCardModel.getCardtype());

        ((BaseActivity)mCtx).setImage(mCardModel.getCardbg(),holder1.mCard);

        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    notifyDataSetChanged();
                    mListener.onClick(mCardModel);

                }
            }
        });
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(CardModel cardModel);
    }

    /*view holder*/
    class viewcardViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvDigit1, mTvDigit2, mTvDigit3, mTvDigit4, mTvHolderName, mTvValidDate, mTvType;
        private ImageView mCard;


        viewcardViewHolder(View itemView) {
            super(itemView);
            mTvDigit1 = itemView.findViewById(R.id.edDigit1);
            mTvDigit2 = itemView.findViewById(R.id.edDigit2);
            mTvDigit3 = itemView.findViewById(R.id.edDigit3);
            mTvDigit4 = itemView.findViewById(R.id.edDigit4);
            mTvHolderName = itemView.findViewById(R.id.tvHolderName);
            mTvValidDate = itemView.findViewById(R.id.tvValidDate);
            mCard = itemView.findViewById(R.id.ivCardbg);
            mTvType = itemView.findViewById(R.id.tvType);
        }
    }

}


