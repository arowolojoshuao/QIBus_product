package app.iqonicthemes.qibus.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.SeatModel;
import app.iqonicthemes.qibus.model.SeatType;
import app.iqonicthemes.qibus.utils.menu.AbstractItem;
import app.iqonicthemes.qibus.utils.menu.OnSeatSelected;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SleeperAdapter extends SelectableAdapter<RecyclerView.ViewHolder> {

    /*variable declaration*/
    private OnSeatSelected mOnSeatSelected;
    private Context mCtx;
    private LayoutInflater mLayoutInflater;
    private List<AbstractItem> mItems;
    private List<SeatModel> mSeatItem;
    private onClickListener mListener;

    /*constructor*/
    public SleeperAdapter(OnSeatSelected onSeatSelected, List<AbstractItem> items, Context context, List<SeatModel> seatModelList) {
        /* initialize parameter*/
        mOnSeatSelected = onSeatSelected;
        this.mCtx = context;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = items;
        this.mSeatItem = seatModelList;

    }

    /*set onClick listener*/
    public void setOnClickListener(onClickListener mListener) {
        this.mListener = mListener;
    }

    /*item count*/
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    /*item type*/
    @Override
    public int getItemViewType(int position) {
        return mItems.get(position).getType();
    }

    /*view holder*/
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == AbstractItem.TYPE_CENTER) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_sleeper, parent, false);
            return new CenterViewHolder(itemView);
        } else if (viewType == AbstractItem.TYPE_EDGE) {
            View itemView = mLayoutInflater.inflate(R.layout.list_item_sleeper, parent, false);
            return new EdgeViewHolder(itemView);
        } else {
            View itemView = new View(mCtx);
            return new EmptyViewHolder(itemView);
        }
    }

    /*bind viewholder*/
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {
        int type = mItems.get(position).getType();

        final SeatModel seatModel = mSeatItem.get(position);

        if (type == AbstractItem.TYPE_CENTER) {

            final CenterViewHolder holder = (CenterViewHolder)viewHolder;
            holder.mTvSeatNo.setText(mItems.get(position).getmLabel());
            int i = Integer.parseInt(mItems.get(position).getmLabel());
            if (i <= 9) {
                holder.mTvSeatNo.setText(String.format("L0%s", mItems.get(position).getmLabel()));
            } else {
                holder.mTvSeatNo.setText(String.format("L%s", mItems.get(position).getmLabel()));

            }
            if (seatModel.isSelected()) {
                ((BaseActivity)mCtx).showView(holder.mTvSeatNo);
            } else {
                ((BaseActivity)mCtx).invisibleView(holder.mTvSeatNo);

            }

            if ((seatModel.getSeatType()).equals(SeatType.EMPTY)) {

                holder.mIVSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toggleSelection(position);
                        seatModel.setSelected(!seatModel.isSelected());
                        notifyItemChanged(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());

                    }
                });


                holder.mIVSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }

            if ((seatModel.getSeatType()).equals(SeatType.BOOKED)) {
                ((BaseActivity)mCtx).showView(holder.mIvSeatBooked);
                holder.mIvSeatBooked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity)mCtx).showToast(mCtx.getString(R.string.text_booked));
                    }
                });
            }
            if ((seatModel.getSeatType()).equals(SeatType.LADIES)) {
                ((BaseActivity)mCtx).showView(holder.mIVSeatLadies);
                holder.mIVSeatLadies.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity)mCtx).showToast(mCtx.getString(R.string.text_booked));
                    }
                });
            } else {
                holder.mIVSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toggleSelection(position);
                        seatModel.setSelected(!seatModel.isSelected());
                        notifyItemChanged(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());

                    }
                });


                holder.mIVSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }

        } else if (type == AbstractItem.TYPE_EDGE) {

            final EdgeViewHolder holder = (EdgeViewHolder)viewHolder;

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onClick(seatModel);
                    }
                }
            });
            int i = Integer.parseInt(mItems.get(position).getmLabel());
            if (i <= 9) {
                holder.mTvSeatNo.setText(String.format("L0%s", mItems.get(position).getmLabel()));
            } else {
                holder.mTvSeatNo.setText(String.format("L%s", mItems.get(position).getmLabel()));

            }
            if (seatModel.isSelected()) {
                ((BaseActivity)mCtx).showView(holder.mTvSeatNo);
            } else {
                ((BaseActivity)mCtx).invisibleView(holder.mTvSeatNo);

            }
            if ((seatModel.getSeatType()).equals(SeatType.EMPTY)) {

                holder.mIvSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toggleSelection(position);
                        seatModel.setSelected(!seatModel.isSelected());
                        notifyItemChanged(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());

                    }
                });


                holder.mIvSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }

            if ((seatModel.getSeatType()).equals(SeatType.BOOKED)) {
                ((BaseActivity)mCtx).showView(holder.mIVSeatBooked);
                holder.mIVSeatBooked.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity)mCtx).showToast(mCtx.getString(R.string.text_booked));
                    }
                });
            }
            if ((seatModel.getSeatType()).equals(SeatType.LADIES)) {
                ((BaseActivity)mCtx).showView(holder.mIvSeatLadies);
                holder.mIvSeatLadies.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((BaseActivity)mCtx).showToast(mCtx.getString(R.string.text_booked));
                    }
                });
            } else {
                holder.mIvSeat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        toggleSelection(position);
                        seatModel.setSelected(!seatModel.isSelected());
                        notifyItemChanged(position);
                        mOnSeatSelected.onSeatSelected(getSelectedItemCount(), mItems.get(position).getmLabel());

                    }
                });


                holder.mIvSeatSelected.setVisibility(isSelected(position) ? View.VISIBLE : View.INVISIBLE);
            }
        }


    }

    /*onclick listener interface*/
    public interface onClickListener {

        void onClick(SeatModel seatModel);
    }

    /*view holder*/
    private static class EdgeViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIvSeatSelected;
        private ImageView mIvSeat, mIVSeatBooked, mIvSeatLadies;

        private TextView mTvSeatNo;

        EdgeViewHolder(View itemView) {
            super(itemView);
            mIvSeat = itemView.findViewById(R.id.ivSeat);
            mIvSeatSelected = itemView.findViewById(R.id.ivSeatSelected);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mIVSeatBooked = itemView.findViewById(R.id.ivSeatBooked);
            mIvSeatLadies = itemView.findViewById(R.id.ivSeatLadies);
        }

    }

    /*view holder*/
    private static class CenterViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIVSeatSelected;
        private ImageView mIVSeat, mIVSeatLadies, mIvSeatBooked;
        private TextView mTvSeatNo;

        CenterViewHolder(View itemView) {
            super(itemView);
            mIVSeat = itemView.findViewById(R.id.ivSeat);
            mIVSeatSelected = itemView.findViewById(R.id.ivSeatSelected);
            mTvSeatNo = itemView.findViewById(R.id.tvSeatNo);
            mIVSeatLadies = itemView.findViewById(R.id.ivSeatLadies);
            mIvSeatBooked = itemView.findViewById(R.id.ivSeatBooked);

        }

    }

    /*view holder*/
    private static class EmptyViewHolder extends RecyclerView.ViewHolder {

        EmptyViewHolder(View itemView) {
            super(itemView);
        }

    }

}
