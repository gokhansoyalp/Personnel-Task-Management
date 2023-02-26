package task.management.persontaskapp

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper


//creating the database logic, extending the SQLiteOpenHelper base class
class DatabaseNot(context: NotActivity): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {
    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "NoteDatabase"
        private val TABLE_CONTACTS = "NoteTable"
        private val KEY_ID = "id"
        private val KEY_SUBJ = "konu"
        private val KEY_NOTE = "not"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //creating table with fields
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_SUBJ + " TEXT,"
                + KEY_NOTE + " TEXT," + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS)
        onCreate(db)
    }


    //method to insert data
    fun addEmployee(note: NoteModel):Long{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, note.noteID)
        contentValues.put(KEY_SUBJ, note.noteSubj ) // EmpModelClass Name
        contentValues.put(KEY_NOTE, note.noteWhole)
        // Inserting Row
        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to read data
    fun viewEmployee():List<NoteModel>{
        val empList:ArrayList<NoteModel> = ArrayList<NoteModel>()
        val selectQuery = "SELECT  * FROM $TABLE_CONTACTS"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var uId: Int
        var uSubj: String
        var uNote: String
        if (cursor.moveToFirst()) {
            do {
                uId = cursor.getInt(cursor.getColumnIndex("id"))
                uSubj = cursor.getString(cursor.getColumnIndex("konu"))
                uNote = cursor.getString(cursor.getColumnIndex("not"))
                val emp= NoteModel(noteID = uId, noteSubj = uSubj, noteWhole = uNote)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }
    //method to update data
    fun updateEmployee(emp: NoteModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.noteID)
        contentValues.put(KEY_SUBJ, emp.noteSubj) // EmpModelClass Name
        contentValues.put(KEY_NOTE, emp.noteWhole )

        // Updating Row
        val success = db.update(TABLE_CONTACTS, contentValues,"id="+emp.noteID,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
    //method to delete data
    fun deleteEmployee(emp: NoteModel):Int{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.noteID) // EmpModelClass UserId
        // Deleting Row
        val success = db.delete(TABLE_CONTACTS,"id="+emp.noteID,null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }
}