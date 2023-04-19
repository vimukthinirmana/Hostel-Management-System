package lk.ijse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    @Column(length = 20,name = "student_Id")
    private String sId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contactNo")
    private String contactNo;
    @Column(name = "gender")
    private String gender;
    @Column(name = "age")
    private String age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Reservation> reservationList;


    public Student(String sId, String name, String address, String contactNo, String gender, String age) {
        this.sId = sId;
        this.name = name;
        this.address = address;
        this.contactNo = contactNo;
        this.gender = gender;
        this.age = age;
    }

}
