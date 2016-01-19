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
