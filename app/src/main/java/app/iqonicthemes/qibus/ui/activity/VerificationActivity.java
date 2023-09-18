package app.iqonicthemes.qibus.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;


public class VerificationActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/
    private EditText mEdDigit1, mEdDigit2, mEdDigit3, mEdDigit4;
    private LinearLayout mLlVerify;
    private TextView mTvResend, mTvTimer;
    private ImageView mIvBack;
    private EditText[] mEds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        initLayouts();
        initializeListeners();
    }

    /* init layout */
    private void initLayouts() {
        mEdDigit1 = findViewById(R.id.edDigit1);
        mEdDigit2 = findViewById(R.id.edDigit2);
        mEdDigit3 = findViewById(R.id.edDigit3);
        mEdDigit4 = findViewById(R.id.edDigit4);
        mLlVerify = findViewById(R.id.llVerify);
        mTvResend = findViewById(R.id.tvResend);
        mTvTimer = findViewById(R.id.tvTimer);
        mEds = new EditText[]{mEdDigit1, mEdDigit2, mEdDigit3, mEdDigit4};

        mIvBack = findViewById(R.id.ivBack);
    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);


        mEdDigit1.setOnKeyListener(new PinOnKeyListener(0));
        mEdDigit2.setOnKeyListener(new PinOnKeyListener(1));
        mEdDigit3.setOnKeyListener(new PinOnKeyListener(2));
        mEdDigit4.setOnKeyListener(new PinOnKeyListener(3));
        mEdDigit1.addTextChangedListener(new CodeTextWatcher(0));
        mEdDigit2.addTextChangedListener(new CodeTextWatcher(1));
        mEdDigit3.addTextChangedListener(new CodeTextWatcher(2));
        mEdDigit4.addTextChangedListener(new CodeTextWatcher(3));
        mLlVerify.setOnClickListener(this);

        new CountDownTimer(60000, 1000) { // adjust the milli seconds here

            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                mTvTimer.setText(String.format("%d seconds left",
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            public void onFinish() {
                hideView(mTvTimer);
                showView(mTvResend);
            }
        }.start();

        mEdDigit4.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (validate()) {
                        //        if(validate()) {
                        startActivity(DashboardActivity.class);
                        //     }
                    }
                    return true;
                }
                return false;
            }

        });

    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) {
            onBackPressed();
        }
        if (v == mLlVerify) {
            //  if(validate()) {
            startActivity(DashboardActivity.class);
            // }
        }

    }

    /* Validation */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdDigit1.getText())) {
            flag = false;
            showToast(getString(R.string.msg_code));
        } else if (TextUtils.isEmpty(mEdDigit2.getText())) {
            flag = false;
            showToast(getString(R.string.msg_code));
        } else if (TextUtils.isEmpty(mEdDigit3.getText())) {
            flag = false;
            showToast(getString(R.string.msg_code));
        } else if (TextUtils.isEmpty(mEdDigit4.getText())) {
            flag = false;
            showToast(getString(R.string.msg_code));
        }

        return flag;
    }

    /* back space key handler*/
    public class PinOnKeyListener implements View.OnKeyListener {

        private int mCurrentIndex;

        PinOnKeyListener(int currentIndex) {
            this.mCurrentIndex = currentIndex;
        }

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (mEds[mCurrentIndex].getText().toString().isEmpty() && mCurrentIndex != 0) {
                    mEds[mCurrentIndex - 1].requestFocus();

                }

            }
            return false;
        }

    }

    /* implement TextWatcher class*/
    public class CodeTextWatcher implements TextWatcher {

        private int mCurrentIndex;
        private boolean mIsFirst = false, mIsLast = false;
        private String mNewString = "";

        CodeTextWatcher(int currentIndex) {
            this.mCurrentIndex = currentIndex;

            if (currentIndex == 0)
                this.mIsFirst = true;
            else if (currentIndex == mEds.length - 1)
                this.mIsLast = true;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mNewString = s.subSequence(start, start + count).toString().trim();
        }

        @Override
        public void afterTextChanged(Editable s) {

            String text = mNewString;

            if (text.length() > 1)
                text = String.valueOf(text.charAt(0));

            mEds[mCurrentIndex].removeTextChangedListener(this);
            mEds[mCurrentIndex].setText(text);
            mEds[mCurrentIndex].setSelection(text.length());
            mEds[mCurrentIndex].addTextChangedListener(this);

            if (text.length() == 1)
                moveToNext();
            else if (text.length() == 0)
                moveToPrevious();
        }

        private void moveToNext() {
            if (!mIsLast)
                mEds[mCurrentIndex + 1].requestFocus();

            if (isAllEditTextsFilled() && mIsLast) {
                mEds[mCurrentIndex].clearFocus();
                hideKeyboard();
            }
        }

        private void moveToPrevious() {
            if (!mIsFirst)
                mEds[mCurrentIndex - 1].requestFocus();
        }

        private boolean isAllEditTextsFilled() {
            for (EditText editText : mEds)
                if (editText.getText().toString().trim().length() == 0)
                    return false;
            return true;
        }

        private void hideKeyboard() {
            if (getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            }

        }
    }
}
