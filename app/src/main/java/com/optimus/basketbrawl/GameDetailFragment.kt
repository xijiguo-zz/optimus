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
import java.text.SimpleDateFormat


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GameDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GameDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GameDetailFragment : Fragment() {

    private var DATE_PREFIX = arrayOf("M", "Tu", "W", "Th", "F", "Sa", "Su")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_game_detail, container, false)
        val game: GameModel = arguments!!.getParcelable("myGame")

        val gameTitle: TextView = view.findViewById(R.id.game_detail_title)
        gameTitle.text = game.gameName

        val gameStartTime: TextView = view.findViewById(R.id.game_detail_starttime)
        val formatter = SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm")
        gameStartTime.text = "Start time: " + formatter.format(game.startTime!!.time)

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
        fun newInstance() = GameDetailFragment()
    }
}