package lk.ijse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Reservation {
    @Id
    @Column(length = 20)
    private String resId;
    private Date date;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

}
