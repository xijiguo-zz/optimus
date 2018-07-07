package com.optimus.basketbrawl

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PaintFlagsDrawFilter
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button;
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

        editbutton.setOnClickListener {
            val popupfragment = DialogFragment()
            val dialogBuilder = AlertDialog.Builder(activity)
            dialogBuilder.setView(inflater.inflate(R.layout.edit_profile,container,false))
            dialogBuilder.setPositiveButton("Confirm"){dialog, which ->
                // Do something when user press the positive button
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
        return view
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
