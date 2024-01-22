package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StudentController {

    private StudentLog studentLog;
    private StudentView studentView;

    public StudentController(StudentView view) {
        this.studentView = view;
        studentLog = new StudentLog();

        view.addAddStudentListener(new AddStudentListener());
        view.addEdiStudentListener(new EditStudentListener());
        view.addDeleteStudentListener(new DeleteStudentListener());
        view.addClearListener(new ClearStudentListener());
        view.addSortStudentIDListener(new SortStudentIDListener());
        view.addSortStudentNameListener(new SortStudentNameListener());
        view.addListStudentSelectionListener(new ListStudentSelectionListener());
    }

//    public List<Student> searchStudents(String query) {
//        List<Student> foundStudents = new ArrayList<>();
//        List<Student> students = studentLog.getListStudents(); // Lấy danh sách sinh viên từ StudentLog
//
//        for (Student student : students) {
//            if (student.getName().toLowerCase().contains(query.toLowerCase()) || student.getId().toLowerCase().contains(query.toLowerCase())) {
//                foundStudents.add(student);
//            }
//        }
//        return foundStudents;
//    }

    public void showStudentView() {
        List<Student> studentList = studentLog.getListStudents();
        studentView.setVisible(true);
        studentView.showListStudents(studentList);
    }


    /**
     * Lớp AddStudentListener chứa cài đặt cho sự kiện click button "Add"
     */
    class AddStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentLog.add(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentLog.getListStudents());
                studentView.showMessage("Thêm thành công!");
            }
        }
    }

    /**
     * Lớp EditStudentListener chứa cài đặt cho sự kiện click button "Edit"
     *
     * @author viettuts.vn
     */
    class EditStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentLog.edit(student);
                studentView.showStudent(student);
                studentView.showListStudents(studentLog.getListStudents());
                studentView.showMessage("Cập nhật thành công!");
            }
        }
    }

    /**
     * Lớp DeleteStudentListener chứa cài đặt cho sự kiện click button "Delete"
     *
     * @author viettuts.vn
     */
    class DeleteStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Student student = studentView.getStudentInfo();
            if (student != null) {
                studentLog.delete(student);
                studentView.clearStudentInfo();
                studentView.showListStudents(studentLog.getListStudents());
                studentView.showMessage("Xóa thành công!");
            }
        }
    }

    /**
     * Lớp ClearStudentListener chứa cài đặt cho sự kiện click button "Clear"
     *
     *
     */
    class ClearStudentListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            studentView.clearStudentInfo();
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By
     * GPA"
     *
     *
     */
    class SortStudentIDListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            studentLog.sortStudentById();
            studentView.showListStudents(studentLog.getListStudents());
        }
    }

    /**
     * Lớp SortStudentGPAListener chứa cài đặt cho sự kiện click button "Sort By
     * Name"
     *
     *
     */
    class SortStudentNameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            studentLog.sortStudentByName();
            studentView.showListStudents(studentLog.getListStudents());
        }
    }

    /**
     * Lớp ListStudentSelectionListener chứa cài đặt cho sự kiện chọn student
     * trong bảng student
     *
     */
    class ListStudentSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            studentView.fillStudentFromSelectedRow();
        }
    }
}
