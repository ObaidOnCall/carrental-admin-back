package ma.crm.carental.dtos.violation;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class ViolationRequestDto {
    
    @Size(max = 255, message = "Description can be up to 255 characters.")
    private String description;

    @PositiveOrZero(message = "Fine amount must be zero or positive.")
    private double finAmount;

    @PastOrPresent(message = "Date must be in the past or present.")
    private Date date;

    @NotNull(message = "Payment status is required.")
    private Boolean isPaid;

    @NotNull(message = "Client ID is required.")
    private Long client;

    // @NotNull(message = "Charge ID is required.")
    private Long charge;
}
