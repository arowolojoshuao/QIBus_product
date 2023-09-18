package app.iqonicthemes.qibus.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import app.iqonicthemes.qibus.BaseActivity;
import androidx.annotation.Nullable;
import app.iqonicthemes.qibus.R;

public class WalletActivity extends BaseActivity implements View.OnClickListener {

    /*variable declaration*/
    private ImageView mIvBack, mIvFaceBook, mIvWhatsapp, mIvGoogle, mIvTwitter, mIvNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initLayouts();
        initializeListeners();
    }

    /* initialize listener */
    private void initializeListeners() {

        mIvBack.setOnClickListener(this);
        mIvFaceBook.setOnClickListener(this);
        mIvWhatsapp.setOnClickListener(this);
        mIvGoogle.setOnClickListener(this);
        mIvTwitter.setOnClickListener(this);
        mIvNotification.setOnClickListener(this);
        SetNotificationImage(mIvNotification);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mIvFaceBook = findViewById(R.id.ivFaceBook);
        mIvWhatsapp = findViewById(R.id.ivWhatsapp);
        mIvGoogle = findViewById(R.id.ivGoogle);
        mIvTwitter = findViewById(R.id.ivTwitter);
        mIvNotification = findViewById(R.id.ivNotification);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {

        if (v == mIvBack) onBackPressed();
        else if (v == mIvFaceBook) sharedata();
        else if (v == mIvWhatsapp) sharedata();
        else if (v == mIvGoogle) sharedata();
        else if (v == mIvTwitter) sharedata();
        else if (v == mIvNotification) startActivity(NotificationActivity.class);
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

