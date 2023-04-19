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
    @Column(length = 20,name = "room_Id")
    private String rId;
    @Column(name = "type")
    private String type;
    @Column(name = "keyMoney")
    private String keyMoney;
    @Column(name = "qty")
    private int qty;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "room")
    private List<Reservation> reservationList;

    public Room(String rId, String type,String keyMoney, int qty ) {
        this.rId = rId;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }


}
