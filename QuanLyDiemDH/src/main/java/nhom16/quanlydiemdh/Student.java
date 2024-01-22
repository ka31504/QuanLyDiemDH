package nhom16.quanlydiemdh;

/**
 *
 * @author khai
 */
import java.io.Serializable;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private byte age;
    private String address;
    private double SumScore;
    private double mon1;
    private double mon2;
    private double mon3;
    private String Khoi;
 
    public Student() {
    }

    public Student(int id, String name, byte age, String address, double SumScore, double mon1, double mon2, double mon3, String Khoi) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.SumScore = SumScore;
        this.mon1 = mon1;
        this.mon2 = mon2;
        this.mon3 = mon3;
        this.Khoi = Khoi;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSumScore() {
        return mon1+mon2+mon3;
    }

    public double getMon1() {
        return mon1;
    }

    public void setMon1(double mon1) {
        this.mon1 = mon1;
    }

    public double getMon2() {
        return mon2;
    }

    public void setMon2(double mon2) {
        this.mon2 = mon2;
    }

    public double getMon3() {
        return mon3;
    }

    public void setMon3(double mon3) {
        this.mon3 = mon3;
    }

    public String getKhoi() {
        return Khoi;
    }

    public void setKhoi(String Khoi) {
        this.Khoi = Khoi;
    }

}