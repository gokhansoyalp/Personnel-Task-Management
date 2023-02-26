package task.management.persontaskapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddNoteActivity() : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.add_not_activity, container, false)

        val btnInsert=view.findViewById<Button>(R.id.btn_insert)
        val btnCancel=view.findViewById<Button>(R.id.btn_cancel)



        btnInsert.setOnClickListener {
            val personID =view.findViewById<EditText>(R.id.insert_id)
            val personName=view.findViewById<EditText>(R.id.insert_name)
            val personRole=view.findViewById<EditText>(R.id.insert_role)

            NoteModel(personID.text.toString().toInt(),personName.text.toString(),personRole.text.toString())
            saveRecord()
            val intent = Intent(context, NotActivity::class.java)
            startActivity(intent)
        }
        btnCancel.setOnClickListener {
            val intent = Intent(context, NotActivity::class.java)
            startActivity(intent)
        }

        return view

    }



    private fun saveRecord(){
        val pID = view?.findViewById<EditText>(R.id.insert_id)
        val pName = view?.findViewById<EditText>(R.id.insert_name)
        val pRole= view?.findViewById<EditText>(R.id.insert_role)

        val personID = pID?.text.toString()
        val personName = pName?.text.toString()
        val personRole = pRole?.text.toString()

        val databaseHandler: DatabaseNot= DatabaseNot(context as NotActivity)
        if(personID.trim()!="" && personName.trim()!="" && personRole.trim()!="" ){
            val status = databaseHandler.addEmployee(NoteModel(Integer.parseInt(personID),personName, personRole))
            if(status > -1){
                Toast.makeText(context,"Record Saved", Toast.LENGTH_LONG).show()
                pID?.text?.clear()
                pName?.text?.clear()
                pRole?.text?.clear()
            }
        }else{
            Toast.makeText(context,"id or name or email cannot be blank", Toast.LENGTH_LONG).show()
        }

    }


}