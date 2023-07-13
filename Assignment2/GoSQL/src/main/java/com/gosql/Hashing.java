package com.gosql;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Hashing {
    /**
     * This method hashes password as it contains password
     * @param password user password
     * @return hashed value of user password
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] hash = md.digest();

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                hexString.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
