package com.optimus.basketbrawl

import android.app.AlertDialog
import android.app.Application
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button;
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.edit_profile.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ProfileFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Profile.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //this.textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val editbutton : Button = view.findViewById(R.id.profile_edit_button)
        updateInfo(view)

        editbutton.setOnClickListener {
            popupHandler(view,inflater,container)
        }
        return view
    }

    fun updateInfo(view:View) {
        val nameView: TextView = view.findViewById(R.id.profile_name_TextView) as TextView
        if ((getActivity()!!.application as userClass).getUserName() != null) nameView.text = (getActivity()!!.application as userClass).getUserName()
        val ageView: TextView = view.findViewById(R.id.profile_age_TextView) as TextView
        if ((getActivity()!!.application as userClass).getUserAge() != null) ageView.text = (getActivity()!!.application as userClass).getUserAge().toString()
        val genderView: TextView = view.findViewById(R.id.profile_gender_TextView) as TextView
        if ((getActivity()!!.application as userClass).getUserGender() != null) genderView.text = (getActivity()!!.application as userClass).getUserGender()
        val weightView: TextView = view.findViewById(R.id.profile_weight_TextView) as TextView
        if ((getActivity()!!.application as userClass).getUserWeight() != null) weightView.text = (getActivity()!!.application as userClass).getUserWeight().toString()
        val heightView: TextView = view.findViewById(R.id.profile_height_TextView) as TextView
        if ((getActivity()!!.application as userClass).getUserHeight() != null) heightView.text = (getActivity()!!.application as userClass).getUserHeight().toString()
    }

    fun popupHandler(view:View,inflater: LayoutInflater,container: ViewGroup?){
        val dialogBuilder = AlertDialog.Builder(activity)
        val popupView = inflater.inflate(R.layout.edit_profile,container,false)

        dialogBuilder.setView(popupView)
        dialogBuilder.setPositiveButton("Confirm"){dialog, which ->
            // Do something when user press the positive button
            var userinstance = getActivity()!!.application as userClass
            val namePopup: EditText = popupView.findViewById(R.id.namePopup)
            if (!TextUtils.isEmpty(namePopup.text)) { userinstance.setUserName(namePopup.text.toString()) }
            val agePopup: EditText = popupView.findViewById(R.id.agePopup)
            if (!TextUtils.isEmpty(agePopup.text)) { userinstance.setUserAge(agePopup.text.toString().toInt()) }
            val genderPopup: EditText = popupView.findViewById(R.id.genderPopup)
            if (!TextUtils.isEmpty(genderPopup.text)) { userinstance.setUserGender(genderPopup.text.toString()) }
            val weightPopup: EditText = popupView.findViewById(R.id.weightPopup)
            if (!TextUtils.isEmpty(weightPopup.text)) { userinstance.setUserWeight(weightPopup.text.toString().toInt()) }
            val heightPopup: EditText = popupView.findViewById(R.id.heightPopup)
            if (!TextUtils.isEmpty(heightPopup.text)) { userinstance.setUserHeight(heightPopup.text.toString().toInt()) }
            //nameView.text = (getActivity()!!.application as userClass).getUserName()
            updateInfo(view)
            Toast.makeText(getActivity()!!.getApplicationContext(),"New information saved", Toast.LENGTH_SHORT).show()
        }
        dialogBuilder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(getActivity()!!.getApplicationContext(),"Edit cancelled",Toast.LENGTH_SHORT).show()
        }
        val dialog: AlertDialog = dialogBuilder.create()
        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CourtFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
