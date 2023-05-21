package org.gui.controllers;

import org.gui.objects.*;
import org.gui.objects.Student;

import java.sql.*;
import java.util.ArrayList;

public class DataPB {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cbts?user=root&password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<TryoutSchedule> coachGetTryoutSchedule(int coachId) {
        ArrayList<TryoutSchedule> schedules = new ArrayList<>();
        try {
            String query = "select scheduleCode, date, start_time, end_time, location from tryout_schedule where coachNo = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, coachId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                schedules.add(new TryoutSchedule(rs.getString(1), rs.getDate(2), rs.getTime(3),
                        rs.getTime(4), rs.getString(5)));
            }

            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public static boolean loginStudent(int studentId, String password) {
        try {
            String query = "select count(*) FROM registration_list where studentId = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, studentId);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            rs.next();

            System.out.println(studentId + "" + password);

            int count = rs.getInt(1);

            if (count > 0) {
                return true;
            }
            return false;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkExistingStudentId(int studentId) {
        try { String query = "select studentId FROM students WHERE studentId = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            boolean exists = rs.next();

            return exists;

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkExistingStudentIdSaRegistrationsHAHAHA(int studentId) {
        try { String query = "select studentId FROM registration_list WHERE studentId = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            boolean exists = rs.next();
            return exists;

        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static void addSport(String sportsName, String sportsDescription) throws Exception {
        String query = "INSERT INTO sports (sportsCode, sportsName, sportsDescription) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(query);

        int sportsID = getAutoIncrementedID(connection);
        
        statement.setInt(1, sportsID);
        statement.setString(2, sportsName);
        statement.setString(3, sportsDescription);
        statement.executeUpdate();
        statement.close();
    }

    private static int getAutoIncrementedID(Connection connection) throws Exception {
        String sql = "SELECT MAX(sportsCode) AS maxID FROM sports";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        int maxID = resultSet.next() ? resultSet.getInt("maxID") : 0;
        resultSet.close();
        statement.close();
        return maxID + 1;
    }

    public static void addStudent(RegisteredUser registeredUsers) {
        String query = "INSERT INTO registration_list VALUES(?,?,?,?,?)";
        String selectQ= "SELECT * FROM registration_list";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(selectQ);
            rs.next();
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            int id = rs.getInt(1);
            while (rs.next()) {
                id = rs.getInt(1);
            }
            ps.setInt(1, id + 1);
            ps.setInt(2, registeredUsers.getStudentId());
            ps.setInt(3, registeredUsers.getAppliedSport());
            ps.setString(4,registeredUsers.getContactNumber());
            ps.setString(5, registeredUsers.getPassword());
            ps.execute();

            System.out.println("Registration Success!\n");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addStudentToDB(Student student) throws SQLException {
        String query = "INSERT INTO students VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            ps.setInt(1, student.getStudentID());
            ps.setString(2, student.getFirstName());
            ps.setString(3, student.getLastName());
            ps.setInt(4, student.getDeptID());
            ps.execute();
        } catch (SQLException e) {
            throw e;
        }
    }



    public static void showStatusOfStudent(int studentId) throws SQLException {
        try {
            String query = "select tryout_sched_details.scheduleCode, tryout_sched_details.status\n" +
                    "from tryout_sched_details\n" +
                    "    inner join registration_list\n" +
                    "        using (registrationId)\n" +
                    "where registration_list.studentId =?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.err.println("Please select a schedule first.");
            } else {
                System.out.printf("%-20s%-20s%n", "Schedule Code", "Status");
                do {
                    System.out.printf("%-20s%-20s%n", rs.getString(1), rs.getString(2));
                } while (rs.next());
            }
            rs.close();
        }catch (Exception e) {
            throw e;
        }
    }

    public static boolean loginCoach(int coachNo, String password) {
        try {
            String query = "select count(*) FROM coach where coachNo = ? and password = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, coachNo);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            rs.next();

            System.out.println(coachNo + "" + password);

            int count = rs.getInt(1);

            if (count > 0) {
                return true;
            }

            return false;
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getDeptId(int studentId) throws SQLException {
        int deptId = 0;
        try {
            String query = "select deptId from students where studentId = ?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                deptId = rs.getInt("deptID");
            } else {
                System.err.println("No student found with the provided ID number.");
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw e;
        }

        return deptId;
    }



    public static void getTryOutSchedule(int sportsCode, int depId) throws SQLException {
        try {
            String query = "select tryout_schedule.scheduleCode, tryout_schedule.date, tryout_schedule.start_time, " +
                    "tryout_schedule.end_time, tryout_schedule.location\n" +
                    "from tryout_schedule\n" +
                    "    inner join coach c\n" +
                    "    using (sportsCode)\n" +
                    "    inner join coordinators c2\n" +
                    "    on c.coachNo = c2.idcoordinators\n" +
                    "where sportsCode=? AND deptID=?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, sportsCode);
            statement.setInt(2, depId);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.err.println("No available schedule yet for your department and applied sport.");
            } else {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "Schedule Code", "Date (YYYY-mm-dd)", "Start Time", "End Time",
                        "Location");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "-------------", "-----------------", "----------", "--------",
                        "--------");
                do {
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5));
                } while (rs.next());
            }
            rs.close();
        }catch (Exception e) {
            throw e;
        }
    }

    public static ArrayList<Sport> getAvailableSports() throws Exception{
        ArrayList<Sport> sports = new ArrayList<>();
        String query = "SELECT * FROM sports";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//        statement.setInt(1, sportsCode);
        ResultSet rs = statement.executeQuery(query);
        rs.beforeFirst();

        while (rs.next()) {
            sports.add(new Sport(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        rs.close();
        return sports;
    }



    public static void addSchedule(TryoutSchedule tryoutSchedule) {
        String query = "INSERT INTO tryout_schedule(scheduleCode, sportsCode, deptTryoutCode, date, start_time," +
                "end_time, location, totalStudents) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, tryoutSchedule.getScheduleCode());
            ps.setInt(2, tryoutSchedule.getSportsCode());
            ps.setInt(3, tryoutSchedule.getDeptTryoutCode());
            ps.setDate(4, tryoutSchedule.getDate());
            ps.setTime(5, tryoutSchedule.getStartTime());
            ps.setTime(6, tryoutSchedule.getEndTime());
            ps.setString(7, tryoutSchedule.getLocation());
            ps.setInt(8, tryoutSchedule.getTotalStudents());
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static int getCoachSportsCode(int coachID) throws SQLException {
        String query = "SELECT sportsCode FROM coach where coachNo=?";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        statement.setInt(1, coachID);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            return resultSet.getInt(1);
        }
        resultSet.close();
        return 0;
    }

    public static ArrayList<RegisteredUser> getRegisteredStudents() throws SQLException {
        ArrayList<RegisteredUser> registeredUsers = new ArrayList<>();
        String query = "SELECT * FROM registration_list";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {

            registeredUsers.add(new RegisteredUser(resultSet.getInt(1), resultSet.getInt(2),resultSet.getInt(3),
                    resultSet.getString(4), resultSet.getString(5)));
        }
        resultSet.close();
        return registeredUsers;
    }

    public static void showListOfRegisteredWithStatus(String schedCode) throws SQLException {
        String query = "select students.studentID, tryout_sched_details.registrationId,\n" +
                "       concat(students.firstname, space(1), students.lastname), status\n" +
                "from tryout_sched_details\n" +
                "natural join registration_list\n" +
                "natural join students\n" +
                "where registration_list.schedCode=?";
        PreparedStatement statement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        statement.setString(1, schedCode);
        ResultSet resultSet = statement.executeQuery();

        System.out.printf("%-20s%-20s%-20s%-20s%n", "Id Number", "Registration Id", "Full Name", "Status");
        System.out.printf("%-20s%-20s%-20s%-20s%n", "---------", "---------------", "---------", "------");
        while (resultSet.next()) {
            System.out.printf("%-20d%-20d%-20s%-20s%n", resultSet.getInt(1), resultSet.getInt(2),
                    resultSet.getString(3), resultSet.getString(4));
        }
        resultSet.close();
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


    public static ArrayList<TryoutSchedDetails> getTryOutSchedDetails(int coachNo) throws Exception   {
        ArrayList<TryoutSchedDetails> schedDetails = new ArrayList<>();
        String query = "SELECT registrationId, concat(firstName, space(1), lastName) as 'Full Name', scheduleCode, status\n" +
                "FROM tryout_sched_details natural join tryout_schedule natural join registration_list natural join students\n" +
                "where coachNo = ?";
        PreparedStatement statement = connection.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        statement.setInt(1, coachNo);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            schedDetails.add(new TryoutSchedDetails(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getString(3), resultSet.getString(4)));
        }
        resultSet.close();
        return schedDetails;
    }

    public static void updateStatusOfStudent(int regId, String status) {
        String query = "UPDATE tryout_sched_details set status=? where registrationId=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, status);
            ps.setInt(2, regId);
            ps.execute();
            System.out.println("Status updated! ");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    }

    public static ArrayList<TryoutSchedule> getTryouts(int sportsCode, int deptId) throws SQLException {
        ArrayList<TryoutSchedule> tryoutSchedules = new ArrayList<>();
        try {
            String query = "select tryout_schedule.scheduleCode,tryout_schedule.sportsCode, tryout_schedule.deptTryoutCode," +
                    " tryout_schedule.date, tryout_schedule.start_time, " +
                    "tryout_schedule.end_time, tryout_schedule.location, tryout_schedule.totalstudents\n" +
                    "from tryout_schedule\n" +
                    "    inner join coach c\n" +
                    "    using (sportsCode)\n" +
                    "    inner join coordinators c2\n" +
                    "    on c.coachNo = c2.idcoordinators\n" +
                    "where sportsCode=? AND deptID=?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, sportsCode);
            statement.setInt(2, deptId);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.err.println("No available schedule yet for your department and applied sport.");
            } else {
                do {
                    tryoutSchedules.add(new TryoutSchedule(rs.getString(1), rs.getInt(2),rs.getInt(3),
                            rs.getDate(4),rs.getTime(5),rs.getTime(6),rs.getString(7),
                            rs.getInt(8)));
                } while (rs.next());
            }
            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return tryoutSchedules;
    }

    public static void showRegisteredStudentsWithStatus() throws SQLException {
        ArrayList<RegisteredUser> registeredUsers = new ArrayList<>();
        String query = "SELECT * FROM registration_list";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE  );
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            registeredUsers.add(new RegisteredUser(resultSet.getInt(1), resultSet.getInt(2),resultSet.getInt(3),
                    resultSet.getString(4), resultSet.getString(5)));
        }
        resultSet.close();
    }


    public static void deleteTryout(String schedCode) {
        String query = "DELETE FROM tryout_schedule where scheduleCode=?";
        try {
            PreparedStatement ps = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, schedCode);
            ps.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }



    public static Coordinator getCoordinator(int coordId) throws SQLException {
        try {
            String query = "SELECT * FROM coordinators where idcoordinators=?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, coordId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Coordinator coordinator = new Coordinator(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getInt(4));
                if (coordinator.getCoordinatorID() == coordId) {
                    return coordinator;
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            throw e;
        }
        throw new SQLException();
    }

    public static ArrayList<Coach> getCoaches() throws Exception{
        ArrayList<Coach> coaches = new ArrayList<>();
        String query = "SELECT concat(c.firstName, space(1), c.lastName), s.sportsName from coach n\n" +
                "inner join coordinators c on n.coachNo = c.idcoordinators inner join sports s on n.sportsCode = s.sportsCode;";
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        ResultSet rs = statement.executeQuery(query);

        while (rs.next()) {
            coaches.add(new Coach(rs.getString(1), rs.getString(2)));
        }
        rs.close();
        return coaches;
    }

    public static void addCoach(int coachNo, int sportsCode) throws Exception{

        String coachPass = generateNewCoachId();
        String query = "INSERT INTO coach (coachNo, password, sportsCode) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, coachNo);
        statement.setString(2, coachPass);
        statement.setInt(3, sportsCode);

        statement.executeUpdate();

    }

    private static String generateNewCoachId() throws SQLException {
        String sql = "SELECT password FROM coach ORDER BY password DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        String latestStringId = resultSet.next() ? resultSet.getString("password") : "coach0";
        resultSet.close();
        statement.close();

        int coachNo = Integer.parseInt(latestStringId.substring(5)) + 1;
        return "coach" + coachNo;
    }

    public static ArrayList<Coordinator> getAllCoordinators() throws Exception{

        ArrayList<Coordinator> coordinators = new ArrayList<>();

        String query = "SELECT * FROM coordinators";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("idcoordinators");
            String lastName = resultSet.getString("lastName");
            String firstName = resultSet.getString("firstName");
            int deptID = resultSet.getInt("deptID");

            Coordinator coordinator = new Coordinator(id, lastName, firstName, deptID);
            coordinators.add(coordinator);
        }
        resultSet.close();
        statement.close();

        return coordinators;
    }

    public static void showScheduleForCoordinator(int coordId) throws SQLException {

        try {
            String query = "select tryout_schedule.scheduleCode, tryout_schedule.date, tryout_schedule.start_time,\n" +
                    "       tryout_schedule.end_time, tryout_schedule.location\n" +
                    "from tryout_schedule\n" +
                    "       where tryout_schedule.deptTryoutCode=?";
            PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.setInt(1, coordId);
            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.err.println("You have not added a schedule yet.");
            } else {
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "Schedule Code", "Date (YYYY-mm-dd)", "Start Time", "End Time",
                        "Location");
                System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", "-------------", "-----------------", "----------", "--------",
                        "--------");
                do {
                    System.out.printf("%-20s%-20s%-20s%-20s%-20s%n", rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getString(4), rs.getString(5));
                } while (rs.next());
            }
            rs.close();
        }catch (Exception e) {
            throw e;
        }
    }



}
