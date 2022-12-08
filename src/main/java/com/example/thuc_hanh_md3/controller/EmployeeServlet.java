package com.example.thuc_hanh_md3.controller;

import com.example.thuc_hanh_md3.DAO.DepartmentDAO;
import com.example.thuc_hanh_md3.model.Department;
import com.example.thuc_hanh_md3.model.Employee;
import com.example.thuc_hanh_md3.service.EmployeeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeService employeeService;
    private DepartmentDAO departmentDAO;

    @Override
    public void init() {
        employeeService = new EmployeeService();
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "delete":
                delete(request, response);
                break;

            default:
                findAll(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                create(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "search":
                search(request, response);
                break;
            default:
                findAll(request, response);

        }
    }

    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll(request);
        List<Department> departments = departmentDAO.findAll();
        request.setAttribute("employees", employees);
        request.setAttribute("departments", departments);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    public void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeService.save(request);
        response.sendRedirect("employee");
    }

    public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeService.save(request);
        response.sendRedirect("employee");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeService.delete(request);
        response.sendRedirect("employee");
    }

    public void search(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Employee> employees = employeeService.search(request);
        request.setAttribute("employees", employees);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }
}
