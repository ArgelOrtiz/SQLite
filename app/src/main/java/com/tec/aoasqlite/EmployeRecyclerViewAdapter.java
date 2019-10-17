package com.tec.aoasqlite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tec.aoasqlite.DataBase.Entities.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeRecyclerViewAdapter.ViewHolder> {
    List<Employee> employeeList;


    public EmployeRecyclerViewAdapter(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.employee_main_recycler_view_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Employee currentEmployee    = employeeList.get(position);

        holder.process(currentEmployee);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView codeTextView,firstNameTetView,lastNameTetView,phoneTetView,balanceTetView;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            codeTextView        = itemView.findViewById(R.id.codeEmployeeItemTextView);
            firstNameTetView    = itemView.findViewById(R.id.firstNameEmployeeItemTetView);
            lastNameTetView     = itemView.findViewById(R.id.lastNameEmployeeItemTetView);
            phoneTetView        = itemView.findViewById(R.id.phoneEmployeeItemTetView);
            balanceTetView      = itemView.findViewById(R.id.balanceEmployeeItemTetView);
        }

        private void  process(Employee currentEmployee){

            codeTextView.setText(String.valueOf(currentEmployee.getCode()));
            firstNameTetView.setText(currentEmployee.getFirst_name());
            lastNameTetView.setText(currentEmployee.getLast_name());
            phoneTetView.setText(String.valueOf(currentEmployee.getPhone()));
            balanceTetView.setText(String.valueOf(currentEmployee.getBalance()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }
}
