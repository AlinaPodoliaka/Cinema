package cinema.dto;

import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @NotNull
    private Integer capacity;
    @NotNull
    private String description;

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
