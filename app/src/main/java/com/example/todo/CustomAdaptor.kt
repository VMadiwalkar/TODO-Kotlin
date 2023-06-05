package com.example.todo

import android.content.Context
import android.content.DialogInterface
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.CustomAdapter.ViewHolder

class CustomAdapter(val context: Context, private val mList:ArrayList<TodoList>) : RecyclerView.Adapter<ViewHolder>() {
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val todoText: TextView = itemView.findViewById(R.id.todoItem)
        val todoDelete: ImageButton = itemView.findViewById(R.id.todoDelete)




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return  mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
//        holder.todoText.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.todoText.text = ItemsViewModel.item
        holder.todoDelete.setOnClickListener{
            showAlertDialogButtonClicked(position)


        }
    }

    fun showAlertDialogButtonClicked(position: Int) {
        // Create an alert builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure you want to delete this item")

        builder.setPositiveButton("Yes") { dialog: DialogInterface?, which: Int ->
            // send data from the AlertDialog to the Activity
            mList.removeAt(position)
            notifyDataSetChanged()


        }
        builder.setNegativeButton("Cancel") { dialog: DialogInterface?, which: Int ->
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

}


