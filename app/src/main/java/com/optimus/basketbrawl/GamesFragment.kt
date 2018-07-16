package com.optimus.basketbrawl

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.support.annotation.Nullable
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [GamesFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [GamesFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class GamesFragment : Fragment() {

    private var listitems: ArrayList<GameModel> = ArrayList()
    private var mRecyclerView: RecyclerView? = null
    private var games = arrayOf("Game 1", "Game 2", "Game 3")
    private var dates = arrayListOf<Calendar>(Calendar.getInstance(),
            Calendar.getInstance(), Calendar.getInstance())

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeList()
        onAttachToParentFragment(this.parentFragment!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_courts, container, false)
        mRecyclerView = view.findViewById(R.id.card_view)
        mRecyclerView!!.setHasFixedSize(true)
        val mLayoutManager = LinearLayoutManager(activity)
        mLayoutManager.orientation = LinearLayoutManager.VERTICAL
        if ((listitems.size > 0) and (mRecyclerView != null)) {
            mRecyclerView!!.adapter = MyAdapter(listitems)
        }
        mRecyclerView!!.layoutManager = mLayoutManager

        return view
    }

    inner class MyAdapter(private val list: ArrayList<GameModel>) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycle_items_game, parent, false)
            view.setOnClickListener({
                v ->
                run {
                    val itemPosition = mRecyclerView!!.getChildLayoutPosition(v)
                    val item = listitems[itemPosition]
                    mOnGameSelectionListener!!.onGameSelection(item)
                }
            })
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.gameNameTextView.text = list[position].gameName
            val dateFormat = SimpleDateFormat.getDateInstance()
            holder.gameStartTextView.text = dateFormat.format(list[position].startTime!!.time)
        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var gameNameTextView: TextView = v.findViewById<View>(R.id.card_game_name) as TextView
        var gameStartTextView: TextView = v.findViewById(R.id.card_game_start) as TextView

    }

    private fun initializeList() {
        if (listitems.isEmpty()) {
            dates[1].add(Calendar.DATE, 2)
            dates[2].add(Calendar.DATE, 4)
        }

        listitems.clear()

        for (i in 0..2) {
            val item = GameModel()
            item.gameName = games[i]
            item.startTime = dates[i]
            listitems.add(item)
        }
    }

    private var mOnGameSelectionListener: OnGameSelectionListener? = null;

    interface OnGameSelectionListener {
        fun onGameSelection(game: GameModel)
    }

    fun onAttachToParentFragment(fragment: Fragment) {
        try {
            mOnGameSelectionListener = fragment as OnGameSelectionListener

        } catch (e: ClassCastException) {
            throw ClassCastException(
                    fragment.toString() + " must implement OnCourtSelectionListener")
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment CourtDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() = GamesFragment()
    }
}