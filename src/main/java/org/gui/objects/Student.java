    package org.gui.objects;

    import java.io.Serializable;

    public class Student implements Serializable {
        private int studentID;
        private String firstName;
        private String lastName;
        private int deptID;

        private RegisteredUser registeredUser;

        private TryoutSchedDetails tryoutSchedDetails;


        public Student(int studentID, String firstName, String lastName, int deptID) throws Exception {
            this.studentID = studentID;
            this.firstName = firstName;
            this.lastName = lastName;
            this.deptID = deptID;
        }

        public int getStudentID() {
            return studentID;
        }

        public void setStudentID(int studentID) {
            this.studentID = studentID;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getDeptID() {
            return deptID;
        }

        public void setDeptID(int deptID) {
            this.deptID = deptID;
        }

        public RegisteredUser getRegisteredUser() {
            return registeredUser;
        }

        public void setRegisteredUser(RegisteredUser registeredUser) {
            this.registeredUser = registeredUser;
        }

        @Override
        public String toString() {
            return registeredUser.getRegId()+  registeredUser.getStudentId() +  firstName + " " + lastName
                    + tryoutSchedDetails.getStatus();
        }
    }
