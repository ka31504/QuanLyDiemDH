package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */
import java.awt.EventQueue;
 
public class QuanLyDiemDH {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            
            }
        });
    }
}
