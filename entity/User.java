package am.itspace.taskmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(name = "name")
    private String name;
//    @Transient
    private String surname;
    private String email;
    @Enumerated(value = EnumType.STRING)
    private Role role;
    private String picUrl;
    private String password;

}
