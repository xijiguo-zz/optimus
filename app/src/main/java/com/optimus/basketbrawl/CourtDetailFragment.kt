package com.optimus.basketbrawl

import android.media.Image
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.annotation.Nullable
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import org.w3c.dom.Text


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

        return view
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