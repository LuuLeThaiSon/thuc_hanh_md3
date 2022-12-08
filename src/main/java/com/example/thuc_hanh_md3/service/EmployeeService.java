package com.example.thuc_hanh_md3.service;

import com.example.thuc_hanh_md3.DAO.DepartmentDAO;
import com.example.thuc_hanh_md3.DAO.EmployeeDAO;
import com.example.thuc_hanh_md3.model.Employee;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    private final DepartmentDAO departmentDAO;

    public EmployeeService() {
        employeeDAO = new EmployeeDAO();
        departmentDAO = new DepartmentDAO();
    }

    public List<Employee> findAll(HttpServletRequest request) {
        return employeeDAO.findAll();
    }

    public boolean save(HttpServletRequest request) {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        Double salary = Double.parseDouble(request.getParameter("salary"));
        Long departmentId = Long.parseLong(request.getParameter("departmentId"));

        if (id == null) {
            return employeeDAO.insert(new Employee(name, email, address, phoneNumber, salary, departmentDAO.findById(departmentId)));
        } else {
            return employeeDAO.update(new Employee(Long.parseLong(id), name, email, address, phoneNumber, salary, departmentDAO.findById(departmentId)));
        }
    }

    public boolean delete(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        return employeeDAO.delete(id);
    }
    public List<Employee> search(HttpServletRequest request) {
        String searchWord = request.getParameter("searchWord");
        return employeeDAO.search(searchWord);
    }
}
