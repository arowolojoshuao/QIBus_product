package app.iqonicthemes.qibus.ui.activity;


import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;

public class SplashActivity extends BaseActivity {

    /*variable declaration*/
    private ImageView mIVLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initLayouts();
        initializeListeners();

    }

    /* init layout */
    private void initLayouts() {

        mIVLogo = findViewById(R.id.ivLogo);
    }

    /* initialize listener */
    private void initializeListeners() {
        Glide.with(this).load(R.raw.ic_logo).into(mIVLogo);

        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          startActivity(SelectionActivity.class);
                                          onBackPressed();
                                      }
                                  },
                2000);
    }

}
