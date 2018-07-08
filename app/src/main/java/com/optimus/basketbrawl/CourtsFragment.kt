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
import android.widget.ImageView
import android.widget.Toast




/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CourtsFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CourtsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CourtsFragment : Fragment() {

    private var listitems: ArrayList<CourtModel> = ArrayList()
    private var mRecyclerView: RecyclerView? = null
    private var courts = arrayOf("CIF 1", "CIF 2", "REV")
    private var images = intArrayOf(R.drawable.court_small, R.drawable.court_small, R.drawable.court_small)
    private var mOnCourtSelectionListener: OnCourtSelectionListener? = null;

    interface OnCourtSelectionListener {
        fun onCourtSelection(court: CourtModel)
    }

    fun onAttachToParentFragment(fragment: Fragment) {
        try {
            mOnCourtSelectionListener = fragment as OnCourtSelectionListener

        } catch (e: ClassCastException) {
            throw ClassCastException(
                    fragment.toString() + " must implement OnCourtSelectionListener")
        }

    }

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

    inner class MyAdapter(private val list: ArrayList<CourtModel>) : RecyclerView.Adapter<MyViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            // create a new view
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycle_items_court, parent, false)
            view.setOnClickListener({
                v ->
                run {
                    val itemPosition = mRecyclerView!!.getChildLayoutPosition(v)
                    val item = listitems[itemPosition]
                    mOnCourtSelectionListener!!.onCourtSelection(item);
                    Toast.makeText(context, item.courtName, Toast.LENGTH_LONG).show()
                }
            })
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            holder.titleTextView.text = list[position].courtName
            holder.coverImageView.setImageResource(list[position].imageResourceId)
            holder.coverImageView.tag = list[position].imageResourceId

        }

        override fun getItemCount(): Int {
            return list.size
        }
    }

    inner class MyViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var titleTextView: TextView = v.findViewById<View>(R.id.court_name_text_view) as TextView
        var coverImageView: ImageView = v.findViewById(R.id.court_cover_image) as ImageView

    }

    private fun initializeList() {
        listitems.clear()

        for (i in 0..2) {
            val item = CourtModel()
            item.courtName = courts[i]
            item.imageResourceId = images[i]
            listitems.add(item)
        }
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
        fun newInstance() = CourtsFragment()
    }
}