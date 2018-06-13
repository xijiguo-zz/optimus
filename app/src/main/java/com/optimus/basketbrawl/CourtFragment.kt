package com.optimus.basketbrawl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CourtFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CourtFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CourtFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_court, container, false)
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
        fun newInstance() = CourtFragment()
    }
}
