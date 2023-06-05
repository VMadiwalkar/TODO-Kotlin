package com.example.todo

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    //      private lateinit var todoList: ArrayList<TodoList>
    private lateinit var todoList: ArrayList<TodoList>
    lateinit var rvList: RecyclerView
//    lateinit var alertDialog: AlertDialog.Builder
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        todoList = arrayListOf()

        // Initial Values
        todoList.add(TodoList("work"))
        todoList.add(TodoList("play"))


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvList = findViewById(R.id.rvList)

        rvList.layoutManager = LinearLayoutManager(this)

        adapter = CustomAdapter(this@MainActivity,todoList)

        // Setting the Adapter with the recyclerview
        rvList.adapter = adapter


        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener() {
            showAlertDialogButtonClicked()


        }


//            Log.i("list",todoList.contains("work").toString())
    }



    fun showAlertDialogButtonClicked() {
        var isDuplicateAvailable = false
        // Create an alert builder
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Your TODO Item")

        // set the custom layout
        val customLayout: View = layoutInflater.inflate(R.layout.acivity_new_item, null)
        builder.setView(customLayout)

        // add a button
        builder.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
            // send data from the AlertDialog to the Activity
            val editText = customLayout.findViewById<EditText>(R.id.editText)
            val enteredValue = editText.text.toString()

            if (editText.text.isNotEmpty()){
                for(i in todoList){
                    if(enteredValue  == i.item){
                        isDuplicateAvailable = true
                        Toast.makeText(this,"Item Already Exists",Toast.LENGTH_SHORT).show()
                        break
                    }
                }
                if(!isDuplicateAvailable){
                    todoList.add(TodoList(editText.text.toString()))
                    adapter.notifyDataSetChanged()
                }

            }else
                Toast.makeText(this,"Enter Valid Item",Toast.LENGTH_SHORT).show()

        }
        builder.setNegativeButton("Cancel") {dialog: DialogInterface?, which: Int ->
        }
        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

}
