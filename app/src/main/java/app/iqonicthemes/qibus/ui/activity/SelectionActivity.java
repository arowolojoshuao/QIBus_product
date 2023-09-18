package app.iqonicthemes.qibus.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUISplashActivity;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;

public class SelectionActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private Button mBtnContinue, btnSoftUiContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selection);
        initLayouts();
        initializeListeners();
    }

    /* init layout */
    private void initLayouts() {
        mBtnContinue = findViewById(R.id.btnContinue);
        btnSoftUiContinue = findViewById(R.id.btnSoftUiContinue);
    }

    /* initialize listener */

    @SuppressLint("ClickableViewAccessibility")
    private void initializeListeners() {
        mBtnContinue.setOnClickListener(this);
        mBtnContinue.setStateListAnimator(null);
        btnSoftUiContinue.setOnClickListener(this);
        btnSoftUiContinue.setStateListAnimator(null);
    }


    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mBtnContinue) {
            startActivity(SignInActivity.class);
            finish();
        }
        if (v == btnSoftUiContinue) {
            startActivity(QIBusSoftUISoftUISplashActivity.class);
            finish();
        }
    }
}
