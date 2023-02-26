package task.management.persontaskapp

import android.annotation.SuppressLint
import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class NoteAdapter(private val context: Activity, private val id: Array<String>, private val subj: Array<String>, private val note: Array<String>)
    : ArrayAdapter<String>(context, R.layout.custom_list, subj) {

    @SuppressLint("SetTextI18n", "ViewHolder", "InflateParams")
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.note_list, null, true)
        val idText = rowView.findViewById(R.id.textViewId) as TextView
        val subjText = rowView.findViewById(R.id.textViewName) as TextView
        val noteText = rowView.findViewById(R.id.textViewRole) as TextView

        idText.text = "Id: ${id[position]}"
        subjText.text = "Konu: ${subj[position]}"
        noteText.text = "Not: ${note[position]}"

        return rowView
    }
}