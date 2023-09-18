package app.iqonicthemes.qibus.ui.activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
public class SettingActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private ImageView mIvBack, mIvNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initLayouts();
        initializeListeners();
    }

    /* initialize listener */
    private void initializeListeners() {

        mIvBack.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        SetNotificationImage(mIvNotification);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mIvNotification = findViewById(R.id.ivNotification);

    }

    /* onClick listener */
    @Override
    public void onClick(View v) {

        if (v == mIvBack) {
            onBackPressed();
        } else if (v == mIvNotification) {
            startActivity(NotificationActivity.class);
        }
    }
}
