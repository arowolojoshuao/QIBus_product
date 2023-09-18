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


public class ProfileSettingsActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/
    private ImageView mIvBack, mIvNotification,mIvAddProfile;
    private Button mBtnSave;
    private EditText mEdFirstName, mEdLastName, mEdEmail, mEdContact;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initLayouts();
        initializeListeners();

    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mBtnSave.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        mIvAddProfile.setOnClickListener(this);
        mBtnSave.setStateListAnimator(null);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mBtnSave = findViewById(R.id.btnSave);
        mEdFirstName = findViewById(R.id.edFirstName);
        mEdLastName = findViewById(R.id.edLastName);
        mEdEmail = findViewById(R.id.edEmail);
        mEdContact = findViewById(R.id.edContact);
        mIvAddProfile = findViewById(R.id.ivAddProfile);
        mIvNotification = findViewById(R.id.ivNotification);
        SetNotificationImage(mIvNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        else if (v == mIvNotification) startActivity(NotificationActivity.class);
        else if (v == mBtnSave) {
          //  if (validate()) {
                showToast(getString(R.string.msg_saved));
          //  }
        }
    }

    /* validations */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdFirstName.getText())) {
            flag = false;
            showToast(getString(R.string.msg_first_name));
        } else if (TextUtils.isEmpty(mEdLastName.getText())) {
            flag = false;
            showToast(getString(R.string.msg_last_name));
        } else if (TextUtils.isEmpty(mEdEmail.getText())) {
            flag = false;
            showToast(getString(R.string.msg_email_required));
        } else if (TextUtils.isEmpty(mEdContact.getText())) {
            flag = false;
            showToast(getString(R.string.msg_mobile_number));
        }

        return flag;
    }


}
