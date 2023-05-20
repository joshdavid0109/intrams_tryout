package org.gui.objects;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

public class Department implements Serializable {
    private static Connection connection;
    private int deptId;
    private String department;

    public Department(int deptId) throws SQLException {
        setConnection();

        ArrayList<Department> departments = getDepartments();

        for (int i = 0; i < departments.size(); i++) {
            Department d = departments.get(i);
            if (deptId == d.deptId) {
                department = d.department;
            }
        }
    }

    public static ArrayList<Department> getDepartments() throws SQLException {
        ArrayList<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM departments";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            departments.add(new Department(resultSet.getInt(1), resultSet.getString(2)));
        }
        resultSet.close();
        return departments;
    }

    public Department(int deptId, String department) {
        this.deptId = deptId;
        this.department = department;
    }

    public static void setConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/im?user=root&password");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
