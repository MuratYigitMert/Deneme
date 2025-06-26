package HW3.Deneme.Service;

import HW3.Deneme.Entity.Orders;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrdersService {
    Page<Orders> getAllOrders(Pageable pageable);
    Orders getOrderById(int id);
    Page<Orders> getOrdersByUserId(int userId, Pageable pageable);
    Orders addOrder(User user, Product product, int quantity);
}
