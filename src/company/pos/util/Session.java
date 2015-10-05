/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package company.pos.util;

/**
 *
 * @author Muhammad Hanif B
 */
public class Session {
    public static String userLoggedIn;

    public static String getUserLoggedIn() {
        return userLoggedIn;
    }

    public static void setUserLoggedIn(String userLoggedIn) {
        Session.userLoggedIn = userLoggedIn;
    }   
}
