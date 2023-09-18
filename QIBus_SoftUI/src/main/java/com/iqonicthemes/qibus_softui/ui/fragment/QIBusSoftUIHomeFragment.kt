package com.iqonicthemes.qibus_softui.ui.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Objects
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iqonicthemes.qibus_softui.QIBusSoftUIBaseActivity
import com.iqonicthemes.qibus_softui.R
import com.iqonicthemes.qibus_softui.model.QIBusSoftUIBookingModel
import com.iqonicthemes.qibus_softui.model.QIBusSoftUINewOfferModel
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUIBusSoftUIListActivity
import com.iqonicthemes.qibus_softui.ui.activity.QIBusSoftUISoftUIOffersActivity
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUINewOfferAdapter
import com.iqonicthemes.qibus_softui.ui.adapter.QIBusSoftUIRecentSearchAdapter
import com.iqonicthemes.qibus_softui.utils.QIBusSoftUIConstants

class QIBusSoftUIHomeFragment : Fragment(), View.OnClickListener,
    QIBusSoftUIRecentSearchAdapter.onClickListener {
    private var mEdFromCity: AutoCompleteTextView? = null
    private var mEdToCity: AutoCompleteTextView? = null
    private var mValue = 0
    private var mView: View? = null
    private var mSoftUINewOfferList: ArrayList<QIBusSoftUINewOfferModel>? = null
    private var mRecentSearchList: ArrayList<QIBusSoftUIBookingModel>? = null
    private var mRvNewOffer: RecyclerView? = null
    private var mRvRecentSearch: RecyclerView? = null
    private var mIvSwap: ImageView? = null
    private var mIvDescrease: ImageView? = null
    private var mIvIncrease: ImageView? = null
    private var mSearch: ImageView? = null
    private var mTvAC: TextView? = null
    private var mTvNonAc: TextView? = null
    private var mTvSleeper: TextView? = null
    private var mTvSeater: TextView? = null
    private var mTvCount: TextView? = null
    private var mTvViewNewOffers: TextView? = null
    private var mDepartDateCalendar: Calendar? = null
    private var mSoftUIRecentSearchAdapter: QIBusSoftUIRecentSearchAdapter? = null
    private var mEdDepartDate: TextView? = null
    private val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        mDepartDateCalendar!!.set(Calendar.YEAR, year)
        mDepartDateCalendar!!.set(Calendar.MONTH, monthOfYear)
        mDepartDateCalendar!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        updateLabel()
    }
    private var mIsAcSelected: Boolean = false
    private var mIsNonAcSelected: Boolean = false
    private var mIsSeaterSelected: Boolean = false
    private var mIsSleeperSelected: Boolean = false
    private var city: Array<String>? = null

    /* create view */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.qibus_softui_fragment_home, null)

        initView(view)
        setListener()
        getData()

        setOfferAdapter()

        if (mDepartDateCalendar == null) {
            mDepartDateCalendar = Calendar.getInstance()
        }
        updateLabel()
        return view
    }

    /* update label */
    private fun updateLabel() {
        mEdDepartDate!!.text =
            QIBusSoftUIConstants.DateFormat.DAY_MONTH_YEAR_FORMATTER.format(mDepartDateCalendar!!.time)
    }

    /* set adapter */
    private fun setOfferAdapter() {
        mRvNewOffer!!.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        mRvNewOffer!!.adapter = QIBusSoftUINewOfferAdapter(activity!!, mSoftUINewOfferList!!)

        mSoftUIRecentSearchAdapter = QIBusSoftUIRecentSearchAdapter(activity!!, mRecentSearchList!!)
        mRvRecentSearch!!.layoutManager =
            LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        mRvRecentSearch!!.adapter = mSoftUIRecentSearchAdapter
        mSoftUIRecentSearchAdapter!!.setOnClickListener(this)


    }

    /* set listener */
    private fun setListener() {
        mSearch!!.setOnClickListener(this)
        mTvViewNewOffers!!.setOnClickListener(this)
        mTvAC!!.setOnClickListener(this)
        mTvNonAc!!.setOnClickListener(this)
        mTvSleeper!!.setOnClickListener(this)
        mTvSeater!!.setOnClickListener(this)
        mIvSwap!!.setOnClickListener(this)
        mIvDescrease!!.setOnClickListener(this)
        mIvIncrease!!.setOnClickListener(this)
        mEdDepartDate!!.setOnClickListener(this)


        mEdFromCity!!.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (mEdFromCity!!.length() > 0) {
                    mView!!.setBackgroundColor(resources.getColor(R.color.QIBus_softui_colorPrimary))
                    mView!!.alpha = 0.2f
                } else {
                    mView!!.setBackgroundColor(resources.getColor(R.color.QIBus_softui_view_color))
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {}
        })
        mEdToCity!!.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int, event: KeyEvent): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //  if (validate()) {
                    mFrom = mEdFromCity!!.text.toString()
                    mTo = mEdToCity!!.text.toString()
                    val intent = Intent(activity, QIBusSoftUIBusSoftUIListActivity::class.java)
                    intent.putExtra(
                        QIBusSoftUIConstants.intentdata.TRIP_KEY,
                        mEdFromCity!!.text.toString() + " To " + mEdToCity!!.text.toString()
                    )
                    startActivity(intent)
                    //  }
                    return true
                }
                return false
            }

        })

    }

    /* init view */
    private fun initView(view: View) {
        mRvNewOffer = view.findViewById(R.id.rvNewOffers)
        mTvViewNewOffers = view.findViewById(R.id.tvViewallNewOffer)
        mIvSwap = view.findViewById(R.id.ivSwap)
        mSearch = view.findViewById(R.id.btnSearch)
        mTvAC = view.findViewById(R.id.tvAc)
        mTvNonAc = view.findViewById(R.id.tvNonAc)
        mTvSleeper = view.findViewById(R.id.tvSleeper)
        mTvSeater = view.findViewById(R.id.tvSeater)
        mEdDepartDate = view.findViewById(R.id.edOneWay)
        mEdFromCity = view.findViewById(R.id.edFromCity)
        mEdToCity = view.findViewById(R.id.edToCity)
        mIvDescrease = view.findViewById(R.id.ivDescrease)
        mIvIncrease = view.findViewById(R.id.ivIncrease)
        mTvCount = view.findViewById(R.id.tvCount)
        mView = view.findViewById(R.id.view2)
        mRvRecentSearch = view.findViewById(R.id.rvRecentSearch)
        city = arrayOf(
            getString(R.string.QIBus_softui_lbl_mumbai),
            getString(R.string.QIBus_softui_lbl_surat),
            getString(R.string.QIBus_softui_lbl_delhi),
            getString(R.string.QIBus_softui_lbl_pune)
        )
        val adapter = ArrayAdapter(activity!!, R.layout.qibus_softui_spinner_items, city!!)
        mEdFromCity!!.threshold = 1
        mEdFromCity!!.setAdapter(adapter)
        mEdToCity!!.threshold = 1
        mEdToCity!!.setAdapter(adapter)

        mIvDescrease!!.visibility = View.INVISIBLE


    }

    /* et cricketdata */
    private fun getData() {

        mRecentSearchList = ArrayList()
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_DelhiToMubai),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToPune),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_AhmedabadToMumbai),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_JaipurToNewDelhi),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToSurat),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_DelhiToMubai),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )
        mRecentSearchList!!.add(
            QIBusSoftUIBookingModel(
                getString(R.string.QIBus_softui_lbl_MumbaiToSurat),
                getString(R.string.QIBus_softui_lbl_date)
            )
        )


        mSoftUINewOfferList = ArrayList()
        mSoftUINewOfferList!!.add(QIBusSoftUINewOfferModel(getString(R.string.QIBus_softui_lbl_offer1)))
        mSoftUINewOfferList!!.add(QIBusSoftUINewOfferModel(getString(R.string.QIBus_softui_lbl_offer2)))
        mSoftUINewOfferList!!.add(QIBusSoftUINewOfferModel(getString(R.string.QIBus_softui_lbl_offer1)))
        mSoftUINewOfferList!!.add(QIBusSoftUINewOfferModel(getString(R.string.QIBus_softui_lbl_offer2)))
        mSoftUINewOfferList!!.add(QIBusSoftUINewOfferModel(getString(R.string.QIBus_softui_lbl_offer2)))

    }

    /* onClick listener */
    override fun onClick(v: View) {
        when (v.id) {

            R.id.edOneWay -> {

                val datePickerDialogs = DatePickerDialog(
                    activity!!, date, mDepartDateCalendar!!
                        .get(Calendar.YEAR), mDepartDateCalendar!!.get(Calendar.MONTH),
                    mDepartDateCalendar!!.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialogs.datePicker.minDate = Date().time
                datePickerDialogs.show()
            }
            R.id.ivSwap -> {
                var mFromCity = mEdFromCity!!.text.toString()
                var mToCity = mEdToCity!!.text.toString()
                val startRotateAnimation =
                    AnimationUtils.loadAnimation(context, R.anim.qibus_softui_anim_rotate)
                mIvSwap!!.startAnimation(startRotateAnimation)
                mFromCity = mFromCity + mToCity
                mToCity = mFromCity.substring(0, mFromCity.length - mToCity.length)
                mFromCity = mFromCity.substring(mToCity.length)


                mEdFromCity!!.setText(mFromCity)
                mEdToCity!!.setText(mToCity)
                mEdFromCity!!.setSelection(mFromCity.length)
                mEdToCity!!.setSelection(mToCity.length)
            }
            R.id.ivDescrease -> {
                mValue = mValue - 1
                mTvCount!!.text = mValue.toString()

                if (mValue <= 1) {

                    (Objects.requireNonNull(activity) as QIBusSoftUIBaseActivity).invisibleView(
                        mIvDescrease!!
                    )

                } else {

                    (activity as QIBusSoftUIBaseActivity).showView(mIvDescrease!!)
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.setTextColor(resources.getColor(R.color.QIBus_softui_colorPrimary))
                }
            }
            R.id.ivIncrease -> {
                mValue = mValue + 1

                if (mValue < 1) {
                    mValue = 1

                } else {

                    if (mValue == 1)
                        (activity as QIBusSoftUIBaseActivity).invisibleView(mIvDescrease!!)
                    else
                        (Objects.requireNonNull(activity) as QIBusSoftUIBaseActivity).showView(
                            mIvDescrease!!
                        )
                    mTvCount!!.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    mTvCount!!.text = mValue.toString()
                    mTvCount!!.setTextColor(resources.getColor(R.color.QIBus_softui_colorPrimary))

                }
            }
            R.id.tvAc -> if (!mIsAcSelected) {
                enable(v as TextView)
                mIsAcSelected = true
            } else {
                disable(v as TextView)
                mIsAcSelected = false

            }
            R.id.tvNonAc -> if (!mIsNonAcSelected) {
                enable(v as TextView)
                mIsNonAcSelected = true
            } else {
                disable(v as TextView)
                mIsNonAcSelected = false

            }
            R.id.tvSleeper -> if (!mIsSleeperSelected) {
                enable(v as TextView)
                mIsSleeperSelected = true
            } else {
                disable(v as TextView)
                mIsSleeperSelected = false

            }
            R.id.tvSeater -> if (!mIsSeaterSelected) {
                enable(v as TextView)
                mIsSeaterSelected = true
            } else {
                disable(v as TextView)
                mIsSeaterSelected = false

            }
            R.id.btnSearch -> {
                //    if (validate()) {

                mFrom = mEdFromCity!!.text.toString()
                mTo = mEdToCity!!.text.toString()
                val intent = Intent(activity, QIBusSoftUIBusSoftUIListActivity::class.java)
                intent.putExtra(QIBusSoftUIConstants.intentdata.TRIP_KEY, "$mFrom To $mTo")
                startActivity(intent)
            }
            R.id.tvViewallNewOffer -> {
                val i = Intent(activity, QIBusSoftUISoftUIOffersActivity::class.java)
                i.putExtra(QIBusSoftUIConstants.intentdata.OFFER, mSoftUINewOfferList)
                startActivity(i)
            }
        }//    }


    }

    /* validation */
    private fun validate(): Boolean {
        var flag = true
        if (TextUtils.isEmpty(mEdFromCity!!.text)) {
            flag = false
            (Objects.requireNonNull(activity) as QIBusSoftUIBaseActivity).showToast(getString(R.string.QIBus_softui_msg_from))
        } else if (TextUtils.isEmpty(mEdToCity!!.text)) {
            flag = false
            (Objects.requireNonNull(activity) as QIBusSoftUIBaseActivity).showToast(getString(R.string.QIBus_softui_msg_to))
        }
        return flag
    }

    /* disable textview */
    private fun disable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.QIBus_softui_textChild))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.QIBus_softui_textChild
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* enable textview */
    private fun enable(textView: TextView) {
        textView.setTextColor(resources.getColor(R.color.design_default_color_primary))
        for (drawable in textView.compoundDrawables) {
            if (drawable != null) {
                drawable.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        textView.context,
                        R.color.design_default_color_primary
                    ), PorterDuff.Mode.SRC_IN
                )
            }
        }
    }

    /* onClick listener */
    override fun onClick(softUIBookingModel: QIBusSoftUIBookingModel) {

        val intent = Intent(activity, QIBusSoftUIBusSoftUIListActivity::class.java)
        intent.putExtra(QIBusSoftUIConstants.intentdata.SEARCH_BUS, softUIBookingModel.destination)
        startActivity(intent)
    }

    /* change destination */
    fun ChangeDestination(result: String) {

        if (mEdFromCity!!.length() == 0) {
            mEdFromCity!!.setText(result)
        } else {
            mEdToCity!!.setText(result)
        }

    }

    companion object {
        /*variable declaration*/
        val mTitle = "Home"
        var mFrom: String = ""
        var mTo: String = ""
    }
}
