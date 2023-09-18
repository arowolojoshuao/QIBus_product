package app.iqonicthemes.qibus.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.iqonicthemes.qibus.BaseActivity;
import app.iqonicthemes.qibus.R;
import app.iqonicthemes.qibus.model.BookingModel;
import app.iqonicthemes.qibus.model.NewOfferModel;
import app.iqonicthemes.qibus.ui.activity.BusListActivity;
import app.iqonicthemes.qibus.ui.activity.OffersActivity;
import app.iqonicthemes.qibus.ui.adapter.NewOfferAdapter;
import app.iqonicthemes.qibus.ui.adapter.RecentSearchAdapter;
import app.iqonicthemes.qibus.utils.Constants;

public class HomeFragmentNewest extends Fragment implements View.OnClickListener, RecentSearchAdapter.onClickListener {
    /*variable declaration*/
    public static final String mTitle = "Home";
    private AutoCompleteTextView mEdFromCity, mEdToCity;
    public static String mFrom, mTo;
    private int mValue = 0;
    private View mView;
    private ArrayList<NewOfferModel> mNewOfferList;
    private ArrayList<BookingModel> mRecentSearchList;
    private RecyclerView mRvNewOffer, mRvRecentSearch;
    private ImageView mIvSwap, mIvDescrease, mIvIncrease, mSearch;
    private TextView mTvAC, mTvNonAc, mTvSleeper, mTvSeater, mTvCount, mTvViewNewOffers;
    private Calendar mDepartDateCalendar;
    private RecentSearchAdapter mRecentSearchAdapter;
    private TextView mEdDepartDate;
    private final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mDepartDateCalendar.set(Calendar.YEAR, year);
            mDepartDateCalendar.set(Calendar.MONTH, monthOfYear);
            mDepartDateCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateLabel();
        }

    };
    private boolean mIsAcSelected, mIsNonAcSelected, mIsSeaterSelected, mIsSleeperSelected;
    private String[] city;

    /* create view */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        initView(view);
        setListener();
        getData();

        setOfferAdapter();

        if (mDepartDateCalendar == null) {
            mDepartDateCalendar = Calendar.getInstance();
        }
        updateLabel();
        return view;
    }

    /* update label */
    private void updateLabel() {
        mEdDepartDate.setText(Constants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar.getTime()));


    }

    /* set adapter */
    private void setOfferAdapter() {
        mRvNewOffer.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        mRvNewOffer.setAdapter(new NewOfferAdapter(getActivity(), mNewOfferList));

        mRecentSearchAdapter = new RecentSearchAdapter(getActivity(), mRecentSearchList);
        mRvRecentSearch.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
        mRvRecentSearch.setAdapter(mRecentSearchAdapter);
        mRecentSearchAdapter.setOnClickListener(this);


    }

    /* set listener */
    private void setListener() {
        mSearch.setOnClickListener(this);
        mTvViewNewOffers.setOnClickListener(this);
        mTvAC.setOnClickListener(this);
        mTvNonAc.setOnClickListener(this);
        mTvSleeper.setOnClickListener(this);
        mTvSeater.setOnClickListener(this);
        mIvSwap.setOnClickListener(this);
        mIvDescrease.setOnClickListener(this);
        mIvIncrease.setOnClickListener(this);
        mEdDepartDate.setOnClickListener(this);


        mEdFromCity.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (mEdFromCity.length() > 0) {
                    mView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    mView.setAlpha(0.2f);
                } else {
                    mView.setBackgroundColor(getResources().getColor(R.color.view_color));
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });
        mEdToCity.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //  if (validate()) {
                    mFrom = mEdFromCity.getText().toString();
                    mTo = mEdToCity.getText().toString();
                    Intent intent = new Intent(getActivity(), BusListActivity.class);
                    intent.putExtra(Constants.intentdata.TRIP_KEY, mEdFromCity.getText().toString() + " To " + mEdToCity.getText().toString());
                    startActivity(intent);
                    //  }
                    return true;
                }
                return false;
            }

        });

    }

    /* init view */
    private void initView(View view) {
        mRvNewOffer = view.findViewById(R.id.rvNewOffers);
        mTvViewNewOffers = view.findViewById(R.id.tvViewallNewOffer);
        mIvSwap = view.findViewById(R.id.ivSwap);
        mSearch = view.findViewById(R.id.btnSearch);
        mTvAC = view.findViewById(R.id.tvAc);
        mTvNonAc = view.findViewById(R.id.tvNonAc);
        mTvSleeper = view.findViewById(R.id.tvSleeper);
        mTvSeater = view.findViewById(R.id.tvSeater);
        mEdDepartDate = view.findViewById(R.id.edOneWay);
        mEdFromCity = view.findViewById(R.id.edFromCity);
        mEdToCity = view.findViewById(R.id.edToCity);
        mIvDescrease = view.findViewById(R.id.ivDescrease);
        mIvIncrease = view.findViewById(R.id.ivIncrease);
        mTvCount = view.findViewById(R.id.tvCount);
        mView = view.findViewById(R.id.view2);
        mRvRecentSearch = view.findViewById(R.id.rvRecentSearch);
        city = new String[]{getString(R.string.lbl_mumbai), getString(R.string.lbl_surat), getString(R.string.lbl_delhi), getString(R.string.lbl_pune)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_items, city);
        mEdFromCity.setThreshold(1);
        mEdFromCity.setAdapter(adapter);
        mEdToCity.setThreshold(1);
        mEdToCity.setAdapter(adapter);

        mIvDescrease.setVisibility(View.INVISIBLE);


    }

    /* et data */
    private void getData() {

        mRecentSearchList = new ArrayList<>();
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_DelhiToMubai), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_MumbaiToPune), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_AhmedabadToMumbai), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_JaipurToNewDelhi), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_MumbaiToSurat), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_DelhiToMubai), getString(R.string.lbl_date)));
        mRecentSearchList.add(new BookingModel(getString(R.string.lbl_MumbaiToSurat), getString(R.string.lbl_date)));


        mNewOfferList = new ArrayList<>();
        mNewOfferList.add(new NewOfferModel(getString(R.string.lbl_offer1)));
        mNewOfferList.add(new NewOfferModel(getString(R.string.lbl_offer2)));
        mNewOfferList.add(new NewOfferModel(getString(R.string.lbl_offer1)));
        mNewOfferList.add(new NewOfferModel(getString(R.string.lbl_offer2)));
        mNewOfferList.add(new NewOfferModel(getString(R.string.lbl_offer2)));

    }

    /* onClick listener */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.edOneWay:

                DatePickerDialog datePickerDialogs = new DatePickerDialog(getActivity(), date, mDepartDateCalendar
                        .get(Calendar.YEAR), mDepartDateCalendar.get(Calendar.MONTH),
                        mDepartDateCalendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialogs.getDatePicker().setMinDate(new Date().getTime());
                datePickerDialogs.show();
                break;
            case R.id.ivSwap:
                String mFromCity = mEdFromCity.getText().toString();
                String mToCity = mEdToCity.getText().toString();
                Animation startRotateAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.anim_rotate);
                mIvSwap.startAnimation(startRotateAnimation);
                mFromCity = mFromCity + mToCity;
                mToCity = mFromCity.substring(0, mFromCity.length() - mToCity.length());
                mFromCity = mFromCity.substring(mToCity.length());


                mEdFromCity.setText(mFromCity);
                mEdToCity.setText(mToCity);
                mEdFromCity.setSelection(mFromCity.length());
                mEdToCity.setSelection(mToCity.length());
                break;
            case R.id.ivDescrease:
                mValue = mValue - 1;
                mTvCount.setText(String.valueOf(mValue));

                if (mValue <= 1) {

                    ((BaseActivity)Objects.requireNonNull(getActivity())).invisibleView(mIvDescrease);

                } else {

                    ((BaseActivity)getActivity()).showView(mIvDescrease);
                    mTvCount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvCount.setTextColor(getResources().getColor(R.color.colorPrimary));
                }
                break;
            case R.id.ivIncrease:
                mValue = mValue + 1;

                if (mValue < 1) {
                    mValue = 1;

                } else {

                    if (mValue == 1) ((BaseActivity)getActivity()).invisibleView(mIvDescrease);
                    else
                        ((BaseActivity)Objects.requireNonNull(getActivity())).showView(mIvDescrease);
                    mTvCount.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    mTvCount.setText(String.valueOf(mValue));
                    mTvCount.setTextColor(getResources().getColor(R.color.colorPrimary));

                }
                break;
            case R.id.tvAc:
                if (!mIsAcSelected) {
                    enable((TextView)v);
                    mIsAcSelected = true;
                } else {
                    disable((TextView)v);
                    mIsAcSelected = false;

                }
                break;
            case R.id.tvNonAc:
                if (!mIsNonAcSelected) {
                    enable((TextView)v);
                    mIsNonAcSelected = true;
                } else {
                    disable((TextView)v);
                    mIsNonAcSelected = false;

                }
                break;
            case R.id.tvSleeper:
                if (!mIsSleeperSelected) {
                    enable((TextView)v);
                    mIsSleeperSelected = true;
                } else {
                    disable((TextView)v);
                    mIsSleeperSelected = false;

                }
                break;
            case R.id.tvSeater:
                if (!mIsSeaterSelected) {
                    enable((TextView)v);
                    mIsSeaterSelected = true;
                } else {
                    disable((TextView)v);
                    mIsSeaterSelected = false;

                }
                break;
            case R.id.btnSearch:
                //    if (validate()) {

                mFrom = mEdFromCity.getText().toString();
                mTo = mEdToCity.getText().toString();
                Intent intent = new Intent(getActivity(), BusListActivity.class);
                intent.putExtra(Constants.intentdata.TRIP_KEY, mFrom + " To " + mTo);
                startActivity(intent);
                //    }
                break;
            case R.id.tvViewallNewOffer:
                Intent i = new Intent(getActivity(), OffersActivity.class);
                i.putExtra(Constants.intentdata.OFFER, mNewOfferList);
                startActivity(i);
                break;

        }


    }

    /* validation */
    private boolean validate() {
        boolean flag = true;
        if (TextUtils.isEmpty(mEdFromCity.getText())) {
            flag = false;
            ((BaseActivity)Objects.requireNonNull(getActivity())).showToast(getString(R.string.msg_from));
        } else if (TextUtils.isEmpty(mEdToCity.getText())) {
            flag = false;
            ((BaseActivity)Objects.requireNonNull(getActivity())).showToast(getString(R.string.msg_to));
        }
        return flag;
    }

    /* disable textview */
    private void disable(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.textchild));
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), R.color.textchild), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    /* enable textview */
    private void enable(TextView textView) {
        textView.setTextColor(getResources().getColor(R.color.startcolor));
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null) {
                drawable.setColorFilter(new PorterDuffColorFilter(ContextCompat.getColor(textView.getContext(), R.color.startcolor), PorterDuff.Mode.SRC_IN));
            }
        }
    }

    /* onClick listener */
    @Override
    public void onClick(BookingModel BookingModel) {

        Intent intent = new Intent(getActivity(), BusListActivity.class);
        intent.putExtra(Constants.intentdata.SEARCH_BUS, BookingModel.getDestination());
        startActivity(intent);
    }
    /* change destination */
    public void ChangeDestination(String result)
    {

            if (mEdFromCity.length() == 0) {
                mEdFromCity.setText(result);
            } else {
               mEdToCity.setText(result);
            }

    }
}
