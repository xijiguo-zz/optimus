package com.optimus.basketbrawl

import android.app.AlertDialog
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CourtDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CourtDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CourtDetailFragment : Fragment() {

    private var DATE_PREFIX = arrayOf("M", "Tu", "W", "Th", "F", "Sa", "Su")
    private var mGames = ArrayList<GameModel>()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_court_detail, container, false)
        val court: CourtModel = arguments!!.getParcelable("myCourt")

        val courtImage: ImageView = view.findViewById(R.id.court_detail_image)
        courtImage.setImageResource(court.imageResourceId)

        val courtTitle: TextView = view.findViewById(R.id.court_detail_title)
        courtTitle.text = court.courtName

        val courtAddress: TextView = view.findViewById(R.id.court_detail_address)
        courtAddress.text = court.courtAddress

        val courtHours: TextView = view.findViewById(R.id.court_detail_hours)
        val courtHoursText = StringBuilder()
        for (i in 0..6) {
            courtHoursText.append(DATE_PREFIX[i] + ": " + court.courtHours[i] + "\n")
        }
        courtHours.text = courtHoursText

        val addGameButton: Button = view.findViewById(R.id.add_game_button)
        addGameButton.setOnClickListener {
            popupHandler(view, inflater, container)
        }
        return view
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun popupHandler(view:View, inflater: LayoutInflater, container: ViewGroup?){
        val dialogBuilder = AlertDialog.Builder(activity)
        dialogBuilder.setTitle("Add Game")
        val popupView = inflater.inflate(R.layout.popup_add_game, container,false)

        dialogBuilder.setView(popupView)
        dialogBuilder.setPositiveButton("Confirm"){dialog, which ->
            val game = GameModel()
            game.gameName = popupView.findViewById<EditText>(R.id.popup_game_name).text.toString()

            val datePicker = popupView.findViewById<DatePicker>(R.id.popup_game_datepicker)
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1
            val year = datePicker.year

            val timePicker = popupView.findViewById<TimePicker>(R.id.popup_game_timepicker)
            val hour = timePicker.hour
            val min = timePicker.minute

            game.startTime = Calendar.getInstance()
            game.startTime.set(year, month, day, hour, min)

            mGames.add(game)

            Toast.makeText(activity!!.applicationContext,"Added game", Toast.LENGTH_SHORT).show()
        }
        dialogBuilder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(activity!!.applicationContext,"Game cancelled", Toast.LENGTH_SHORT).show()
        }
        val dialog: AlertDialog = dialogBuilder.create()
        dialog.show()
        dialog.window.setLayout(750, 1200)

        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CourtsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = CourtDetailFragment()
    }
}