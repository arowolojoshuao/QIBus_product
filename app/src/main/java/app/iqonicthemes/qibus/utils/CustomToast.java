package app.iqonicthemes.qibus.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import app.iqonicthemes.qibus.R;


public class CustomToast extends Toast {
    /*variable declaration*/
    private Context mCtx;

    @Override
    public void setView(View view) {
        super.setView(view);
    }

    /*constructor*/
    public CustomToast(Context context) {
        super(context);
        this.mCtx = context;
    }

    /*set custom view*/
    public void setCustomView(String message) {
        LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_toast, null);
        TextView mTvToastText = view.findViewById(R.id.tvToastMessage);
        mTvToastText.setText(message);
        setView(view);
    }
}