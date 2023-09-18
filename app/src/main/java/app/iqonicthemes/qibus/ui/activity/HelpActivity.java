package app.iqonicthemes.qibus.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import androidx.annotation.Nullable;

public class HelpActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private ImageView mIvBack, mIvNotification;
    private EditText mEdContact, mEdEmail;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        initLayouts();
        initialzeListeners();
    }

    /* initialize listener */
    private void initialzeListeners() {
        mIvBack.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        setTypeFace(mEdContact);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mEdContact = findViewById(R.id.edContact);
        mEdEmail = findViewById(R.id.edEmail);
        mIvNotification = findViewById(R.id.ivNotification);
        mBtnSubmit = findViewById(R.id.btnSubmit);
        mBtnSubmit.setStateListAnimator(null);
        SetNotificationImage(mIvNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        else if (v == mBtnSubmit) {
           // if (validate()) {
                onBackPressed();
           // }
        } else if (v == mIvNotification) startActivity(NotificationActivity.class);
    }

    /* validations */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdEmail.getText())) {
            flag = false;
            showToast(getString(R.string.msg_email_required));
        }
        return flag;
    }
}