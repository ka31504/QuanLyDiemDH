package nhom16.quanlydiemdh;

/**
 *
 * @author luong dep chai
 */
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class StudentView extends JFrame implements ActionListener, ListSelectionListener {

    private StudentLog studentLog;

    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentIDBtn;
    private JButton sortStudentNameBtn;
    private JButton ThongKeBtn;
    private JButton TimKiemBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JScrollPane jScrollPaneAddress;
    private JTable studentTable;
    private JComboBox<String> khoiComboBox;

    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel khoiLabel;
    private JLabel SumLabel;
    private JLabel mon1Label;
    private JLabel mon2Label;
    private JLabel mon3Label;

    private JLabel imageLabel;
    //private JTextArea ChuThich;

    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;

    private JTextField SumField;
    private JTextField mon1Field;
    private JTextField mon2Field;
    private JTextField mon3Field;
    private JTextField searchTextField;
    private JFrame searchFrame; // Cửa sổ tìm kiếm
    private JTextField searchTextfield; // Trường nhập liệu tìm kiếm
    private JButton searchBtn; // Nút thực hiện tìm kiếm

    // định nghĩa các cột của bảng student
    private String[] columnNames = new String[]{
        "ID", "Tên", "Tuổi", "Địa chỉ", "Khối", "Môn 1", "Môn 2", "Môn 3", "Tổng"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private final Object data = new Object[][]{};

    public StudentView() {
        initComponents();
        this.studentLog = new StudentLog();
    }

    private JTextArea resultTextArea; // Thêm vào đầu class

    private JTable resultTable; // Bảng kết quả tìm kiếm
    private DefaultTableModel tableModel; // Model cho bảng kết quả
    
    private void performStatistics() {
        
        if (studentLog == null) {
            // Xử lý trường hợp studentLog là null
            JOptionPane.showMessageDialog(this, "Lỗi: Không thể truy cập dữ liệu sinh viên.");
            return;
        }

        List<Student> students = studentLog.getListStudents(); // Lấy danh sách sinh viên
        if (students == null || students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu sinh viên.");
            return;
        }

        double maxScore = Double.MIN_VALUE;
        double minScore = Double.MAX_VALUE;
        double totalScore = 0;
        for (Student student : students) {
            double sumScore = student.getSumScore();
            totalScore += sumScore;
            if (sumScore > maxScore) {
                maxScore = sumScore;
            }
            if (sumScore < minScore) {
                minScore = sumScore;
            }
        }

        double averageScore = totalScore / students.size();

        // Hiển thị thông tin thống kê
        String message = "Tổng số thí sinh: " + students.size()
                + "\nTổng điểm thi trung bình: " + averageScore
                + "\nĐiểm cao nhất: " + maxScore
                + "\nĐiểm thấp nhất: " + minScore;
        JOptionPane.showMessageDialog(this, message);
    }
    
    
    private void openSearchDialog() {
        studentLog = new StudentLog();
        searchFrame = new JFrame("Search Student");
        searchFrame.setSize(500, 500);
        searchFrame.setLayout(new FlowLayout());

        searchTextfield = new JTextField(20);
        searchFrame.add(searchTextfield);

        searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSearch();
            }
        });
        searchFrame.add(searchBtn);

        // Khởi tạo tableModel và thiết lập các tiêu đề cột
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"ID", "Tên", "Tuổi", "Địa chỉ", "Khối", "Môn 1", "Môn 2", "Môn 3", "Tổng"});

        // Khởi tạo resultTable với tableModel
        resultTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(resultTable); // Cho phép cuộn
        scrollPane.setPreferredSize(new Dimension(450, 250)); // Đặt kích thước
        searchFrame.add(scrollPane); // Thêm vào JFrame

        searchFrame.setLocationRelativeTo(null);
        searchFrame.setVisible(true);
    }
    
    private void performSearch() {
    String searchText = searchTextfield.getText().trim().toLowerCase();
    System.out.println("Searching : " + searchText + " ...");

    // Lấy danh sách sinh viên
    List<Student> students = studentLog.getListStudents();
    List<Student> foundStudents = new ArrayList<>();
    
    boolean exactMatchFound = false;
    tableModel.setRowCount(0);

    // Tìm kiếm sinh viên
    int dynamicThreshold = Math.max(2, searchText.length() / 2); // Ví dụ về ngưỡng động
    List<Pair<Student, Integer>> closeMatches = new ArrayList<>(); // Lưu trữ cặp (Sinh viên, Khoảng cách)

    for (Student student : students) {
        String studentNameLower = student.getName().toLowerCase();
        if (studentNameLower.equals(searchText)) {
            // ... xử lý trận đấu chính xác ...
        } else if (!exactMatchFound) {
            if (studentNameLower.contains(searchText)) {
                closeMatches.add(new Pair<>(student, 0)); // 0 để chỉ ra đây là trận đấu chuỗi con
            } else {
                // Tính Khoảng cách Levenshtein cho trận đấu gần đúng
                int distance = LevenshteinDistance.computeDistance(studentNameLower, searchText);
                if (distance <= dynamicThreshold) {
                    closeMatches.add(new Pair<>(student, distance));
                }
            }
        }
    }

    // Sắp xếp danh sách closeMatches dựa trên khoảng cách
    Collections.sort(closeMatches, Comparator.comparingInt(Pair::getSecond));

    // Thêm các trận đấu gần đúng vào mô hình bảng
    for (Pair<Student, Integer> match : closeMatches) {
        addStudentToTableModel(match.getFirst());
    }

    if (foundStudents.isEmpty() && !exactMatchFound) {
        System.out.println("Không tìm thấy sinh viên nào.");
    } else {
        System.out.println("Đã tìm thấy " + foundStudents.size() + " sinh viên:");
    }
}
    
    private void updateSubjectsBasedOnBlock() {
        String selectedBlock = (String) khoiComboBox.getSelectedItem();
        switch (selectedBlock) {
            case "A":
                mon1Label.setText("Toán");
                mon2Label.setText("Lý");
                mon3Label.setText("Hóa");
                break;
            case "A1":
                mon1Label.setText("Toán");
                mon2Label.setText("Lý");
                mon3Label.setText("Anh");
                break;
            case "B":
                mon1Label.setText("Toán");
                mon2Label.setText("Hóa");
                mon3Label.setText("Sinh");
                break;
            case "C":
                mon1Label.setText("Văn");
                mon2Label.setText("Sử");
                mon3Label.setText("Địa");   
                break;
            case "D":
                mon1Label.setText("Toán");
                mon2Label.setText("Văn");
                mon3Label.setText("Anh");
                break;
        }
    }

// Phương thức hỗ trợ để thêm sinh viên vào table model
private void addStudentToTableModel(Student student) {
    tableModel.addRow(new Object[]{
        student.getId(),
        student.getName(),
        student.getAge(),
        student.getAddress(),
        student.getKhoi(),
        student.getMon1(),
        student.getMon2(),
        student.getMon3(),
        student.getSumScore()
    });
}


    

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        editStudentBtn = new JButton("Sửa");
        deleteStudentBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");
        sortStudentIDBtn = new JButton("Sắp xếp theo ID");
        sortStudentNameBtn = new JButton("Sắp xếp theo Tên");
        ThongKeBtn = new JButton("Thống Kê");
        TimKiemBtn = new JButton("Tìm Kiếm");

        String[] khoiOptions = {"A", "A1", "B" , "C" , "D"};
        khoiComboBox = new JComboBox<>(khoiOptions);
        khoiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSubjectsBasedOnBlock();
            }
        });

        
        TimKiemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSearchDialog();
            }
        });
        
        ThongKeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performStatistics();
            }
        });

        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();

        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Tên");
        ageLabel = new JLabel("Tuổi");
        addressLabel = new JLabel("Địa chỉ");
        khoiLabel = new JLabel("Khối");
        mon1Label = new JLabel("Môn 1");
        mon2Label = new JLabel("Môn 2");
        mon3Label = new JLabel("Môn 3");
        SumLabel = new JLabel("Tổng");

//        ChuThich = new JTextArea("Thứ tự các môn lần lượt theo khối:\n"
//                + "A00:Toán, Lý, Hóa.\n"
//                + "A01:Toán, Lý, Anh.\n"
//                + "B00:Toán, , Sinh.\n"
//                + "C00:Văn, Sử, Địa.");

        // khởi tạo các trường nhập dữ liệu cho student
        idField = new JTextField(4);
        nameField = new JTextField(15);
        ageField = new JTextField(4);
        addressTA = new JTextArea();
        addressTA.setColumns(10);
        addressTA.setRows(1);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);

        mon1Field = new JTextField(5);
        mon2Field = new JTextField(5);
        mon3Field = new JTextField(5);

        SumField = new JTextField(5);
        SumField.setEditable(false);

        imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("img\\pka.png");
        Image image = imageIcon.getImage().getScaledInstance(324, 84, Image.SCALE_DEFAULT);
        ImageIcon scaledIcon = new ImageIcon(image);
        imageLabel.setIcon(scaledIcon);

        // cài đặt các cột và data cho bảng student
        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);

        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentIDBtn);
        panel.add(sortStudentNameBtn);
        panel.add(ThongKeBtn);
        panel.add(TimKiemBtn);

        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(khoiLabel);
        panel.add(khoiComboBox);
        panel.add(mon1Label);
        panel.add(mon2Label);
        panel.add(mon3Label);
        panel.add(SumLabel);
        panel.add(imageLabel);
        //panel.add(ChuThich);

        panel.add(idField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);

        panel.add(mon1Field);
        panel.add(mon2Field);
        panel.add(mon3Field);
        panel.add(SumField);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, khoiLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, khoiLabel, 130, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon1Label, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon1Label, 160, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon2Label, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon2Label, 190, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon3Label, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon3Label, 220, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SumLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SumLabel, 250, SpringLayout.NORTH, panel);

//        layout.putConstraint(SpringLayout.WEST, ChuThich, 10, SpringLayout.WEST, panel);
//        layout.putConstraint(SpringLayout.NORTH, ChuThich, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, khoiComboBox, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, khoiComboBox, 130, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon1Field, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon1Field, 160, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon2Field, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon2Field, 190, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, mon3Field, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, mon3Field, 220, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, SumField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, SumField, 250, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 10, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 12, SpringLayout.EAST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 14, SpringLayout.EAST, editStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, clearBtn, 16, SpringLayout.EAST, deleteStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 280, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sortStudentIDBtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortStudentIDBtn, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, 5, SpringLayout.EAST, sortStudentIDBtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, TimKiemBtn, 10, SpringLayout.EAST, sortStudentNameBtn);
        layout.putConstraint(SpringLayout.NORTH, TimKiemBtn, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, ThongKeBtn, 10, SpringLayout.EAST, TimKiemBtn);
        layout.putConstraint(SpringLayout.NORTH, ThongKeBtn, 330, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, imageLabel, 380, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, imageLabel, 380, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(850, 500);
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
        

    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list student vào bảng studentTable
     *
     * @param list
     */
    public void showListStudents(List<Student> list) {
        int size = list.size();
        // với bảng studentTable có 9 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 9
        Object[][] students = new Object[size][9];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getAge();
            students[i][3] = list.get(i).getAddress();
            students[i][4] = list.get(i).getKhoi();
            students[i][5] = list.get(i).getMon1();
            students[i][6] = list.get(i).getMon2();
            students[i][7] = list.get(i).getMon3();
            students[i][8] = list.get(i).getSumScore();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));

    }

    /**
     * điền thông tin của hàng được chọn từ bảng student vào các trường tương
     * ứng của student.
     */
    public void fillStudentFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(studentTable.getModel().getValueAt(row, 0).toString());
            idField.setEditable(true);
            nameField.setText(studentTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(studentTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(studentTable.getModel().getValueAt(row, 3).toString());
            mon1Field.setText(studentTable.getModel().getValueAt(row, 5).toString());
            mon2Field.setText(studentTable.getModel().getValueAt(row, 6).toString());
            mon3Field.setText(studentTable.getModel().getValueAt(row, 7).toString());
            SumField.setText(studentTable.getModel().getValueAt(row, 8).toString());
            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);
            // disable Add button
            addStudentBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin student
     */
    public void clearStudentInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        
        mon1Field.setText("");
        mon2Field.setText("");
        mon3Field.setText("");
        SumField.setText("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }

    /**
     * hiện thị thông tin student
     *
     * @param student
     */
    public void showStudent(Student student) {
        idField.setText("" + student.getId());
        nameField.setText(student.getName());
        ageField.setText("" + student.getAge());
        addressTA.setText(student.getAddress());
        
        mon1Field.setText("" + student.getMon1());
        mon2Field.setText("" + student.getMon2());
        mon3Field.setText("" + student.getMon3());
        SumField.setText("" + student.getSumScore());
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);
    }

    /**
     * lấy thông tin student
     *
     * @return
     */
    public Student getStudentInfo() {
        // validate student
        if (!validateName() || !validateAge() || !validateAddress() || !validateMon1() || !validateMon2() || !validateMon3() || !validateKhoi()) {
            return null;
        }
        try {
            Student student = new Student();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                student.setId(Integer.parseInt(idField.getText()));
            }
            student.setName(nameField.getText().trim());
            student.setAge(Byte.parseByte(ageField.getText().trim()));
            student.setAddress(addressTA.getText().trim());
            student.setKhoi(khoiComboBox.getSelectedItem().toString());
            student.setMon1(Double.parseDouble(mon1Field.getText().trim()));
            student.setMon2(Double.parseDouble(mon2Field.getText().trim()));
            student.setMon3(Double.parseDouble(mon3Field.getText().trim()));
            //student.setSumScore(Double.parseDouble(SumField.getText().trim()));
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Name không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateAddress() {
        String address = addressTA.getText();
        if (address == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Address không được trống.");
            return false;
        }
        return true;
    }

    private boolean validateAge() {
        try {
            Byte age = Byte.parseByte(ageField.getText().trim());
            if (age < 0 || age > 100) {
                ageField.requestFocus();
                showMessage("Age không hợp lệ, age nên trong khoảng 0 đến 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Age không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateMon1() {
        try {
            Double Toan = Double.parseDouble(mon1Field.getText().trim());
            if (Toan < 0 || Toan > 10) {
                mon1Field.requestFocus();
                showMessage("Điểm không hợp lệ! ên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            mon1Field.requestFocus();
            showMessage("Toán không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateMon2() {
        try {
            Double Van = Double.parseDouble(mon2Field.getText().trim());
            if (Van < 0 || Van > 10) {
                mon2Field.requestFocus();
                showMessage("Điểm không hợp lệ! Nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            mon2Field.requestFocus();
            showMessage("Điểm không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateMon3() {
        try {
            Double Anh = Double.parseDouble(mon3Field.getText().trim());
            if (Anh < 0 || Anh > 10) {
                mon3Field.requestFocus();
                showMessage("Điểm không hợp lệ! Nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            mon3Field.requestFocus();
            showMessage("Điểm không hợp lệ!");
            return false;
        }
        return true;
    }

    private boolean validateKhoi() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Khối không được trống.");
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     *
     * @param e
     */
    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }

    public void addEdiStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }

    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addSortStudentIDListener(ActionListener listener) {
        sortStudentIDBtn.addActionListener(listener);
    }

    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }

    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }
}