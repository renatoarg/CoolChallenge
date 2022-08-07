package renatoarg.telluschallenge.ui.employeesList.adapter

import renatoarg.telluschallenge.R
import renatoarg.telluschallenge.databinding.ItemEmployeeBinding
import renatoarg.telluschallenge.model.Employee
import renatoarg.telluschallenge.ui.toUsdCurrency

class EmployeeViewHolder(
    private val binding: ItemEmployeeBinding,
    private val onEmployeeClicked: (Employee) -> Unit
) : EmployeesAdapter.BaseViewHolder<Employee>(binding.root) {

    override fun bind(item: Employee) {
        val context = binding.root.context
        binding.apply {
            nameTextView.text = item.name
            salaryTextView.text = context.getString(R.string.salary_value, item.salary.toUsdCurrency())
            ageTextView.text = context.getString(R.string.age_value, item.age)
            itemLayout.setOnClickListener {
                onEmployeeClicked(item)
            }
        }
    }
}