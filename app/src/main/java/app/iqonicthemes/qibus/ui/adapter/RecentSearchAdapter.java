package app.iqonicthemes.qibus.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecentSearchAdapter extends RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder> {

    /*variable declaration*/
    private onClickListener mListener;
    private Context mContext;
    private ArrayList<BookingModel> mRecentSearchList;

    /*constructor*/
    public RecentSearchAdapter(Context aContext, ArrayList<BookingModel> aRecentSearchlist) {
        /* initialize parameter*/
        this.mContext = aContext;
        this.mRecentSearchList = aRecentSearchlist;
    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*  inflate layout */
    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public RecentSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecentSearchViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_recentsearch, null));
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(@NonNull RecentSearchViewHolder holder, int position) {

        final BookingModel mBookibgModel = mRecentSearchList.get(position);

        holder.mTvDestination.setText(mBookibgModel.getDestination());
        holder.mTvDate.setText(mBookibgModel.getDate());
        holder.mBtnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    notifyDataSetChanged();
                    mListener.onClick(mBookibgModel);

                }
            }
        });

    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mRecentSearchList.size();
    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(BookingModel cardModel);
    }

    /*view holder*/
    class RecentSearchViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvDestination, mTvDate;
        private Button mBtnBook;

        RecentSearchViewHolder(View itemView) {
            super(itemView);

            mTvDestination = itemView.findViewById(R.id.tvDestination);
            mTvDate = itemView.findViewById(R.id.tvDate);
            mBtnBook = itemView.findViewById(R.id.btnBook);

        }
    }

}

