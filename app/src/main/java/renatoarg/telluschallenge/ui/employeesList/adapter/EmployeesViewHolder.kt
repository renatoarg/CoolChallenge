package renatoarg.telluschallenge.ui.employeesList.adapter

import renatoarg.telluschallenge.databinding.ItemEmployeeBinding
import renatoarg.telluschallenge.model.Employee

class EmployeeViewHolder(
    private val binding: ItemEmployeeBinding,
    private val callBack: (Employee) -> Unit
) : EmployeesAdapter.BaseViewHolder<Employee>(binding.root) {


    override fun bind(item: Employee) {
        binding.apply {
            nameTextView.text = item.employee_name
            salaryTextView.text = item.employee_salary.toString()
            ageTextView.text = item.employee_age.toString()
        }
    }
}