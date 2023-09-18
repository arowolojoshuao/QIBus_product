package app.iqonicthemes.qibus.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.NewPackageModel;
import app.iqonicthemes.qibus.ui.activity.BusListActivity;
import app.iqonicthemes.qibus.ui.activity.PackageActivity;
import app.iqonicthemes.qibus.ui.adapter.PackageAdapter;
import app.iqonicthemes.qibus.utils.Constants;
import app.iqonicthemes.qibus.utils.RecyclerCoverFlow;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;

public class FragmentOffers extends Fragment implements View.OnClickListener, PackageAdapter.onClickListener {
    /*variable declaration*/
    public static final String mTitle = "Packages";
    private PackageAdapter mAdapter,mPopularAdapter;
    private RecyclerCoverFlow mRvNewPackage, mRvPopularPackage, mRvTrendingPackage;
    private ArrayList<NewPackageModel> mPackageList, mPopularList;
    private TextView mTvNewPackageAll, mTvPopularPackageAll, mTvTrendingPackageAll;

    /* create view */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, null);

        bindView(view);
        setListener();
        getData();
        return view;
    }

    /* set listener */
    private void setListener() {

        mTvNewPackageAll.setOnClickListener(this);
        mTvPopularPackageAll.setOnClickListener(this);
        mTvTrendingPackageAll.setOnClickListener(this);


    }

    /* bind view */
    private void bindView(View view) {
        mRvNewPackage = view.findViewById(R.id.rvNewPackage);
        mRvPopularPackage = view.findViewById(R.id.rvPopularPackage);
        mRvTrendingPackage = view.findViewById(R.id.rvTrendingPackage);
        mTvNewPackageAll = view.findViewById(R.id.tvViewAllNewPackage);
        mTvPopularPackageAll = view.findViewById(R.id.tvViewallPopularPackage);
        mTvTrendingPackageAll = view.findViewById(R.id.tvViewAllTrendingPackage);
    }

    /*get data */
    private void getData() {
        mPackageList = new ArrayList<>();
        mPopularList = new ArrayList<>();
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_goa), getString(R.string.lbl_package_duration1), getString(R.string.lbl_package_rate1), getString(R.string.lbl_package_price1), getString(R.string.lbl_package_booking1), R.drawable.ic_goa));
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_amritsar), getString(R.string.lbl_package_duration2), getString(R.string.lbl_package_rate3), getString(R.string.lbl_package_price2), getString(R.string.lbl_package_bookin2), R.drawable.ic_amritsar));
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_andaman), getString(R.string.lbl_package_duration3), getString(R.string.lbl_package_rate5), getString(R.string.lbl_package_price3), getString(R.string.lbl_package_booking3), R.drawable.ic_andamans));
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_delhi), getString(R.string.lbl_package_duration1), getString(R.string.lbl_package_rate4), getString(R.string.lbl_package_price4), getString(R.string.lbl_package_booking1), R.drawable.ic_delhi));
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_shimla), getString(R.string.lbl_package_duration1), getString(R.string.lbl_package_rate4), getString(R.string.lbl_package_price5), getString(R.string.lbl_package_bookin2), R.drawable.ic_temp));
        mPackageList.add(new NewPackageModel(getString(R.string.lbl_udaipur),  getString(R.string.lbl_package_duration2), getString(R.string.lbl_package_rate5), getString(R.string.lbl_package_price1), getString(R.string.lbl_package_booking1), R.drawable.ic_udaipur));


        mPopularList.add(new NewPackageModel(getString(R.string.lbl_delhi), getString(R.string.lbl_package_duration3),  getString(R.string.lbl_package_rate5), getString(R.string.lbl_package_price5), getString(R.string.lbl_package_booking3), R.drawable.ic_delhi));
        mPopularList.add(new NewPackageModel(getString(R.string.lbl_shimla), getString(R.string.lbl_package_duration1),  getString(R.string.lbl_package_rate5),  getString(R.string.lbl_package_price2), getString(R.string.lbl_package_booking1), R.drawable.ic_temp));
        mPopularList.add(new NewPackageModel(getString(R.string.lbl_udaipur), getString(R.string.lbl_package_duration1),  getString(R.string.lbl_package_rate5), getString(R.string.lbl_package_price1), getString(R.string.lbl_package_booking1), R.drawable.ic_udaipur));
        mPopularList.add(new NewPackageModel(getString(R.string.lbl_andaman), getString(R.string.lbl_package_duration1),  getString(R.string.lbl_package_rate5), getString(R.string.lbl_package_price1), getString(R.string.lbl_package_booking1), R.drawable.ic_andamans));



        mPopularList.addAll(mPopularList);

        mAdapter = new PackageAdapter(getActivity(), mPackageList);
        mPopularAdapter = new PackageAdapter(getActivity(), mPopularList);
        mRvNewPackage.setAdapter(mAdapter);
        mAdapter.setOnClickListener(this);
        mPopularAdapter.setOnClickListener(this);

        mRvPopularPackage.setAdapter(mPopularAdapter);
        mRvTrendingPackage.setAdapter(mAdapter);


    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        if (v == mTvNewPackageAll) {
            Intent intent = new Intent(getActivity(), PackageActivity.class);
            intent.putExtra(Constants.intentdata.PACKAGE, mPackageList);
            startActivity(intent);
        } else if (v == mTvPopularPackageAll) {
            Intent intent = new Intent(getActivity(), PackageActivity.class);
            intent.putExtra(Constants.intentdata.PACKAGE, mPackageList);
            startActivity(intent);
        } else if (v == mTvTrendingPackageAll) {
            Intent intent = new Intent(getActivity(), PackageActivity.class);
            intent.putExtra(Constants.intentdata.PACKAGE, mPackageList);
            startActivity(intent);
        }
    }

    /* onClick listener for item */
    @Override
    public void onClick(NewPackageModel aPackageModel) {

        Intent intent = new Intent(getActivity(), BusListActivity.class);
        intent.putExtra(Constants.intentdata.PACKAGE_NAME, aPackageModel.getDestination());
        startActivity(intent);


    }
}
