package com.dac.lab3_ex

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.graphics.Typeface

/**
 * Custom adapter to display a list of users with name, hometown, and avatar.
 */
class UsersAdapter(context: Context, users: ArrayList<User>) :
    ArrayAdapter<User>(context, 0, users) {

    /**
     * ViewHolder pattern to optimize ListView performance.
     */
    private class ViewHolder(
        val name: TextView,
        val home: TextView,
        val avatar: ImageView
    )

    /**
     * Provides a view for an adapter view (ListView).
     * @param position The position of the item within the adapter's data set.
     * @param convertView The old view to reuse, if possible.
     * @param parent The parent that this view will eventually be attached to.
     * @return A View corresponding to the data at the specified position.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var currentView = convertView
        val viewHolder: ViewHolder

        if (currentView == null) {
            // Inflate new view if no reusable view available
            currentView = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
            viewHolder = ViewHolder(
                currentView.findViewById(R.id.tvName),
                currentView.findViewById(R.id.tvHome),
                currentView.findViewById(R.id.ivProfile)
            )
            currentView.tag = viewHolder
        } else {
            // Reuse existing viewHolder
            viewHolder = currentView.tag as ViewHolder
        }

        // Get the current user item
        val user = getItem(position)
        viewHolder.name.text = user?.name

        // Create "from + hometown" with hometown in bold
        val text = "from ${user?.hometown}"
        val spannable = SpannableString(text)
        user?.hometown?.let {
            val start = text.indexOf(it)
            val end = start + it.length
            spannable.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        viewHolder.home.text = spannable

        // Set default avatar image
        viewHolder.avatar.setImageResource(R.drawable.ic_user)

        return currentView!!
    }
}
