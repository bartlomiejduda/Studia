/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 *
 * @author mariusz
 */
public class User {
    
    private final String firstName;
    private final String lastName;
    private final String passwordHash;
    private final String state; //bd
    private final java.util.Date last_active_time; //bd
    private final String email;
    
    public User(String firstName, String lastName, String passwordHash, String state, java.util.Date last_active_time) { //bd
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.state = state;
        this.last_active_time = last_active_time;
        this.email = "example@gmail.com";
    }
    public User(String firstName, String lastName, String password, String algName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = makeHash(password, algName);
        this.state = "offline";
        this.last_active_time = new java.util.Date();
        this.email = "example@gmail.com";
    }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPasswordHash() { return passwordHash; }
    public String getState() { return state; }
    public java.util.Date getLastActiveTime() { return last_active_time; }
    public String getMail() { return email; }
    
    public static String makeHash(String plain, String algName) {
        try {
            MessageDigest alg = java.security.MessageDigest.getInstance(algName);
            byte[] array = alg.digest(plain.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {}
        return null;
    }
    
    @Override
    public String toString() { return firstName + " " + lastName; }
}
