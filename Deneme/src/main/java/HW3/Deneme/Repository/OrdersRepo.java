package HW3.Deneme.Repository;

import HW3.Deneme.Entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o WHERE o.user.id = :userId")
    Page<Orders> findOrderByUserId(int userId, Pageable pageable);
}
