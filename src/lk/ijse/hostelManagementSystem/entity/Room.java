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
public class Room {
    @Id
    @Column(length = 20)
    private String rId;
    private String type;
    private double keyMoney;
    private int qty;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "room")
    private List<Reservation> reservationList;

}
