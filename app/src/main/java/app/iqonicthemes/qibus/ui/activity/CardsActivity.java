package app.iqonicthemes.qibus.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.CardModel;
import app.iqonicthemes.qibus.ui.adapter.CardsAdapter;
import app.iqonicthemes.qibus.utils.Constants;
import app.iqonicthemes.qibus.utils.SwipeToDeleteCallback;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CardsActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/

    private ArrayList<CardModel> mCardList;
    private RecyclerView mRvCard;
    private ImageView mIvBack, mIVAddCardDetail;
    private CardsAdapter mCardAdapter;
    private RelativeLayout mRlMain;
    private String mFlags;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        initLayouts();
        initializeListeners();
        enableSwipeToDeleteAndUndo();
    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mIVAddCardDetail.setOnClickListener(this);
    }

    /* init layout */
    private void initLayouts() {
        setTitle(R.string.tite_toolbar_cards);

        mRvCard = findViewById(R.id.rvCard);
        mIvBack = findViewById(R.id.ivBack);
        mRlMain = findViewById(R.id.rlMain);
        mIVAddCardDetail = findViewById(R.id.ivAddCardDetail);
        mRvCard.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCardList = new ArrayList<>();


        mCardList.add(new CardModel(getString(R.string.lbl_cardtype1), R.drawable.ic_card, getString(R.string.lbl_card_digit1), getString(R.string.lbl_card_digit2), getString(R.string.lbl_card_digit3), getString(R.string.lbl_card_digit4), getString(R.string.lbl_card_validdate1),  getString(R.string.lbl_booking_passengername1)));
        mCardList.add(new CardModel(getString(R.string.lbl_cardtype2), R.drawable.ic_card3, getString(R.string.lbl_card_digit2), getString(R.string.lbl_card_digit4), getString(R.string.lbl_card_digit1), getString(R.string.lbl_card_digit3), getString(R.string.lbl_card_validdate1), getString(R.string.lbl_booking_passengername1)));

        mCardAdapter = new CardsAdapter(this, mCardList);
        mRvCard.setAdapter(mCardAdapter);
        RunLayoutAnimation(mRvCard);

        mFlags = getIntent().getStringExtra(Constants.intentdata.CARDFLAG);

    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        if (v == mIVAddCardDetail) {
            Intent intent = new Intent(CardsActivity.this, CardDetailActivity.class);
            intent.putExtra(Constants.intentdata.CARDFLAG, mFlags);
            startActivity(intent);
        }
    }

    /* swipe to delete & undo */
    private void enableSwipeToDeleteAndUndo() {
        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {


                final int position = viewHolder.getAdapterPosition();
                CardModel mCardModel;
                mCardModel = mCardAdapter.getData().get(position);

                mCardAdapter.removeItem(position);


                Snackbar snackbar = Snackbar
                        .make(mRlMain, getString(R.string.text_remove), Snackbar.LENGTH_LONG);
                final CardModel finalNotificationModel = mCardModel;
                snackbar.setAction(getString(R.string.text_undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        mCardAdapter.restoreItem(finalNotificationModel, position);
                        mRvCard.scrollToPosition(position);
                    }
                });

                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(mRvCard);
    }

}
