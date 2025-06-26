package HW3.Deneme.Dto;

import lombok.Data;

@Data
public class UserResponse {
    private int id;
    private String username;
    private String password;
    private int roleId;
    private String email;
}
