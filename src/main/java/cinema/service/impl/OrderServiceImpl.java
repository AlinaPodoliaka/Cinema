package cinema.service.impl;

import cinema.dao.OrderDao;
import cinema.model.Order;
import cinema.model.ShoppingCart;
import cinema.model.User;
import cinema.service.OrderService;
import cinema.service.ShoppingCartService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao ordersDao;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @Override
    public Order completeOrder(User user) {
        Order order = new Order();
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        order.setUser(user);
        order.setTickets(shoppingCart.getTickets());
        order.setOrderDate(LocalDateTime.now());
        shoppingCartService.clear(shoppingCart);
        return ordersDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return ordersDao.getOrderHistory(user);
    }

    @Override
    public List<Order> getAll() {
        return ordersDao.getAll();
    }
}
