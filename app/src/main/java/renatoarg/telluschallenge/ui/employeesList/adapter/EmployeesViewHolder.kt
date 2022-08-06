package renatoarg.telluschallenge.ui.employeesList.adapter

import renatoarg.telluschallenge.databinding.ItemEmployeeBinding
import renatoarg.telluschallenge.model.Employee

class EmployeeViewHolder(
    private val binding: ItemEmployeeBinding,
    private val callBack: (Employee) -> Unit
) : EmployeesAdapter.BaseViewHolder<Employee>(binding.root) {


    override fun bind(item: Employee) {
        binding.apply {
            nameTextView.text = item.name
            salaryTextView.text = item.salary.toString()
            ageTextView.text = item.age.toString()
            itemLayout.setOnClickListener {
                callBack(item)
            }
        }
    }
}