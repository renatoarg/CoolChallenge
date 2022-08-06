package renatoarg.telluschallenge.ui.employeesList.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import renatoarg.telluschallenge.databinding.ItemEmployeeBinding
import renatoarg.telluschallenge.model.Employee

class EmployeesAdapter(
    private val callBack: (Employee) -> Unit
) : RecyclerView.Adapter<EmployeesAdapter.BaseViewHolder<*>>() {

    private var items = ArrayList<Employee>()

    companion object {
        private const val TYPE_EMPLOYEE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            TYPE_EMPLOYEE -> {
                val itemBinding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                EmployeeViewHolder(
                    itemBinding,
                    callBack
                )
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return TYPE_EMPLOYEE
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is EmployeeViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun resetList(employees: List<Employee>) {
        this.items.clear()
        this.items.addAll(employees)
        notifyDataSetChanged()
    }

    abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: T)
    }
}