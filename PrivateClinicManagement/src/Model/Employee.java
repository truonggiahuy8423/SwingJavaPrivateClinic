/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Calendar;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author GIAHUY
 */
public class Employee {
    private Integer employeeID;
    private String name;
    private String phone;
    private Calendar birthday;
    private Calendar startDay;
    private String address;
    private String hometown;
    private String password;
    private Integer salaryPerDay;
    private String portrait;
    private Integer roleId;
    private String roleName;

    public void setSalaryPerDay(Integer salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public void setStartDay(Calendar startDay) {
        this.startDay = startDay;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalaryPerDay(int salaryPerDay) {
        this.salaryPerDay = salaryPerDay;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
    
    public Integer getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public Calendar getStartDay() {
        return startDay;
    }

    public String getAddress() {
        return address;
    }

    public String getHometown() {
        return hometown;
    }

    public String getPassword() {
        return password;
    }

    public Integer getSalaryPerDay() {
        return salaryPerDay;
    }

    public String getPortrait() {
        return portrait;
    }

    public Integer getRoleId() {
        return roleId;
    }
    
     public Employee(int employeeId, String fullName, String roleName, String phone, String password, Calendar birthday, String address, String hometown, Calendar startDay) {
        this.employeeID = employeeId;
        this.name = fullName;
        this.roleName = roleName;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.hometown = hometown;
        this.startDay = startDay;
    }
    
    public Employee(int employeeId, String fullName, int roleId, String phone, String password, Calendar birthday, String address, String hometown, Calendar startDay) {
        this.employeeID = employeeId;
        this.name = fullName;
        this.roleId = roleId;
        this.phone = phone;
        this.password = password;
        this.birthday = birthday;
        this.address = address;
        this.hometown = hometown;
        this.password = password;
    }
    
    public Employee(){}

    public Employee(Integer employeeID, String name, String phone){
        this.employeeID = employeeID;
        this.name = name;
         this.phone = phone;
    }
   public Employee(int id)
    {
        this.employeeID = id;
    }
    public Employee(int employeeId, String fullName, String phone, Calendar birthday, Calendar startDay, String address, String hometown, String password, int salaryPerDay, String portrait, int roleId, String roleName) {
        this.employeeID = employeeId;
        this.name = fullName;
        this.phone = phone;
        this.birthday = birthday;
        this.startDay = startDay;
        this.address = address;
        this.hometown = hometown;
        this.password = password;
        this.salaryPerDay = salaryPerDay;
        this.portrait = portrait;
        this.roleId = roleId;
        this.roleName = roleName;
    }
    


    
    public void addEmployee(Employee employee) throws SQLException
    {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  

            String username = "c##uni4";  // Replace with your username
            String password = "123";  // Replace with your password
            String sqlInsert = "insert into employee(employee_id, full_name, role_id, phone, password, birthday, "
                    + "address, hometown) values(employee_id_sequence.nextval, ?, ?, ?, ?, ?, ?, ?)";

//            String username = "AD";  // Replace with your username
//            String password = "88888888";  // Replace with your password
//            String sqlInsert = "insert into employee(employee_id, full_name, phone, birthday, "
//                    + "start_day, address, hometown, password, salary_per_day, role_id) values(employee_id_sequence.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.prepareStatement(sqlInsert);
//            statement.setInt(1, employee.getEmployeeId());
            statement.setString(1, employee.getName());
            statement.setInt(2, employee.getRoleId());
            statement.setString(3, employee.getPhone());
            statement.setString(4, employee.getPassword());
            statement.setDate(5, employee.getBirthday() == null ? null : new java.sql.Date(employee.getBirthday().getTimeInMillis()));
//            statement.setDate(5, employee.getStartDay() == null ? null : new java.sql.Date(employee.getStartDay().getTimeInMillis()));
            statement.setString(6, employee.getAddress());
            statement.setString(7, employee.getHometown());          
//            statement.setInt(1, employee.getSalaryPerDay());
            statement.executeUpdate(); // co van de o day
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public void getListOfEmployees(String sql, List<Employee> listOfEmployee) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try
        {Class.forName("oracle.jdbc.driver.OracleDriver");
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
        String username = "AD";  // Replace with your username
        String password = "88888888";  // Replace with your password
        connection = null; statement = null; result = null;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            //System.out.println(connection.isClosed());
            statement = connection.createStatement() ;  
            result = statement.executeQuery(sql);
            //System.out.println(rs.next()); 
            while (result.next())
            {
                Calendar birthday = null;
                if (result.getDate(4) != null)
                {
                    birthday = Calendar.getInstance();
                    birthday.setTimeInMillis(result.getDate(4).getTime());
                }
                Calendar start_date = null;
                if (result.getDate(5) != null)
                {
                    start_date = Calendar.getInstance();
                    start_date.setTimeInMillis(result.getDate(5).getTime());
                }
                //"select employee.*, role.role_name from employee inner join role on employee.role_id = role.role_id"
                Employee p = new Employee(result.getInt(1), result.getString(2), result.getString(3), birthday, start_date, result.getString(6), result.getString(7), result.getString(8), result.getInt(9), " ", result.getInt(10), result.getString(11));
                listOfEmployee.add(p);
            }         
        }
        catch (ClassNotFoundException e)
        { 

            System.out.println(e);
        }
        finally { 
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
            
        }        
    }
    public Employee getAEmployee(int employee_id) throws SQLException
    {
        Employee employee = null;
        Connection connection = null;
        Statement statement = null; 
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement() ;  
            result = statement.executeQuery("select * from EMPLOYEE where employee_id = " + String.valueOf(employee_id));
            if (!result.next())
                return employee;
            
            Calendar birthday = null;
            if (result.getDate(4) != null)
            {
                birthday = Calendar.getInstance();
                birthday.setTimeInMillis(result.getDate(4).getTime());
            }
            Calendar start_date =null;
            if (result.getDate(5) != null)
            {
                start_date = Calendar.getInstance();
                start_date.setTimeInMillis(result.getDate(5).getTime());
            }
            employee = new Employee(result.getInt(1), result.getString(2), result.getString(3), birthday, start_date, result.getString(6), result.getString(7), result.getString(8), result.getInt(9), "", result.getInt(10), result.getString(11));
        } catch (ClassNotFoundException e) {}
        finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        return employee;
    }
    public void deleteEmployee(int employeeID) throws SQLException
    {
        Connection connection = null;
        Statement statement = null;
        try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";
            String sql = "delete EMPLOYEE where employee_id = " + employeeID;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            if (statement.getUpdateCount() == 0) throw new SQLException();
            
        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally { 
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
    public void updateEmployee(Employee updatedEmployee) throws SQLException
    {
        String sqlUpdate = "update employee "
                                + "set full_name = " + "?"
                                + ", phone = " + "?"
                                + ", birthday = " + "?"
                                + ", start_day = " + "?"
                                + ", address = " + "?"
                                + ", hometown = " + "?"
                                + ", password = " + "?"
                                + ", salary_per_day = " + "?"
                                
                                + ", role_id = " + "?"
                                + "where employee_id = " + "?";
        Connection connection = null;
        PreparedStatement statement = null; 
        ResultSet result = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";  
            String username = "AD";  // Replace with your username
            String password = "88888888";  // Replace with your password
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.prepareStatement(sqlUpdate);
            statement.setString(1, updatedEmployee.getName());
            statement.setString(2, updatedEmployee.getPhone());
            statement.setDate(3, updatedEmployee.getBirthday() == null ? null : new java.sql.Date(updatedEmployee.getBirthday().getTimeInMillis()));
            statement.setDate(4, updatedEmployee.getStartDay() == null ? null : new java.sql.Date(updatedEmployee.getStartDay().getTimeInMillis()));
            statement.setString(6, updatedEmployee.getAddress());
            statement.setString(7, updatedEmployee.getHometown());
            statement.setString(8, updatedEmployee.getPassword());
            statement.setInt(9, updatedEmployee.getSalaryPerDay());
            statement.setInt(10, updatedEmployee.getRoleId());
            statement.executeUpdate();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally {
            if (result != null) result.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        
    }
    
    public void getListOfEmployee(String sql, List<Employee> listOfEmployee){
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "c##phongkham", "phongkham");
//            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "AD", "88888888");
            Statement statement = connection.createStatement() ;  
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                Employee p = new Employee(result.getInt(1), result.getString(2), result.getString(3));
                listOfEmployee.add(p);
            }
            connection.close();
        } 
        catch (SQLException | ClassNotFoundException e){
            System.out.println(e.toString()); 
        }
        finally{
            System.out.println("Successful"); 
        }        
    }
}

    
