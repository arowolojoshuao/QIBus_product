package app.iqonicthemes.qibus.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import androidx.annotation.Nullable;

public class ReferEarnActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/
    private TextView mTvLink, mTvCode;
    private ImageView mIvBack, mIvFaceBook, mIvWhatsapp, mIvGoogle, mIvTwitter, mIvNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refer_earn);

        initLayouts();
        initializeListeners();
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mTvLink = findViewById(R.id.tvLink);
        mTvCode = findViewById(R.id.tvCode);
        mIvFaceBook = findViewById(R.id.ivFaceBook);
        mIvWhatsapp = findViewById(R.id.ivWhatsapp);
        mIvGoogle = findViewById(R.id.ivGoogle);
        mIvTwitter = findViewById(R.id.ivTwitter);
        mIvNotification = findViewById(R.id.ivNotification);

    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mTvCode.setOnClickListener(this);
        mIvFaceBook.setOnClickListener(this);
        mIvWhatsapp.setOnClickListener(this);
        mIvGoogle.setOnClickListener(this);
        mIvTwitter.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        mTvLink.setText((getString(R.string.text_link)));
        SetNotificationImage(mIvNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
        else if (v == mTvCode)
            showToast(getString(R.string.txt_copy));
        else if (v == mIvFaceBook)
            sharedata();
        else if (v == mIvWhatsapp)
            sharedata();
        else if (v == mIvGoogle)
            sharedata();
        else if (v == mIvTwitter)
            sharedata();
        else if (v == mIvNotification) {
            startActivity(NotificationActivity.class);
        }
    }

    /* share data */
    private void sharedata() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, R.string.msg_shareapp);
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
    }
}
