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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class PaymentActivity extends BaseActivity implements View.OnClickListener, CardsAdapter.onClickListener {

    /*variable declaration*/
    private ArrayList<CardModel> mCardList;
    private RecyclerView mRvCard;
    private ImageView mIvBack, mIvAdd;
    private CardsAdapter mCardAdapter;
    private RelativeLayout mRlMain, mRlDebit, mRlCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        initLayouts();
        initializeListeners();
        enableSwipeToDeleteAndUndo();
    }

    /* init layout */
    private void initLayouts() {
        mRvCard = findViewById(R.id.rvCard);
        mIvBack = findViewById(R.id.ivBack);
        mIvAdd = findViewById(R.id.ivAdd);
        mRlMain = findViewById(R.id.rlMain);
        mRlCredit = findViewById(R.id.rlCreditCard);
        mRlDebit = findViewById(R.id.rlDebitCard);

    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mIvAdd.setOnClickListener(this);
        mRlCredit.setOnClickListener(this);
        mRlDebit.setOnClickListener(this);

        mCardList = new ArrayList<>();
        mRvCard.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mCardList.add(new CardModel(getString(R.string.lbl_cardtype1), R.drawable.ic_card, getString(R.string.lbl_card_digit1), getString(R.string.lbl_card_digit2), getString(R.string.lbl_card_digit3), getString(R.string.lbl_card_digit4), getString(R.string.lbl_card_validdate1),  getString(R.string.lbl_booking_passengername1)));
        mCardAdapter = new CardsAdapter(this, mCardList);
        mRvCard.setAdapter(mCardAdapter);
        mCardAdapter.setOnClickListener(this);
        RunLayoutAnimation(mRvCard);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        else if (v == mIvAdd) startActivity(CardDetailActivity.class);
        else if (v == mRlCredit) startActivity(CardDetailActivity.class);
        else if (v == mRlDebit) startActivity(CardDetailActivity.class);

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

    /* onClick listener */
    @Override
    public void onClick(CardModel cardModel) {
        Intent intent = new Intent(this, CardDetailActivity.class);
        intent.putExtra(Constants.intentdata.CARDDETAIL, cardModel);
        startActivity(intent);
    }
}
