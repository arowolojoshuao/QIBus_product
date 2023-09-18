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

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;

public class SignInActivity extends BaseActivity implements View.OnClickListener {
    /*variable declaration*/
    private Button mBtnContinue;
    private EditText mEdMobileNumber;
    private ImageView mIvFacebook, mIvGoogle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_in);

        initLayouts();
        initializeListeners();


    }

    /* init layout */
    private void initLayouts() {
        mEdMobileNumber = findViewById(R.id.edMobileNumber);
        mBtnContinue = findViewById(R.id.btnContinue);
        mIvFacebook = findViewById(R.id.ivFacebook);
        mIvGoogle = findViewById(R.id.ivGoogle);
    }

    /* initialize listener */

    @SuppressLint("ClickableViewAccessibility")
    private void initializeListeners() {

        mBtnContinue.setOnClickListener(this);
        mIvFacebook.setOnClickListener(this);
        mIvGoogle.setOnClickListener(this);
        mBtnContinue.setStateListAnimator(null);


        mEdMobileNumber.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //  if (validate()) {

                    startActivity(VerificationActivity.class);
                    //  }
                    return true;
                }
                return false;
            }

        });
    }

    /* validation */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdMobileNumber.getText())) {
            flag = false;
            showToast(getString(R.string.msg_mobile_number));
        }
        return flag;
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mBtnContinue) {
            //  if (validate()) {

            startActivity(VerificationActivity.class);
            //  }
        } else if (v == mIvFacebook) {

            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.text_facebooklink)));
            startActivity(i);

        } else if (v == mIvGoogle) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(getString(R.string.text_googlelink)));
            startActivity(i);
        }
    }
}
