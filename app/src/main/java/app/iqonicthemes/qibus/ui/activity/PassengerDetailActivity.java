package app.iqonicthemes.qibus.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;

public class PassengerDetailActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private Button mBtnBook;
    private LinearLayout mLlDynamicContent;
    private int mCount;
    private String[] mSplited;
    private EditText mEdEmail, mEdMobileNumber, mEdFirstName, mEdAge;
    private ImageView mIVBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_detail);
        initLayouts();
        initializeListeners();

    }

    /* init layout */
    private void initLayouts() {
        mBtnBook = findViewById(R.id.btnBook);
        mLlDynamicContent = findViewById(R.id.llDynamicContent);
        mEdEmail = findViewById(R.id.edEmail);
        mEdMobileNumber = findViewById(R.id.edMobileNumber);
        mIVBack = findViewById(R.id.ivBack);
    }

    /* initialize listener */
    private void initializeListeners() {
        mBtnBook.setOnClickListener(this);
        mIVBack.setOnClickListener(this);
        mBtnBook.setStateListAnimator(null);
        mCount = BookingActivity.mCountSeat;
        setTypeFace(mEdMobileNumber);

        mSplited = String.valueOf(BookingActivity.mSb).split(" ");
        int i = 0;
        while (i < mCount) {
            i++;
            View view1 = getLayoutInflater().inflate(R.layout.item_passenger1, mLlDynamicContent, false);
            TextView mTvSeatNo = view1.findViewById(R.id.tvSeatNo);
            final RelativeLayout mRlHeading = view1.findViewById(R.id.rlHeading);
            final RelativeLayout mRlSubHeading = view1.findViewById(R.id.rlSubHeading);
            final ImageView mIvIcon = view1.findViewById(R.id.ivIcon);
            mEdFirstName = view1.findViewById(R.id.edFirstName);
            mEdAge = view1.findViewById(R.id.edAge);
            setTypeFace(mEdAge);
            mRlHeading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mRlSubHeading.getVisibility() == View.VISIBLE) {

                        mIvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_down_black));

                        hideView(mRlSubHeading);
                    } else {
                        mIvIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_keyboard_arrow_up_black));
                        showView(mRlSubHeading);

                    }
                }
            });
            mTvSeatNo.setText(String.format("L%s", mSplited[i - 1]));
            mLlDynamicContent.addView(view1);
        }
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mBtnBook) {
           // if (validate()) {
                 startActivity(PaymentActivity.class);
          //  }
        } else if (v == mIVBack) {
            onBackPressed();
        }
    }

    /* validations */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdEmail.getText())) {
            flag = false;
            showToast(getString(R.string.msg_email_id));
        } else if (TextUtils.isEmpty(mEdMobileNumber.getText())) {
            flag = false;
            showToast(getString(R.string.msg_mobile_number));
        } else if (TextUtils.isEmpty(mEdFirstName.getText())) {
            flag = false;
            showToast(getString(R.string.msg_name));
        } else if (TextUtils.isEmpty(mEdAge.getText())) {
            flag = false;
            showToast(getString(R.string.ms_age));
        }
        return flag;
    }
}
