package cinema.controllers;

import cinema.dto.TicketResponseDto;
import cinema.dto.UserResponseDto;
import cinema.model.Order;
import cinema.model.Ticket;
import cinema.service.OrderService;
import cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping(value = "/complete")
    public Order completeOrder(@RequestBody UserResponseDto userResponseDto) {
        return orderService.completeOrder(userService.findByEmail(userResponseDto.getEmail()));
    }

    @GetMapping
    public List<TicketResponseDto> getAllOrders() {
        return orderService.getAll().stream()
                .flatMap(order -> order.getTickets().stream())
                .map(this::getTicketsDto)
                .collect(Collectors.toList());
    }

    private TicketResponseDto getTicketsDto(Ticket ticket) {
        TicketResponseDto ticketDto = new TicketResponseDto();
        ticketDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketDto.setCinemaHallDescription(ticket.getCinemaHall().getDescription());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        return ticketDto;
    }
}
