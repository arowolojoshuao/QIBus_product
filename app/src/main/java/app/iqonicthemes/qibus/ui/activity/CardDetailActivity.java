package app.iqonicthemes.qibus.ui.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.CardModel;
import app.iqonicthemes.qibus.ui.fragment.HomeFragmentNewest;
import app.iqonicthemes.qibus.utils.Constants;

import static app.iqonicthemes.qibus.ui.activity.BookingActivity.mTotal;
import static app.iqonicthemes.qibus.ui.activity.DroppingPointActivity.mDropping;
import static app.iqonicthemes.qibus.ui.activity.DroppingPointActivity.mPickup;


public class CardDetailActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/

    private ArrayAdapter<String> mYearAdapter, mMonthAdapter;
    private EditText mEdDigit1, mEdDigit2, mEdDigit3, mEdDigit4, mEdHolderName, mEdCode, mEdCvv;
    private Spinner mSpinYear, mSpinMonth;
    private String mFlagValue;
    private ImageView mIvBack, mIvShowPWd, mIVHidePwd;
    private ArrayList<String> mYearList, mMonthList;
    private CardModel mCardList;
    private TextView mTvTo, mTvPickup, mTvDropping, mTvTotal, mTvOfferCode, mTvFrom,mTvDetail;
    private Button mBtnBook;
    private LinearLayout mLlContent;
    private RelativeLayout mRlDetail,mRlHeading;
    private TextView mTvTitle, mTvTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        initLayouts();
        initializeListeners();
    }

    /* init layout */
    private void initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1);
        mEdDigit2 = findViewById(R.id.edDigit2);
        mEdDigit3 = findViewById(R.id.edDigit3);
        mEdDigit4 = findViewById(R.id.edDigit4);
        mEdHolderName = findViewById(R.id.edHolderName);
        mBtnBook = findViewById(R.id.btnBook);
        mRlDetail = findViewById(R.id.rlDetail);
        mTvTitle = findViewById(R.id.tvTitle);
        mIvShowPWd = findViewById(R.id.ivShowPwd);
        mEdCvv = findViewById(R.id.edCvv);
        mRlHeading = findViewById(R.id.rlHeading);
        mIVHidePwd = findViewById(R.id.ivHidePwd);

        mSpinYear = findViewById(R.id.spYear);
        mSpinMonth = findViewById(R.id.spMonth);

        mIvBack = findViewById(R.id.ivBack);
        mTvTo = findViewById(R.id.tvFromTo);
        mTvFrom = findViewById(R.id.tvFromName);
        mTvPickup = findViewById(R.id.tvFrom);
        mTvDropping = findViewById(R.id.tvTo);
        mLlContent = findViewById(R.id.llContent);
        mTvDetail = findViewById(R.id.tvDetail);
        mTvTotal = findViewById(R.id.tvTotal);
        mEdCode = findViewById(R.id.edCode);
        mTvOfferCode = findViewById(R.id.tvOfferCode);
        mTvTimer = findViewById(R.id.tvTimer);

    }

    /* initialize listener */
    private void initializeListeners() {

        mIvBack.setOnClickListener(this);
        mBtnBook.setOnClickListener(this);


        mRlHeading.setOnClickListener(this);
        mIvShowPWd.setOnClickListener(this);
        mIVHidePwd.setOnClickListener(this);
        mBtnBook.setStateListAnimator(null);

        mEdDigit1.addTextChangedListener(new GenericTextWatcher(mEdDigit1));
        mEdDigit2.addTextChangedListener(new GenericTextWatcher(mEdDigit2));
        mEdDigit3.addTextChangedListener(new GenericTextWatcher(mEdDigit3));
        mEdDigit4.addTextChangedListener(new GenericTextWatcher(mEdDigit4));
        mFlagValue = getIntent().getStringExtra(Constants.intentdata.CARDFLAG);
        if (mFlagValue != null) {

            if (mFlagValue.equals("1")) {
                hideView(mRlDetail);
                hideView(mTvOfferCode);
                hideView(mEdCode);
                mBtnBook.setText(getString(R.string.text_addcard));
            }

        } else {
            showView(mTvTimer);
            mTvTitle.setText(getString(R.string.text_payment));
            mTvTo.setText(HomeFragmentNewest.mTo);
            mTvFrom.setText(HomeFragmentNewest.mFrom);

            mTvPickup.setText(mPickup);
            mTvDropping.setText(mDropping);

            mTvTotal.setText(String.format("%s %s", getString(R.string.rs), String.valueOf(mTotal)));
            showView(mTvOfferCode);
            showView(mEdCode);


            new CountDownTimer(300000, 1000) { // adjust the milli seconds here

                @SuppressLint("DefaultLocale")
                public void onTick(long millisUntilFinished) {


                    mTvTimer.setText(String.format("%d : %d ",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                }

                public void onFinish() {
                    finish();
                }
            }.start();


        }
        mCardList = (CardModel)getIntent().getSerializableExtra(Constants.intentdata.CARDDETAIL);

        if (mCardList != null) {
            mEdDigit1.setEnabled(false);
            mEdDigit2.setEnabled(false);
            mEdDigit3.setEnabled(false);
            mEdDigit4.setEnabled(false);
            mEdHolderName.setEnabled(false);
            mEdDigit1.setText(mCardList.getTxtDigit1());
            mEdDigit2.setText(mCardList.getTxtDigit2());
            mEdDigit3.setText(mCardList.getTxtDigit3());
            mEdDigit4.setText(mCardList.getTxtDigit4());
            mEdHolderName.setText(mCardList.getTxtHolderName());

        }

        mYearList = new ArrayList<>();
        mMonthList = new ArrayList<>();

        int month = Calendar.getInstance().get(Calendar.MONTH);

        for (int j = 1; j <= 12; j++) {
            mMonthList.add(Integer.toString(j));
        }
        for (int i = 2019; i <= 2040; i++) {
            mYearList.add(Integer.toString(i));
        }

        mYearAdapter = new ArrayAdapter<>(this, R.layout.spinner_items, mYearList);
        mMonthAdapter = new ArrayAdapter<>(this, R.layout.spinner_items, mMonthList);

        mSpinYear.setAdapter(mYearAdapter);
        mSpinMonth.setAdapter(mMonthAdapter);

        mSpinMonth.setSelection(month);

        mEdCode.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdCode.length() > 0) {
                    mEdCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_checkbox_circle, 0);
                } else {
                    mEdCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
    }


    /* onClick listener */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivShowPwd:
                hideView(mIvShowPWd);
                showView(mIVHidePwd);
                mEdCvv.setSelection(mEdCvv.length());
                mEdCvv.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                break;
            case R.id.ivHidePwd:
                mEdCvv.setSelection(mEdCvv.length());
                mEdCvv.setTransformationMethod(PasswordTransformationMethod.getInstance());

                hideView(mIVHidePwd);
                showView(mIvShowPWd);
                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.btnBook:
                //    if (validate()) {
                if (mFlagValue != null) {
                    if (mFlagValue.equals(getString(R.string.text_1))) {
                        showToast(getString(R.string.text_cardadd));
                    }
                } else {
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.dialog_success);
                    dialog.setCancelable(true);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    dialog.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (dialog.isShowing())
                                dialog.dismiss();
                            startActivity(DashboardActivity.class);
                        }
                    });
                    dialog.show();
                }
                //     }
                break;
            case R.id.rlHeading:
                if (mLlContent.getVisibility() == View.GONE) {
                    showView(mLlContent);
                    mTvDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black, 0);

                } else {
                    mTvDetail.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_right, 0);
                    hideView(mLlContent);

                }
                break;
        }


    }

    /* validation */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdDigit1.getText())) {
            flag = false;
            showToast(getString(R.string.msg_digit));
        } else if (TextUtils.isEmpty(mEdDigit2.getText())) {
            flag = false;
            showToast(getString(R.string.msg_digit));
        } else if (TextUtils.isEmpty(mEdDigit3.getText())) {
            flag = false;
            showToast(getString(R.string.msg_digit));
        } else if (TextUtils.isEmpty(mEdDigit4.getText())) {
            flag = false;
            showToast(getString(R.string.msg_digit));
        } else if (TextUtils.isEmpty(mEdHolderName.getText())) {
            flag = false;
            showToast(getString(R.string.msg_holdername));
        }
        return flag;
    }

    /* implement textwatcher */
    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            String text = editable.toString();
            switch (view.getId()) {
                case R.id.edDigit1:
                    if (text.length() == 4)
                        mEdDigit2.requestFocus();
                    break;
                case R.id.edDigit2:
                    if (text.length() == 4)
                        mEdDigit3.requestFocus();
                    else if (text.length() == 0)
                        mEdDigit1.requestFocus();
                    break;
                case R.id.edDigit3:
                    if (text.length() == 4)
                        mEdDigit4.requestFocus();
                    else if (text.length() == 0)
                        mEdDigit2.requestFocus();
                    break;
                case R.id.edDigit4:
                    if (text.length() == 0)
                        mEdDigit3.requestFocus();
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }
    }
}
