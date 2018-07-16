package com.optimus.basketbrawl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.v4.app.FragmentManager
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.Toolbar


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GameContainerFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GameContainerFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GameContainerFragment : Fragment(), GamesFragment.OnGameSelectionListener {

    private val BACK_STACK_ROOT_TAG = "root_fragment"
    private var mToolbar: Toolbar? = null

    override fun onGameSelection(game: GameModel) {
        val fragmentManager = childFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        val gameDetailFragment = GameDetailFragment.newInstance()
        val bundle = Bundle()
        bundle.putParcelable("myGame", game)
        gameDetailFragment.arguments = bundle

//        val someOtherNestFrag = fragmentManager.findFragmentByTag("Some fragment tag") as SomeOtherNestFrag
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.body_fragment, gameDetailFragment)
                .addToBackStack(BACK_STACK_ROOT_TAG)
                .commit()
        //Tag of your fragment which you should use when you add

        mToolbar!!.title = game.gameName
        mToolbar!!.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.abc_ic_ab_back_material, activity!!.theme)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_container, container, false)

        val fragmentManager = childFragmentManager
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        val gamesFragment = GamesFragment.newInstance()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.body_fragment, gamesFragment)
                .addToBackStack(null)
                .commit()

        mToolbar = view.findViewById(R.id.container_toolbar)

        mToolbar!!.title = "My Games"
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
        fun newInstance() = GameContainerFragment()
    }
}