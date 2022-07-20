package com.android.menusandpickers

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        Toast.makeText(this,"${hourOfDay}:${minute}",Toast.LENGTH_SHORT).show()

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        Toast.makeText(
            this,
            "${dayOfMonth}/${month}/${year}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private lateinit var toolbar: Toolbar
    private lateinit var textViewContextMenu: TextView
    private var actionMode: ActionMode? = null
    private lateinit var textViewContextActionBar: TextView
    private lateinit var imageButtonPopUp: ImageButton
    private lateinit var buttonDialog: Button
    private lateinit var buttonDatePickerDialog: Button
    private lateinit var buttonTimePickerDialog: Button

    private val actionModeCallBack = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            when (item?.itemId) {
                R.id.ic_delete -> {
                    showToast("delete")
                    mode?.finish()
                    return true
                }
                R.id.ic_share -> {
                    showToast("share")
                    mode?.finish()
                    return true
                }
            }
            return false


        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.contextactionbar_menu, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        textViewContextMenu = findViewById(R.id.textView_contextmenu)
        registerForContextMenu(textViewContextMenu)

        textViewContextActionBar = findViewById(R.id.textView_contextactionbar)
        textViewContextActionBar.setOnLongClickListener { view ->
            if (actionMode != null) {
                false
            } else {
                actionMode = startSupportActionMode(actionModeCallBack)
                view.isSelected = true
                true
            }


        }
        imageButtonPopUp = findViewById(R.id.imageButton_popUp)
        imageButtonPopUp.setOnClickListener {
            val popUpMenu = PopupMenu(this, imageButtonPopUp)
            val inflater: MenuInflater = popUpMenu.menuInflater
            inflater.inflate(R.menu.popup_menu, popUpMenu.menu)
            popUpMenu.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.item_setting -> {
                        showToast("Setting")
                        return@setOnMenuItemClickListener true
                    }
                    R.id.item_save -> {
                        showToast("save")
                        return@setOnMenuItemClickListener true
                    }
                    else -> false
                }
            }

            popUpMenu.show()
        }

        buttonDialog = findViewById(R.id.button_dialog)
        buttonDialog.setOnClickListener {
            SimpleDialog().show(supportFragmentManager, null)
        }

        buttonDatePickerDialog = findViewById(R.id.button_datepicker)
        buttonDatePickerDialog.setOnClickListener {
            SimpleDatePickerDialog().show(supportFragmentManager,null)
        }

        buttonTimePickerDialog = findViewById(R.id.button_timepicker)
        buttonTimePickerDialog.setOnClickListener {
            SimpleTimePickerDialog().show(supportFragmentManager,null)
        }

    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.setHeaderTitle("Choose ur option")
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_share -> {
                showToast("share")
                true
            }
            R.id.item_delete -> {
                showToast("delete")
                true
            }
        }
        return super.onContextItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        showToast(item.title.toString())
        return true

    }

    fun showToast(word: String) {
        Toast.makeText(this, "You just pressed on $word", Toast.LENGTH_SHORT).show()
    }
}
