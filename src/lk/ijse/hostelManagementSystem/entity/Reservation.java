package lk.ijse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    @Column(length = 20,name = "res_Id")
    private String resId;
    @Column(name = "date")
    private Date date;
    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_Id",nullable = false)
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_Id",nullable = false)
    private Student student;

    public Reservation(String resId, Date date, String status, Room room, Student student) {
        this.resId = resId;
        this.date = date;
        this.status = status;
        this.room = room;
        this.student = student;
    }


}
