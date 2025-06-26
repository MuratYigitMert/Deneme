package HW3.Deneme.Controller;


import HW3.Deneme.Dto.OrderRequest;
import HW3.Deneme.Dto.OrderResponse;
import HW3.Deneme.Entity.Orders;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Service.IOrdersService;
import HW3.Deneme.Service.Impl.ProductServiceImpl;
import HW3.Deneme.Service.Impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@EnableSpringDataWebSupport
@RequestMapping("/api/orders")
public class OrderController {
    private final IOrdersService ordersService;
    private final UserServiceImpl userServiceImpl;
    private final ProductServiceImpl productServiceImpl;

    @GetMapping
    public  ResponseEntity<Page<OrderResponse>> getAllOrders(Pageable pageable) {
        Page<Orders> orders = ordersService.getAllOrders(pageable);
        Page<OrderResponse> dtos = orders.map(this::toDto);


        return ResponseEntity.ok(dtos);

    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable int id) {
        return ResponseEntity.ok(toDto(ordersService.getOrderById(id)));
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Orders>> getOrdersByUser(@PathVariable int userId,Pageable pageable) {
        return ResponseEntity.ok(ordersService.getOrdersByUserId(userId,pageable));
    }
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        User user= userServiceImpl.getUserById(request.getUserId());
        Product product= productServiceImpl.getProductById(request.getProductId());
        int quantity= request.getQuantity();
        OrderResponse response= toDto(ordersService.addOrder(user, product, quantity));

        return ResponseEntity.ok(response);
    }

    private OrderResponse toDto(Orders order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setName(order.getName());
        orderResponse.setPrice(order.getPrice());
        orderResponse.setQuantity(order.getQuantity());
        orderResponse.setUserId(order.getUser().getId());

        return orderResponse;
    }

}
