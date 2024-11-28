package com.saveetha.techexactlytask.Adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.switchmaterial.SwitchMaterial
import com.saveetha.techexactlytask.Model.AppList
import com.saveetha.techexactlytask.R
import com.squareup.picasso.Picasso

class ApplicationsRecyclerAdapter(var itemList: ArrayList<AppList>, context: Context): RecyclerView.Adapter<ApplicationsRecyclerAdapter.Viewholder>() {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.application_recycler_adapter, parent, false)
        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = itemList[position]
        Picasso.get().load(item.appIcon).into(holder.appIcon)
        holder.appName.text = item.appName

        // Save the switch state immediately when it's toggled
        holder.appStatusSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save the updated state in SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putBoolean("switch_state_$position", isChecked)
            editor.apply()  // Apply the changes immediately
        }

        // Restore the switch state from SharedPreferences
        val savedState = sharedPreferences.getBoolean("switch_state_$position", false)
        holder.appStatusSwitch.isChecked = savedState

    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appIcon: ImageView = itemView.findViewById(R.id.appIcon)
        val appName: TextView = itemView.findViewById(R.id.appName)
        val appStatusSwitch: SwitchMaterial = itemView.findViewById(R.id.appStatusSwitch)
    }

    fun updateData(newList: ArrayList<AppList>) {
        itemList = newList
        notifyDataSetChanged()
    }

}