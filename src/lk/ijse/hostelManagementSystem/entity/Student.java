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
    @Column(length = 20)
    private String sId;
    private String name;
    private String address;
    private String contactNo;
    private String gender;
    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "student")
    private List<Reservation> reservationList;
}