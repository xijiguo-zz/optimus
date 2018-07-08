package com.optimus.basketbrawl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.Toolbar
import android.widget.Toast




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CourtContainerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CourtContainerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CourtContainerFragment : Fragment(), CourtsFragment.OnCourtSelectionListener {
    private val BACK_STACK_ROOT_TAG = "root_fragment"
    private var mToolbar: Toolbar? = null

    override fun onCourtSelection(court: CourtModel) {
        val fragmentManager = childFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        val courtDetailFragment = CourtDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable("myCourt", court)
        courtDetailFragment.arguments = bundle

//        val someOtherNestFrag = fragmentManager.findFragmentByTag("Some fragment tag") as SomeOtherNestFrag
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.court_fragment, courtDetailFragment)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit()
        //Tag of your fragment which you should use when you add

        mToolbar!!.title = court.courtName
        mToolbar!!.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.abc_ic_ab_back_material, activity!!.theme)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_court_container, container, false)

        val fragmentManager = childFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        val courtsFragment = CourtsFragment.newInstance()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.court_fragment, courtsFragment)
                .addToBackStack(null)
                .commit()

        mToolbar = view.findViewById(R.id.court_toolbar)

        mToolbar!!.title = "Court List"
        mToolbar!!.setNavigationOnClickListener({
            val fragmentManager = childFragmentManager
            fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            mToolbar!!.navigationIcon = null
        })

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
        fun newInstance() = CourtContainerFragment()
    }
}