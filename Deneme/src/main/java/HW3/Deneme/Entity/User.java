package HW3.Deneme.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String username;
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL )
    private List<Orders> orders;
}
