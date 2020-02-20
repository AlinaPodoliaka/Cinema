package cinema.controllers;

import cinema.dto.CinemaHallRequestDto;
import cinema.dto.CinemaHallResponseDto;
import cinema.model.CinemaHall;
import cinema.service.CinemaHallService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping(value = "/add")
    public CinemaHall add(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHall.getCapacity());
        cinemaHall.setDescription(cinemaHall.getDescription());
        return cinemaHallService.add(cinemaHall);
    }

    @GetMapping(value = "/all")
    public List<CinemaHallResponseDto> getAllMovies() {
        return cinemaHallService.getAll().stream()
                .map(cinemaHall -> getCinemaHallDto(cinemaHall))
                .collect(Collectors.toList());
    }

    private CinemaHallResponseDto getCinemaHallDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallDto = new CinemaHallResponseDto();
        cinemaHallDto.setDescription(cinemaHall.getDescription());
        cinemaHallDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallDto;
    }
}
