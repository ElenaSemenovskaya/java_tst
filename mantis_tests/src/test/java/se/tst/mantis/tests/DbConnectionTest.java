package se.tst.mantis.tests;

import org.testng.annotations.Test;
import se.tst.mantis.model.UserData;
import se.tst.mantis.model.Users;

import java.sql.*;

public class DbConnectionTest {
    @Test
    public void testDbConnection () {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?user=root&password=&serverTimezone=UTC");
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select id, lastname, firstname from addressbook");
            Users users= new Users();
            while (rs.next()) {
                users.add(new UserData()
                        .withId(rs.getInt("id"))
                        .withUserName(rs.getString("username"))
                        .withEmail(rs.getString("email")));
            }
            rs.close();
            st.close();
            conn.close();

            System.out.println(users);
            // Do something with the Connection
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}


