
/**
 *
 * @author The Back Row
 */

import java.util.*;

public class securityClass {

    private static HashMap<Integer, String> user;
    private static HashMap<Integer, String> pass;
    private static HashMap<String, String> verify;

    public securityClass() {
        user = new HashMap();
        pass = new HashMap();
        verify = new HashMap();

    }

    public String setUser(String x) {
        if (user.containsKey(hashF(x))) {
            return "Username already in system.";

        } else {
            user.put(hashF(x), x);
            return "Successful.";
        }
    }

    public void setPass(String x) {
        pass.put(hashF(x), x);
    }

    public String getUser(String x) {
        return user.get(hashF(x));
    }

    public String getPass(String x) {
        return pass.get(hashF(x));
    }

    public boolean ver(String x, String y) {
        return (user.get(hashF(x)).equals(x)) && (pass.get(hashF(y)).equals(y));

    }

    public int hashF(String x) {
        int hash = 17;

        for (int i = 0; i < x.length(); i++) {
            hash = hash * 13 + x.charAt(i);
        }
        return hash;
    }

}
