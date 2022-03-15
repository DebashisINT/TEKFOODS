package com.tekfoods.features.lead

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.borax12.materialdaterangepicker.date.DatePickerDialog
import com.tekfoods.CustomStatic
import com.tekfoods.R
import com.tekfoods.app.AppDatabase
import com.tekfoods.app.NetworkConstant
import com.tekfoods.app.domain.ActivityDropDownEntity
import com.tekfoods.app.domain.TypeEntity
import com.tekfoods.app.utils.AppUtils
import com.tekfoods.app.utils.Toaster
import com.tekfoods.base.BaseResponse
import com.tekfoods.base.presentation.BaseActivity
import com.tekfoods.base.presentation.BaseFragment
import com.tekfoods.features.activities.api.ActivityRepoProvider
import com.tekfoods.features.activities.model.ActivityDropdownListResponseModel
import com.tekfoods.features.activities.model.TypeListResponseModel
import com.tekfoods.features.activities.presentation.ActivityListDialog
import com.tekfoods.features.activities.presentation.TypeListDialog
import com.tekfoods.features.dashboard.presentation.DashboardActivity
import com.tekfoods.features.lead.adapter.ViewActivityAdapter
import com.tekfoods.features.lead.api.GetLeadRegProvider
import com.tekfoods.features.lead.dialog.EnqListDialog
import com.tekfoods.features.lead.model.*
import com.tekfoods.features.photoReg.model.UserListResponseModel
import com.tekfoods.features.report.model.TargetVsAchvDataModel
import com.tekfoods.features.report.presentation.TargetVsAchvDetailsFragment
import com.tekfoods.widgets.AppCustomTextView
import com.downloader.Progress
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pnikosis.materialishprogress.ProgressWheel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.dialog_reason.*
import kotlinx.android.synthetic.main.fragment_add_activity.*
import kotlinx.android.synthetic.main.fragment_add_pjp.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ViewLeadFrag : BaseFragment(), View.OnClickListener{

    private lateinit var mContext: Context

    private lateinit var rv_list:RecyclerView
    private lateinit var progress_wheel:ProgressWheel
    private lateinit var tv_no_data:AppCustomTextView
    private lateinit var shopName:AppCustomTextView
    private lateinit var shopImage:ImageView
    private lateinit var shopAddre:AppCustomTextView
    private lateinit var shopContact:AppCustomTextView
    private lateinit var fab_view_frag:FloatingActionButton

    private var viewActivityAdapter: ViewActivityAdapter? = null

    var addActivityReq = AddActivityReq()
    var editActivityReq = EditActivityReq()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    companion object {


        var crm_id:String?=null
        var shopNames:String?=null
        var shopAddresss:String?=null
        var shopPhone:String?=null

        fun getInstance(objects: Any): ViewLeadFrag {
            val fragment = ViewLeadFrag()
            var obj = objects as CustomerLeadList

            crm_id=obj.crm_id
            shopNames = obj.customer_name
            shopAddresss = obj.customer_addr
            shopPhone = obj.mobile_no

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.view_lead_frag, container, false)
        initView(view)

        return view
    }


    private fun initView(view: View){
        rv_list=view.findViewById(R.id.rv_view_lead_frag_stls)
        tv_no_data=view.findViewById(R.id.tv_no_data)
        fab_view_frag=view.findViewById(R.id.fab_view_frag)
        progress_wheel=view.findViewById(R.id.progress_wheel)
        shopName = view.findViewById(R.id.row_cutomer_lead_list_ShopNameTV)
        shopImage = view.findViewById(R.id.row_cutomer_lead_list_ivImage)
        shopAddre = view.findViewById(R.id.row_cutomer_lead_list_Shopaddress_TV)
        shopContact = view.findViewById(R.id.row_cutomer_lead_list_Shopcontact_no)

        progress_wheel.stopSpinning()

        fab_view_frag.setOnClickListener(this)
        shopName.text = shopNames
        val drawable = TextDrawable.builder()
                .buildRoundRect(shopNames!!.trim().toUpperCase().take(1), ColorGenerator.MATERIAL.randomColor, 120)
        shopImage.setImageDrawable(drawable)
        shopAddre.text = shopAddresss
        shopContact.text = shopPhone
        getActivityList()
    }

    private fun getActivityList(){
        try {
            if (!AppUtils.isOnline(mContext)) {
                (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
                return
            }
            BaseActivity.isApiInitiated = true
            progress_wheel.spin()
            val repository = GetLeadRegProvider.provideList()
            BaseActivity.compositeDisposable.add(
                    repository.getActivityList(crm_id!!)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({ result ->
                                val addShopResult = result as ActivityViewRes
                                BaseActivity.isApiInitiated = false
                                progress_wheel.stopSpinning()
                                if (addShopResult.status == NetworkConstant.SUCCESS) {
                                    if(addShopResult.activity_dtls_list.size>0){
                                        tv_no_data.visibility=View.GONE
                                        setAdapter(addShopResult.activity_dtls_list!!)
                                    }
                                } else  {
                                    tv_no_data.visibility=View.VISIBLE
                                }
                            }, { error ->
                                tv_no_data.visibility=View.VISIBLE
                                progress_wheel.stopSpinning()
                                BaseActivity.isApiInitiated = false
                                (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                                if (error != null) {
                                }
                            })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            tv_no_data.visibility=View.VISIBLE
            BaseActivity.isApiInitiated = false
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
            progress_wheel.stopSpinning()
        }
    }

    private fun setAdapter(list: ArrayList<activity_dtls_list>){
        viewActivityAdapter=ViewActivityAdapter(mContext,list,object: ViewActivityAdapter.OnViewActiClickListener{
            override fun onEditClick(obj: activity_dtls_list,adapterPos:Int) {
                var isLast:Boolean=false
                if((list.size-1) == adapterPos){
                    isLast=true
                }else{
                    isLast=false
                }
                onEdit(obj, isLast)
            }
        })
        rv_list.adapter=viewActivityAdapter
    }


    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.fab_view_frag->{
                openDialog()
                /*val list = AppDatabase.getDBInstance()?.activityDropdownDao()?.getAll()
                if (list == null || list.isEmpty())
                    getActivityDropdownList()
                else
                    openActivityList(list)*/
            }
        }
    }

   lateinit var simpleDialog :Dialog
    lateinit var tv_date_dialog:TextView
    lateinit var activityType:AppCustomTextView
    private fun openDialog() {
        simpleDialog = Dialog(mContext)
        simpleDialog.setCancelable(false)
        simpleDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        simpleDialog.setContentView(R.layout.dialog_add_activity_customer)

        val activityStatus = simpleDialog.findViewById(R.id.dialog_add_activity_spinnerType) as AppCustomTextView
        activityType = simpleDialog.findViewById(R.id.dialog_add_activity_activity_Type) as AppCustomTextView
        tv_date_dialog = simpleDialog.findViewById(R.id.tv_dialog_add_acti_date) as TextView
        val tv_time = simpleDialog.findViewById(R.id.tv_dialog_add_acti_time) as TextView
        val tv_message_ok = simpleDialog.findViewById(R.id.tv_message_ok) as AppCustomTextView

        val iv_cross = simpleDialog.findViewById(R.id.iv_dialog_add_acti_cus_cross) as ImageView

        val et_dtls = simpleDialog.findViewById(R.id.et_dialog_add_acti_cus_dtls) as EditText
        val et_remarks = simpleDialog.findViewById(R.id.et_dialog_add_acti_cus_remarks) as EditText

        val header = simpleDialog.findViewById(R.id.tv_dialog_add_acti_header) as AppCustomTextView
        header.text = "Add Activity"

        activityStatus.setOnClickListener {
            activityStatus.error=null
            var List:ArrayList<String> = ArrayList()
            List.add("Confirmed ")
            List.add("In Process")
            List.add("Not Interested")

            EnqListDialog.newInstance(List,"Select Activity Status"){
                activityStatus.text=it
            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        }

        activityType.setOnClickListener{
            val list = AppDatabase.getDBInstance()?.activityDropdownDao()?.getAll()
            if (list == null || list.isEmpty())
                getActivityDropdownList()
            else
                openActivityList(list)
        }

        tv_date_dialog.setOnClickListener{
            tv_date_dialog.error=null
            val datePicker = android.app.DatePickerDialog(mContext, R.style.DatePickerTheme, date, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        tv_time.setOnClickListener{
            var timeMilis = 0L
            val cal = Calendar.getInstance(Locale.ENGLISH)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                timeMilis = cal.timeInMillis
                tv_time.text = SimpleDateFormat("HH:mm:ss").format(cal.time)
            }
            val timePicker = TimePickerDialog(mContext, R.style.DatePickerTheme, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false)
            timePicker.show()
        }


        tv_message_ok.setOnClickListener({ view ->
            var validating = true

            if(tv_date_dialog.text.toString().equals("")){
                tv_date_dialog.requestFocus()
                tv_date_dialog.setError("Select date")
                validating=false
            }else if(et_dtls.text.toString().equals("")){
                et_dtls.requestFocus()
                et_dtls.setError("Enter details")
                validating=false
            }else if(activityStatus.text.toString().equals("")){
                activityStatus.requestFocus()
                activityStatus.setError("Select Status")
                validating=false
            }
            if(validating){
                val simpleDialogYesNo = Dialog(mContext)
                simpleDialogYesNo.setCancelable(false)
                simpleDialogYesNo.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                simpleDialogYesNo.setContentView(R.layout.dialog_yes_no)
                val dialogHeader = simpleDialogYesNo.findViewById(R.id.dialog_cancel_order_header_TV) as AppCustomTextView
                val dialog_yes_no_headerTV = simpleDialogYesNo.findViewById(R.id.dialog_yes_no_headerTV) as AppCustomTextView
                dialog_yes_no_headerTV.text = AppUtils.hiFirstNameText()+"!"
                dialogHeader.text = "Want to submit activity ? "
                val dialogYes = simpleDialogYesNo.findViewById(R.id.tv_dialog_yes_no_yes) as AppCustomTextView
                val dialogNo = simpleDialogYesNo.findViewById(R.id.tv_dialog_yes_no_no) as AppCustomTextView
                dialogYes.setOnClickListener({ view ->
                    addActivityReq.activity_date=tv_date_dialog.text.toString()
                    addActivityReq.activity_time=tv_time.text.toString()
                    addActivityReq.activity_details=et_dtls.text.toString()
                    addActivityReq.other_remarks=et_remarks.text.toString()
                    addActivityReq.activity_type_name=activityType.text.toString()
                    addActivityReq.activity_status=activityStatus.text.toString()
                    simpleDialogYesNo.cancel()
                    simpleDialog.cancel()
                    submitActivityAPI()

                })
                dialogNo.setOnClickListener({ view ->
                    simpleDialogYesNo.cancel()
                })
                simpleDialogYesNo.show()

            }

        })

        iv_cross.setOnClickListener {
            simpleDialog.cancel()
        }

        simpleDialog.show()

    }

    private val myCalendar: Calendar by lazy {
        Calendar.getInstance(Locale.ENGLISH)
    }

    val date = android.app.DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        // TODO Auto-generated method stub
        myCalendar.set(Calendar.YEAR, year)
        myCalendar.set(Calendar.MONTH, monthOfYear)
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        //tv_date_dialog.text=  AppUtils.getFormatedDateNew(AppUtils.getBillingDateFromCorrectDate(AppUtils.getFormattedDateForApi(myCalendar.time)),"dd-mm-yyyy","yyyy-mm-dd")
        tv_date_dialog.text=  AppUtils.getBillingDateFromCorrectDate(AppUtils.getFormattedDateForApi(myCalendar.time))
    }

    private fun getActivityDropdownList() {

        if (!AppUtils.isOnline(mContext)) {
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
            return
        }

        val repository = ActivityRepoProvider.activityRepoProvider()
        progress_wheel.spin()
        BaseActivity.compositeDisposable.add(
                repository.activityDropdownList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            val response = result as ActivityDropdownListResponseModel
                            if (response.status == NetworkConstant.SUCCESS) {
                                val list = response.activity_list

                                if (list != null && list.isNotEmpty()) {

                                    doAsync {

                                        list.forEach {
                                            val activity = ActivityDropDownEntity()
                                            AppDatabase.getDBInstance()?.activityDropdownDao()?.insertAll(activity.apply {
                                                activity_id = it.id
                                                activity_name = it.name
                                            })
                                        }

                                        uiThread {
                                            progress_wheel.stopSpinning()
                                            openActivityList(AppDatabase.getDBInstance()?.activityDropdownDao()?.getAll()!!)
                                        }
                                    }
                                } else {
                                    progress_wheel.stopSpinning()
                                    (mContext as DashboardActivity).showSnackMessage(response.message!!)
                                }
                            } else {
                                progress_wheel.stopSpinning()
                                (mContext as DashboardActivity).showSnackMessage(response.message!!)
                            }

                        }, { error ->
                            error.printStackTrace()
                            progress_wheel.stopSpinning()
                            (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
                        })
        )
    }

    private fun openActivityList(list: List<ActivityDropDownEntity>) {
        ActivityListDialog.newInstance(list as ArrayList<ActivityDropDownEntity>) {
            activityType.text=it.activity_name
        }.show((mContext as DashboardActivity).supportFragmentManager, "")
    }


    private fun submitActivityAPI(){
        addActivityReq.crm_id= crm_id

        try {
            if (!AppUtils.isOnline(mContext)) {
                (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
                return
            }

            var formatDate = AppUtils.getFormatedDateNew(addActivityReq.activity_date,"dd-mm-yyyy","yyyy-mm-dd")
            addActivityReq.activity_date=formatDate

            BaseActivity.isApiInitiated = true
            progress_wheel.spin()
            val repository = GetLeadRegProvider.provideList()
            BaseActivity.compositeDisposable.add(
                    repository.submitActivity(addActivityReq)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({ result ->
                                val addShopResult = result as BaseResponse
                                BaseActivity.isApiInitiated = false
                                progress_wheel.stopSpinning()
                                if (addShopResult.status == NetworkConstant.SUCCESS) {
                                    showMsg("Activity submitted successfully.")
                                } else  {
                                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                                }
                            }, { error ->
                                progress_wheel.stopSpinning()
                                BaseActivity.isApiInitiated = false
                                (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                                if (error != null) {
                                }
                            })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            BaseActivity.isApiInitiated = false
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
            progress_wheel.stopSpinning()
        }
    }

    private fun onEdit(obj: activity_dtls_list,isLast:Boolean){
        simpleDialog = Dialog(mContext)
        simpleDialog.setCancelable(false)
        simpleDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        simpleDialog.setContentView(R.layout.dialog_add_activity_customer)

        val activityStatus = simpleDialog.findViewById(R.id.dialog_add_activity_spinnerType) as AppCustomTextView
        activityType = simpleDialog.findViewById(R.id.dialog_add_activity_activity_Type) as AppCustomTextView
        tv_date_dialog = simpleDialog.findViewById(R.id.tv_dialog_add_acti_date) as TextView
        val tv_time = simpleDialog.findViewById(R.id.tv_dialog_add_acti_time) as TextView
        val tv_message_ok = simpleDialog.findViewById(R.id.tv_message_ok) as AppCustomTextView

        val iv_cross = simpleDialog.findViewById(R.id.iv_dialog_add_acti_cus_cross) as ImageView

        val et_dtls = simpleDialog.findViewById(R.id.et_dialog_add_acti_cus_dtls) as EditText
        val et_remarks = simpleDialog.findViewById(R.id.et_dialog_add_acti_cus_remarks) as EditText

        val header = simpleDialog.findViewById(R.id.tv_dialog_add_acti_header) as AppCustomTextView
        header.text = "Update Activity"


        //data set
        tv_date_dialog.text=obj.activity_date
        if(isLast){
            tv_date_dialog.isEnabled=true
        }else{
            tv_date_dialog.isEnabled=false
        }
        tv_time.text=obj.activity_time
        //tv_time.isEnabled=false
        et_dtls.setText(obj.activity_details)
        et_remarks.setText(obj.other_remarks)
        activityStatus.text=obj.activity_status
        activityType.text=obj.activity_type_name



        activityStatus.setOnClickListener {
            activityStatus.error=null
            var List:ArrayList<String> = ArrayList()
            List.add("Confirmed ")
            List.add("In Process")
            List.add("Not Interested")

            EnqListDialog.newInstance(List,"Select Activity Status"){
                activityStatus.text=it
            }.show((mContext as DashboardActivity).supportFragmentManager, "")
        }

        activityType.setOnClickListener{
            val list = AppDatabase.getDBInstance()?.activityDropdownDao()?.getAll()
            if (list == null || list.isEmpty())
                getActivityDropdownList()
            else
                openActivityList(list)
        }

        tv_date_dialog.setOnClickListener{
            tv_date_dialog.error=null
            val datePicker = android.app.DatePickerDialog(mContext, R.style.DatePickerTheme, date, myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }
        tv_time.setOnClickListener{
            var timeMilis = 0L
            val cal = Calendar.getInstance(Locale.ENGLISH)
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                timeMilis = cal.timeInMillis
                tv_time.text = SimpleDateFormat("HH:mm:ss").format(cal.time)
            }
            val timePicker = TimePickerDialog(mContext, R.style.DatePickerTheme, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), false)
            timePicker.show()
        }


        tv_message_ok.setOnClickListener({ view ->
            var validating = true

            if(tv_date_dialog.text.toString().equals("")){
                tv_date_dialog.requestFocus()
                tv_date_dialog.setError("Select date")
                validating=false
            }else if(et_dtls.text.toString().equals("")){
                et_dtls.requestFocus()
                et_dtls.setError("Enter details")
                validating=false
            }
            else if(activityStatus.text.toString().equals("")){
                activityStatus.requestFocus()
                activityStatus.setError("Select Status")
                validating=false
            }
            if(validating){


                val simpleDialogYesNo = Dialog(mContext)
                simpleDialogYesNo.setCancelable(false)
                simpleDialogYesNo.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                simpleDialogYesNo.setContentView(R.layout.dialog_yes_no)
                val dialogHeader = simpleDialogYesNo.findViewById(R.id.dialog_cancel_order_header_TV) as AppCustomTextView
                val dialog_yes_no_headerTV = simpleDialogYesNo.findViewById(R.id.dialog_yes_no_headerTV) as AppCustomTextView
                dialog_yes_no_headerTV.text = AppUtils.hiFirstNameText()+"!"
                dialogHeader.text = "Want to update activity ? "
                val dialogYes = simpleDialogYesNo.findViewById(R.id.tv_dialog_yes_no_yes) as AppCustomTextView
                val dialogNo = simpleDialogYesNo.findViewById(R.id.tv_dialog_yes_no_no) as AppCustomTextView
                dialogYes.setOnClickListener({ view ->

                    editActivityReq.activity_id=obj.activity_id
                    editActivityReq.crm_id= crm_id
                    editActivityReq.activity_date=tv_date_dialog.text.toString()
                    editActivityReq.activity_time=tv_time.text.toString()
                    editActivityReq.activity_details=et_dtls.text.toString()
                    editActivityReq.other_remarks=et_remarks.text.toString()

                    editActivityReq.activity_type_name=activityType.text.toString()
                    editActivityReq.activity_status=activityStatus.text.toString()

                    simpleDialogYesNo.cancel()
                    simpleDialog.cancel()


                    submitEditActivityAPI()

                })
                dialogNo.setOnClickListener({ view ->
                    simpleDialogYesNo.cancel()
                })
                simpleDialogYesNo.show()

            }

        })

        iv_cross.setOnClickListener {
            simpleDialog.cancel()
        }

        simpleDialog.show()
    }

    private fun submitEditActivityAPI(){
        try {
            if (!AppUtils.isOnline(mContext)) {
                (mContext as DashboardActivity).showSnackMessage(getString(R.string.no_internet))
                return
            }

            var formatDate = AppUtils.getFormatedDateNew(editActivityReq.activity_date,"dd-mm-yyyy","yyyy-mm-dd")
            editActivityReq.activity_date=formatDate

            BaseActivity.isApiInitiated = true
            progress_wheel.spin()
            val repository = GetLeadRegProvider.provideList()
            BaseActivity.compositeDisposable.add(
                    repository.editActivity(editActivityReq)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({ result ->
                                val addShopResult = result as BaseResponse
                                BaseActivity.isApiInitiated = false
                                progress_wheel.stopSpinning()
                                if (addShopResult.status == NetworkConstant.SUCCESS) {
                                    showMsg("Activity updated successfully.")
                                } else  {
                                    (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                                }
                            }, { error ->
                                progress_wheel.stopSpinning()
                                BaseActivity.isApiInitiated = false
                                (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
                                if (error != null) {
                                }
                            })
            )
        } catch (ex: Exception) {
            ex.printStackTrace()
            BaseActivity.isApiInitiated = false
            (mContext as DashboardActivity).showSnackMessage(getString(R.string.something_went_wrong))
            progress_wheel.stopSpinning()
        }
    }


    private fun showMsg(body:String){

        val simpleDialog = Dialog(mContext)
        simpleDialog.setCancelable(false)
        simpleDialog.getWindow()!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        simpleDialog.setContentView(R.layout.dialog_message)
        val dialogHeader = simpleDialog.findViewById(R.id.dialog_message_header_TV) as AppCustomTextView
        val dialog_yes_no_headerTV = simpleDialog.findViewById(R.id.dialog_message_headerTV) as AppCustomTextView
        dialog_yes_no_headerTV.text = AppUtils.hiFirstNameText()+"!"
        dialogHeader.text = body
        val dialogYes = simpleDialog.findViewById(R.id.tv_message_ok) as AppCustomTextView
        dialogYes.setOnClickListener({ view ->
            simpleDialog.cancel()
            CustomStatic.IsViewLeadAddUpdate=true
            (mContext as DashboardActivity).onBackPressed()
        })
        simpleDialog.show()


    }

}