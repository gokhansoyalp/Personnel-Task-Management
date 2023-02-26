package task.management.persontaskapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class NotActivity() : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.insert_btn_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        val listView = findViewById<ListView>(R.id.listView)
        if(id==R.id.action_one){
            listView.visibility= View.GONE
            changeFragment(AddNoteActivity())
            return true
        }
        if (id==R.id.action_two){
            viewRecord()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.not_activity)
        viewRecord()
    }

    private fun changeFragment(fragment : Fragment){
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLay, fragment)
        fragmentTransaction.commit()
    }

    fun viewRecord(){
        val listView=findViewById<ListView>(R.id.listView)
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseNot= DatabaseNot(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<NoteModel> = databaseHandler.viewEmployee()
        val empArrayId = Array<String>(emp.size){"0"}
        val empArraySubj = Array<String>(emp.size){"null"}
        val empArrayNote = Array<String>(emp.size){"null"}
        var index = 0
        for(e in emp){
            empArrayId[index] = e.noteID.toString()
            empArraySubj[index] = e.noteSubj
            empArrayNote[index] = e.noteWhole
            index++
        }
        //creating custom ArrayAdapter
        val noteAdapter = NoteAdapter(this,empArrayId,empArraySubj,empArrayNote)
        listView.adapter = noteAdapter

    }

    fun updateRecord(){
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.update_not_dialog, null)
        dialogBuilder.setView(dialogView)

        val edtId = dialogView.findViewById(R.id.updateId) as EditText
        val edtName = dialogView.findViewById(R.id.updateName) as EditText
        val edtRole = dialogView.findViewById(R.id.updateRole) as EditText

        val databaseHandler1: DatabaseNot= DatabaseNot(this)
        val emp: List<NoteModel> =databaseHandler1.viewEmployee()

        val empArrayId= Array<String>(emp.size){"0"}
        val empArrayName= Array<String>(emp.size){""}
        val empArrayRole= Array<String>(emp.size){""}
        for ((index, e) in emp.withIndex()){
            empArrayId[index] = e.noteID.toString()
            empArrayName[index] = e.noteSubj.toString()
            empArrayRole[index] = e.noteWhole.toString()

            edtId.setText(empArrayId[index])
            edtName.setText(empArrayName[index])
            edtRole.setText(empArrayRole[index])

        }
        dialogBuilder.setTitle("Update Record")
        dialogBuilder.setMessage("Enter data below")
        dialogBuilder.setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->

            val updateId = edtId.text.toString()
            val updateName = edtName.text.toString()
            val updateRole = edtRole.text.toString()

            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseNot= DatabaseNot(this)
            if(updateId.trim()!="" && updateName.trim()!="" && updateRole.trim()!=""){
                //calling the updateEmployee method of DatabaseHandler class to update record
                val status = databaseHandler.updateEmployee(NoteModel(Integer.parseInt(updateId),updateName, updateRole))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record Updated",Toast.LENGTH_LONG).show()
                    viewRecord()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }
    fun deleteRecord(){
        //creating AlertDialog for taking user id
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_not_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            //creating the instance of DatabaseHandler class
            val databaseHandler: DatabaseNot= DatabaseNot(this)
            if(deleteId.trim()!=""){
                //calling the deleteEmployee method of DatabaseHandler class to delete record
                val status = databaseHandler.deleteEmployee(NoteModel(Integer.parseInt(deleteId),"",""))
                if(status > -1){
                    Toast.makeText(applicationContext,"Record Deleted",Toast.LENGTH_LONG).show()
                    viewRecord()
                }
            }else{
                Toast.makeText(applicationContext,"id or name or email cannot be blank",Toast.LENGTH_LONG).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ ->
            //pass
        })
        val b = dialogBuilder.create()
        b.show()
    }

    fun updateRecord(view: View) {
        updateRecord()
    }

    fun deleteRecord(view: View) {
        deleteRecord()
    }

}