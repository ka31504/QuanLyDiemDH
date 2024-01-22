package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 
public class LoginController {
    private UserLog userLog;
    private LoginView loginView;
    private StudentView studentView;
     
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userLog = new UserLog();
        view.addLoginListener(new LoginListener());
    }
     
    public void showLoginView() {
        loginView.setVisible(true);
    }
     
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userLog.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                studentView = new StudentView();
                StudentController studentController = new StudentController(studentView);
                studentController.showStudentView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
