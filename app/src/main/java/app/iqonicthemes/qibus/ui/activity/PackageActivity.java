package app.iqonicthemes.qibus.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.NewPackageModel;
import app.iqonicthemes.qibus.ui.adapter.ViewPackageAdapter;
import app.iqonicthemes.qibus.utils.Constants;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PackageActivity extends BaseActivity implements View.OnClickListener, ViewPackageAdapter.onClickListener {
    /*variable declaration*/
    private RecyclerView mRvOffer;
    private ArrayList<NewPackageModel> mPackageList;
    private ImageView mIvBack;
    private ViewPackageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);
        initLayouts();
        initializeListeners();
    }

    /* initialize listener */
    private void initializeListeners() {
        mIvBack.setOnClickListener(this);
        mPackageList = new ArrayList<>();
        mPackageList = (ArrayList<NewPackageModel>)getIntent().getSerializableExtra(Constants.intentdata.PACKAGE);

        mRvOffer.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));

        mAdapter = new ViewPackageAdapter(this, mPackageList);
        mRvOffer.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        RunLayoutAnimation(mRvOffer);
    }

    /* init layout */
    private void initLayouts() {
        mIvBack = findViewById(R.id.ivBack);
        mRvOffer = findViewById(R.id.rvPackage);
    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mIvBack) onBackPressed();
    }

    /* onClick listener for item */
    @Override
    public void onClick(NewPackageModel aPackageModel) {
        Intent intent = new Intent(this, BusListActivity.class);
        intent.putExtra(Constants.intentdata.PACKAGE_NAME, aPackageModel.getDestination());
        startActivity(intent);

    }
}
