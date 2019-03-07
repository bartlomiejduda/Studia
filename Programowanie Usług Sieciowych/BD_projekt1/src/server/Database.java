/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.User;
import common.Messagee;
import java.util.Set;
import java.util.HashSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author mariusz
 */
public final class Database {
    
    public static final String ERRMSG = "Error with server DB";
    
    private Connection dbConn;
    
    public Database(Connection dbConn) throws SQLException { this(dbConn, null); }
    public Database(Connection dbConn, String adminPassword) throws SQLException {
        this.dbConn = dbConn;
        if(adminPassword != null) {
            Statement st = dbConn.createStatement();
            try {
                st.execute("DROP TABLE \"user\"");
            } catch(SQLException e) {}
            st.execute("CREATE TABLE \"user\" (" +
                "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "firstName VARCHAR(64) NOT NULL," +
                "lastName VARCHAR(64) NOT NULL," +
                "passwordHash VARCHAR(64) NOT NULL," +
                "CONSTRAINT user_primary_key PRIMARY KEY (id)," + //bd
                    "state VARCHAR(64)," + //bd
                    "last_active_time TIMESTAMP,"
                    + "email VARCHAR(64)" +  //bd
                ")");
            try {
                st.execute("DROP TABLE \"friend\"");
            } catch(SQLException e) {}
            st.execute("CREATE TABLE \"friend\" (" +
                "id1 INTEGER NOT NULL," +
                "id2 INTEGER NOT NULL," +
                "CONSTRAINT friend_primary_key PRIMARY KEY (id1, id2)" +
                ")");            
              addUser(new User("Admin", "Istrator", adminPassword, "MD5"));
//            Set<Integer> ids = getUserIds("%");
//            for(Integer id: ids) {
//                System.out.println("#" + id + ": " + getUser(id));
//            }
            try {
                st.execute("DROP TABLE \"message\"");
            } catch(SQLException e) {}
            st.execute("CREATE TABLE \"message\" (" +
                "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
                "msgsent TIMESTAMP," +
                "msgread TIMESTAMP," +
                "msgfrom INTEGER NOT NULL," +
                "msgto INTEGER NOT NULL," +
                "content VARCHAR(1024)," +
                "CONSTRAINT message_primary_key PRIMARY KEY (id)" +
                ")");            
        }
    }
    
    
    
    public void setLastActiveTime(int id) throws SQLException { //bd
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"user\" set last_active_time = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
        java.sql.Date date = getCurrentDatetime();
        st.setTimestamp(1, new java.sql.Timestamp( new java.util.Date().getTime()));
        st.setInt(2, id);
        st.executeUpdate();
    }
    
    
        public void setMail(int id, String email) throws SQLException { //bd
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"user\" set email = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
        java.sql.Date date = getCurrentDatetime();
        st.setString(1, email);
        st.setInt(2, id);
        st.executeUpdate();
    }
        
        public void setPassword(String mail, String password) throws SQLException { //bd
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"user\" set passwordHash = ? where email = ?", Statement.RETURN_GENERATED_KEYS);
        java.sql.Date date = getCurrentDatetime();
        st.setString(1, User.makeHash(password, "MD5"));
        st.setString(2, mail);
        st.executeUpdate();
    }
        
        
    
    
    public java.sql.Date getCurrentDatetime() { //bd
    java.util.Date today = new java.util.Date();
    return new java.sql.Date(today.getTime());
}
    
        public java.sql.Date convertToSqlDate(java.util.Date dat) { //bd
    java.util.Date today = dat;
    return new java.sql.Date(today.getTime());
        }
        
    public java.util.Date convertToJavaDate(java.sql.Date sqlDate) { //bd
        java.util.Date javaDate = null;
        if (sqlDate != null) {
            javaDate = new Date(sqlDate.getTime());
        }
        return javaDate;
    }

    
    public void setState(String state, int id) throws SQLException { //bd
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"user\" set state = ? where id = ?", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, state);
        st.setInt(2, id);
        st.executeUpdate();
    }
    
    public int addUser(User u) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("INSERT INTO \"user\" (firstName, lastName, passwordHash, state, last_active_time) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        st.setString(1, u.getFirstName());
        st.setString(2, u.getLastName());
        st.setString(3, u.getPasswordHash());
        st.setString(4, u.getState());
        st.setTimestamp(5, new java.sql.Timestamp(u.getLastActiveTime().getTime()));
        SimpleDateFormat format= new SimpleDateFormat("DD.MM.YYYY HH:mm:ss");
        //System.out.println("AAAA: " + format.format(u.getLastActiveTime()));
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }
    
    public User getUser(int id) throws SQLException {
        if(id == 0) return null;
        PreparedStatement st = dbConn.prepareStatement("SELECT firstName, lastName, passwordHash, state, last_active_time FROM \"user\" WHERE id=?"); //bd
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next();
        System.out.println("Date userrrrr: " + rs.getTimestamp(5));
        return new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5)); //bd
    }
    
    public Set<Integer> getUserIds(String pattern) throws SQLException {
        Set<Integer> userIds = new HashSet<> ();
        PreparedStatement st = dbConn.prepareStatement("SELECT id FROM \"user\" WHERE firstName LIKE ? OR lastName LIKE ?");
        st.setString(1, pattern);
        st.setString(2, pattern);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            userIds.add(rs.getInt(1));
        }        
        return userIds;
    }
    
    public void updateUser(int id, User u) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"user\" SET firstName=?, lastName=?, passwordHash=? WHERE id=?");
        st.setString(1, u.getFirstName());
        st.setString(2, u.getLastName());
        st.setString(3, u.getPasswordHash());
        st.setInt(4, id);
        st.executeUpdate();
    }

    public void deleteUser(int id) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("DELETE FROM \"user\" WHERE id=?");
        st.setInt(1, id);
        st.executeUpdate();
    }
    
    public int countUsers() throws SQLException {
        Statement st = dbConn.createStatement();
        ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM \"user\"");
        rs.next();
        return rs.getInt(1);
    }
    
    public void addFriendship(int id1, int id2) throws SQLException, IllegalArgumentException {
        if(getUser(id1) == null || getUser(id2) == null) {
            throw new IllegalArgumentException();
        }
        PreparedStatement st = dbConn.prepareStatement("INSERT INTO \"friend\" (id1, id2) VALUES (?, ?)");
        st.setInt(1, id1);
        st.setInt(2, id2);
        st.executeUpdate();
    }
    
    public void deleteFriendship(int id1, int id2) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("DELETE FROM \"friend\" WHERE id1=? AND id2=?");
        st.setInt(1, id1);
        st.setInt(2, id2);
        st.executeUpdate();        
    }
    
    public boolean isFriend(int id1, int id2)  throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("SELECT COUNT(*) FROM \"friend\" WHERE id1=? AND id2=?");
        st.setInt(1, id1);
        st.setInt(2, id2);
        ResultSet rs = st.executeQuery();
        rs.next();
        return rs.getInt(1) > 0;
    }

    public Set<Integer> getFriendIds(int id1) throws SQLException {
        Set<Integer> friendIds = new HashSet<> ();
        PreparedStatement st = dbConn.prepareStatement("SELECT id2 FROM \"friend\" WHERE id1=?");
        st.setInt(1, id1);
        ResultSet rs = st.executeQuery();
        while(rs.next()) {
            friendIds.add(rs.getInt(1));
        }        
        return friendIds;
    }
    
    public int saveMessage(Messagee msg) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("INSERT INTO \"message\" (msgsent, msgread, msgfrom, msgto, content) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        st.setTimestamp(1, msg.getSent());
        st.setTimestamp(2, msg.getRead());
        st.setInt(3, msg.getFrom());
        st.setInt(4, msg.getTo());
        st.setString(5, msg.getContent());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);        
    }
    
    public Messagee restoreMessage(int id, boolean delete) throws SQLException {
        if(id == 0) return null;
        PreparedStatement st = dbConn.prepareStatement("SELECT msgsent, msgread, msgfrom, msgto, content FROM \"message\" WHERE id=?");
        st.setInt(1, id);
        ResultSet rs = st.executeQuery();
        rs.next();
        Messagee msg = new Messagee(rs.getTimestamp(1), rs.getTimestamp(2), rs.getInt(3), rs.getInt(4), rs.getString(5));        
        if(delete) {
            st = dbConn.prepareStatement("DELETE FROM \"message\" WHERE id=?");
            st.setInt(1, id);
            st.executeUpdate();            
        }
        return msg;
    }
    
    public void markMessageAsRead(int id) throws SQLException {
        PreparedStatement st = dbConn.prepareStatement("UPDATE \"message\" SET msgread=? WHERE id=?");
        st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        st.setInt(2, id);
        st.executeUpdate();        
    }
}