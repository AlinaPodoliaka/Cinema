package cinema.controllers;

import cinema.dto.MovieSessionRequestDto;
import cinema.dto.TicketResponseDto;
import cinema.model.MovieSession;
import cinema.model.ShoppingCart;
import cinema.model.Ticket;
import cinema.model.User;
import cinema.service.CinemaHallService;
import cinema.service.MovieService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/shoppingcarts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieService movieService,
                                  CinemaHallService cinemaHallService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping(value = "/addmoviesession")
    public ShoppingCart addMovieSession(@RequestBody MovieSessionRequestDto movieSessionDto,
                                        @RequestParam Long userId) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.get(movieSessionDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService
                .get(movieSessionDto.getCinemaHallId()));
        LocalDateTime localDateTime = LocalDateTime.parse(movieSessionDto.getShowTime());
        movieSession.setShowTime(localDateTime);
        User user = userService.get(userId);
        shoppingCartService.addSession(movieSession, user);
        return shoppingCartService.getByUser(user);
    }

    @GetMapping(value = "/byuser")
    public List<TicketResponseDto> getByUser(@RequestParam Long userId) {
        return shoppingCartService.getByUser(userService.get(userId)).getTickets().stream()
                .map(ticket -> transferTicketToDto(ticket)).collect(Collectors.toList());
    }

    private TicketResponseDto transferTicketToDto(Ticket ticket) {
        TicketResponseDto ticketDto = new TicketResponseDto();
        ticketDto.setMovieTitle(ticket.getMovie().getTitle());
        ticketDto.setCinemaHallDescription(ticket.getCinemaHall().getDescription());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        return ticketDto;
    }
}
