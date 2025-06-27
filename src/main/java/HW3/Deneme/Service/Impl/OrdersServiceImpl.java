package HW3.Deneme.Service.Impl;

import HW3.Deneme.Entity.Orders;
import HW3.Deneme.Entity.Product;
import HW3.Deneme.Entity.User;
import HW3.Deneme.Exception.ResourceNotFoundException;
import HW3.Deneme.Repository.OrdersRepo;
import HW3.Deneme.Service.IOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements IOrdersService {
    private final OrdersRepo ordersRepo;

    @Override
    public Page<Orders> getAllOrders(Pageable pageable) {
        return ordersRepo.findAll(pageable);
    }

    @Override
    public Orders getOrderById(int id) {
        return ordersRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public Page<Orders> getOrdersByUserId(int userId, Pageable pageable) {
        return ordersRepo.findOrderByUserId(userId, pageable);
    }
    @Transactional
    @Override
    public Orders addOrder(User user, Product product, int quantity) {
        Orders order = new Orders();
        order.setUser(user);
        order.setQuantity(quantity);
        order.setName(product.getName());
        order.setPrice(product.getPrice());
        return ordersRepo.save(order);
    }
}
