package HW3.Deneme.Dto;

import lombok.Data;

@Data
public class RoleChangeRequest {
    private int userId;
    private String roleName;
}