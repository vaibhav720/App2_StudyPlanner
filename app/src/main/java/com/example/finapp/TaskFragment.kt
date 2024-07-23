package com.example.finapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
// making the task fragment which will add all the task it has spinner and text box and submit button.
class TaskFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var spinnerPlan: Spinner
    lateinit var editTextPlan: EditText
    lateinit var buttonAdd: Button
    lateinit var result_plan: TextView
    var selectedPlan: String = ""
    var listofplan: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_task, container, false)

        spinnerPlan = view.findViewById(R.id.spinner_plan)
        editTextPlan = view.findViewById(R.id.editText_plan)
        buttonAdd = view.findViewById(R.id.button_add)
        result_plan = view.findViewById(R.id.result_plan)
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.plan_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPlan.adapter = adapter
        }

        spinnerPlan.onItemSelectedListener = this

        buttonAdd.setOnClickListener {
            val task = editTextPlan.text.toString()
            if (task.isNotEmpty() && selectedPlan.isNotEmpty()) {
                Toast.makeText(requireContext(), "Task: $task, type: $selectedPlan", Toast.LENGTH_LONG).show()
                listofplan.add(task)
                result_plan.text = listofplan.toString()
                editTextPlan.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter a task and select a type", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        selectedPlan = parent.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        selectedPlan = ""
    }
}