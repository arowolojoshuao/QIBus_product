package app.iqonicthemes.qibus.ui.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.ui.activity.CardsActivity;
import app.iqonicthemes.qibus.ui.activity.HelpActivity;
import app.iqonicthemes.qibus.ui.activity.ProfileSettingsActivity;
import app.iqonicthemes.qibus.ui.activity.ReferEarnActivity;
import app.iqonicthemes.qibus.ui.activity.SettingActivity;
import app.iqonicthemes.qibus.ui.activity.SignInActivity;
import app.iqonicthemes.qibus.ui.activity.WalletActivity;
import app.iqonicthemes.qibus.utils.Constants;

public class MoreFragment extends Fragment implements View.OnClickListener {

    /*variable declaration*/
    public static final String mTitle = "More";
    private TextView mTvProfileSettings, mTvWallet, mTvCards, mTvReferEarn, mTvHelp, mTvLogout, mTvSetting;
    private String mFlag = "1";

    /* create view */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, null);
        initLayouts(view);
        initializeListeners();
        return view;
    }

    /* initialize listener */
    private void initializeListeners() {
        mTvProfileSettings.setOnClickListener(this);
        mTvWallet.setOnClickListener(this);
        mTvCards.setOnClickListener(this);
        mTvReferEarn.setOnClickListener(this);
        mTvHelp.setOnClickListener(this);
        mTvLogout.setOnClickListener(this);
        mTvSetting.setOnClickListener(this);
    }

    /* init layout */
    private void initLayouts(View view) {
        mTvProfileSettings = view.findViewById(R.id.tvProfileSettings);
        mTvWallet = view.findViewById(R.id.tvWallet);
        mTvCards = view.findViewById(R.id.tvCards);
        mTvReferEarn = view.findViewById(R.id.tvReferEarn);
        mTvHelp = view.findViewById(R.id.tvHelp);
        mTvLogout = view.findViewById(R.id.tvLogout);
        mTvSetting = view.findViewById(R.id.tvSetting);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mTvProfileSettings)
            ((BaseActivity) Objects.requireNonNull(getActivity())).startActivity(ProfileSettingsActivity.class);
        else if (v == mTvWallet)
            ((BaseActivity) Objects.requireNonNull(getActivity())).startActivity(WalletActivity.class);
        else if (v == mTvCards) {
            Intent intent = new Intent(getActivity(), CardsActivity.class);
            intent.putExtra(Constants.intentdata.CARDFLAG, mFlag);
            startActivity(intent);
        } else if (v == mTvReferEarn)
            ((BaseActivity) Objects.requireNonNull(getActivity())).startActivity(ReferEarnActivity.class);
        else if (v == mTvHelp)
            ((BaseActivity) Objects.requireNonNull(getActivity())).startActivity(HelpActivity.class);
        else if (v == mTvSetting)
            ((BaseActivity) Objects.requireNonNull(getActivity())).startActivity(SettingActivity.class);
        else if (v == mTvLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(getString(R.string.text_confirmation)).setMessage(getString(R.string.msg_logout));
            builder.setPositiveButton(getString(R.string.text_yes),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            startActivity(new Intent(getActivity(), SignInActivity.class));
                        }
                    });
            builder.setNegativeButton(getString(R.string.text_no),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
