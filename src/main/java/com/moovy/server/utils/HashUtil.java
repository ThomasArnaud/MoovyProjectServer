package com.moovy.server.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */

public class HashUtil
{
    public static String sha256(String password) {
        try
        {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            byte[] rawSecurityDigest = sha256.digest(password.getBytes(StandardCharsets.UTF_8));

            StringBuilder digestBuilder = new StringBuilder();

            for(byte b : rawSecurityDigest)
            {
                digestBuilder.append(String.format("%02x", b & 0xff));
            }

            return digestBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
