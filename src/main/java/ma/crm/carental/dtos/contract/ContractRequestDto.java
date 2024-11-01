package ma.crm.carental.dtos.contract;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import ma.crm.carental.dtos.interfaces.CreateValidationGroup;
import ma.crm.carental.entities.Client;
import ma.crm.carental.entities.DeliveryGuy;
import ma.crm.carental.entities.Vehicule;

@Data
public class ContractRequestDto {
    

    @NotNull(message = "Contract number is required", groups = CreateValidationGroup.class)
    private Long numContract;

    @NotNull(message = "Start date is required", groups = CreateValidationGroup.class)
    private Date startDate;

    @NotNull(message = "End date is required", groups = CreateValidationGroup.class)
    private Date finDate;

    @Positive(message = "Days must be a positive number", groups = CreateValidationGroup.class)
    private int days;

    @NotBlank(message = "Place of contract is required", groups = CreateValidationGroup.class)
    private String placeOfContract;

    @NotBlank(message = "Place of delivery is required", groups = CreateValidationGroup.class)
    private String placeOfDelivery;

    @NotBlank(message = "Place of return is required", groups = CreateValidationGroup.class)
    private String placeOfReturn;

    @Positive(message = "Price must be positive", groups = CreateValidationGroup.class)
    private double price;

    @Positive(message = "Start mileage must be positive", groups = CreateValidationGroup.class)
    private long startMileage;

    @PositiveOrZero(message = "Caution cannot be negative", groups = CreateValidationGroup.class)
    private int caution;

    @Positive(message = "Total amount must be positive", groups = CreateValidationGroup.class)
    private double totalAmount;

    @PositiveOrZero(message = "Pre-given price cannot be negative", groups = CreateValidationGroup.class)
    private double preGivenPrice;

    @PositiveOrZero(message = "Remaining price cannot be negative")
    private Double remainingPrice;

    private Date dateValideCin;
    private Date dateValideDrivingLicence;

    @PositiveOrZero(message = "Delivery costs cannot be negative")
    private Double deliveryCosts;

    // @NotBlank(message = "Created by field is required", groups = CreateValidationGroup.class)
    // private String createdBy;

    @NotNull(message = "Vehicule is required", groups = CreateValidationGroup.class)
    private long vehicule;

    @NotNull(message = "Client is required", groups = CreateValidationGroup.class)
    private long client;

    @NotNull(message = "Delivery guy is required", groups = CreateValidationGroup.class)
    private long deliveryGuy;
}
