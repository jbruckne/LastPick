package joebruckner.lastpick.ui.movie

import android.graphics.Color
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import joebruckner.lastpick.R
import joebruckner.lastpick.data.Genre

class GenreAdapter(initSelected: BooleanArray): RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {
    val genres = Genre.getAll()
    var selected = initSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder? {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.toggle_genre, parent, false)
        return GenreViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.name.text = genres[position].name
        setToggle(holder.view, position)
        holder.view.setOnClickListener {
            selected[position] = !selected[position]
            if (!selected[position] && selected[0]) {
                selected[0] = false
                notifyItemChanged(0)
            }
            if (position == 0) {
                for (i in 1..selected.size-1) selected[i] = selected[0]
                notifyDataSetChanged()
            }
            setToggle(it, position)
        }
    }

    fun setToggle(view: View, position: Int) {
        val name = view.findViewById(R.id.name) as TextView
        val card = view.findViewById(R.id.card) as CardView
        val black = Color.parseColor("#212121")
        if (!selected[position]) {
            name.setTextColor(Color.WHITE)
            card.setCardBackgroundColor(black)
        } else {
            name.setTextColor(black)
            card.setCardBackgroundColor(Color.WHITE)
        }

    }

    override fun getItemCount(): Int = genres.size

    class GenreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView

        init {
            name = view.findViewById(R.id.name) as TextView
        }
    }
}