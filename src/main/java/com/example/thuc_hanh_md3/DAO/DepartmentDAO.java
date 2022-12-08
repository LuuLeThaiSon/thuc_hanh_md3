package com.example.thuc_hanh_md3.DAO;

import com.example.thuc_hanh_md3.connection.MyConnection;
import com.example.thuc_hanh_md3.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAO {
    private final Connection connection;

    public DepartmentDAO() {
        connection = MyConnection.getConnection();
    }

    private final String FIND_ALL = "select * from department;";
    private final String FIND_BY_ID = "select * from department where id = ?;";

    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                departments.add(new Department(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public Department findById(Long id) {
        try(PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                return new Department(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
