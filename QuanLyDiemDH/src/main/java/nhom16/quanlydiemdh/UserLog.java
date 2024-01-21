package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */


public class UserLog {
    public boolean checkUser(User user) {
        if (user != null) {
            if ("admin".equals(user.getUserName()) 
                    && "admin".equals(user.getPassword())) {
                return true;
            }
            if ("".equals(user.getUserName()) 
                    && "".equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }
    
}
