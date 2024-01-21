package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StudentLog {
    private static final String STUDENT_FILE_NAME = "student.xml";
    private List<Student> listStudents;
 
    public StudentLog() {
        this.listStudents = readListStudents();
        if (listStudents == null) {
            listStudents = new ArrayList<>();
        }
    }
 
    /**
     * Lưu các đối tượng student vào file student.xml
     * 
     * @param students
     */
    public void writeListStudents(List<Student> students) {
        StudentXML studentXML = new StudentXML();
        studentXML.setStudent(students);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, studentXML);
    }
 
    /**
     * Đọc các đối tượng student từ file student.xml
     * 
     * @return list student
     */
    public List<Student> readListStudents() {
        List<Student> list = new ArrayList<Student>();
        StudentXML studentXML = (StudentXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, StudentXML.class);
        if (studentXML != null) {
            list = studentXML.getStudent();
        }
        return list;
    }
     
 
    /**
     * thêm student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void add(Student student) {
        listStudents.add(student);
        writeListStudents(listStudents);
    }
    
 
    /**
     * cập nhật student vào listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public void edit(Student student) {
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                listStudents.get(i).setId(student.getId());
                listStudents.get(i).setName(student.getName());
                listStudents.get(i).setAge(student.getAge());
                listStudents.get(i).setAddress(student.getAddress());
                listStudents.get(i).setKhoi(student.getKhoi());
                listStudents.get(i).setMon1(student.getMon1());
                listStudents.get(i).setMon2(student.getMon2());
                listStudents.get(i).setMon3(student.getMon3());
                writeListStudents(listStudents);
                break;
            }
        }
    }
 
    /**
     * xóa student từ listStudents và lưu listStudents vào file
     * 
     * @param student
     */
    public boolean delete(Student student) {
        boolean isFound = false;
        int size = listStudents.size();
        for (int i = 0; i < size; i++) {
            if (listStudents.get(i).getId() == student.getId()) {
                student = listStudents.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            listStudents.remove(student);
            writeListStudents(listStudents);
            return true;
        }
        return false;
    }
 
    /**
     * sắp xếp danh sách student theo name theo tứ tự tăng dần
     */
    public void sortStudentByName() {
        Collections.sort(listStudents, (Student student1, Student student2) -> student1.getName().compareTo(student2.getName()));
    }
 
    /**
     * sắp xếp danh sách student theo ID theo tứ tự tăng dần
     */
    public void sortStudentById() {
        Collections.sort(listStudents, (Student student1, Student student2) -> Integer.compare(student1.getId(), student2.getId()));
    }
 
    public List<Student> getListStudents() {
        return listStudents;
    }
 
    public void setListStudents(List<Student> listStudents) {
        this.listStudents = listStudents;
    }
}