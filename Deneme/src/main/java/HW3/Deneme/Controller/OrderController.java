package HW3.Deneme.Controller;


import HW3.Deneme.Dto.OrderRequest;
import HW3.Deneme.Entity.Orders;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.OrdersService;
import HW3.Deneme.Service.ProductService;
import HW3.Deneme.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@EnableSpringDataWebSupport
@RequestMapping("/api/orders")
public class OrderController {
    private final OrdersService ordersService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public Page<Orders> getAllOrders(Pageable pageable) {
        return ordersService.getAllOrders(pageable);
    }
    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable int id) {
        return ordersService.getOrderById(id);
    }
    @GetMapping("/user/{userId}")
    public List<Orders> getOrdersByUser(@PathVariable int userId) {
        return ordersService.getOrdersByUserId(userId);
    }
    @PostMapping
    public Orders createOrder(@RequestBody OrderRequest request) {
        User user= userService.getUserById(request.getUserId());
        Product product= productService.getProductById(request.getProductId());
        int quantity= request.getQuantity();

        return ordersService.addOrder(user, product, quantity);
    }

}
