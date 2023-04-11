package lk.ijse.hostelManagementSystem.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @Column(length = 20)
    private String userId;
    private String name;
    private String userName;
    private String password;
    private String contactNo;

}
