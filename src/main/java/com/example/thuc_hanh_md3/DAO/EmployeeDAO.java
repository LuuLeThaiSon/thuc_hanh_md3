package com.example.thuc_hanh_md3.DAO;

import com.example.thuc_hanh_md3.connection.MyConnection;
import com.example.thuc_hanh_md3.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private final Connection connection;
    private DepartmentDAO departmentDAO;

    public EmployeeDAO() {
        connection = MyConnection.getConnection();
        departmentDAO = new DepartmentDAO();
    }

    private final String FIND_ALL = "select * from employee;";
    private final String INSERT = "insert into employee(name, email, address, phoneNumber, salary, departmentId) value (?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "update employee set name = ?, email = ?, address = ?, phoneNumber = ?, salary = ?, departmentId = ? where id = ?;";
    private final String DELETE = "delete from employee where id = ?;";
    private final String SEARCH = "select * from employee where name like concat('%',?,'%');";
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            result(employees, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public boolean insert(Employee employee) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT)) {
            setSQL(employee, ps);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setSQL(Employee employee, PreparedStatement ps) throws SQLException {
        ps.setString(1, employee.getName());
        ps.setString(2, employee.getEmail());
        ps.setString(3, employee.getAddress());
        ps.setString(4, employee.getPhoneNumber());
        ps.setDouble(5, employee.getSalary());
        ps.setLong(6, employee.getDepartment().getId());
    }

    public boolean update(Employee employee) {
        try(PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            setSQL(employee, ps);
            ps.setLong(7, employee.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Long id) {
        try(PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Employee> search(String search) {
        List<Employee> employees = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(SEARCH)) {
            ps.setString(1, search);
            result(employees, ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private void result(List<Employee> employees, PreparedStatement ps) throws SQLException {
        ResultSet resultSet = ps.executeQuery();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String address = resultSet.getString("address");
            String phoneNumber = resultSet.getString("phoneNumber");
            Double salary =resultSet.getDouble("salary");
            Long departmentId = resultSet.getLong("departmentId");
            employees.add(new Employee(id, name, email, address, phoneNumber, salary, departmentDAO.findById(departmentId)));
        }
    }
}
