package ma.crm.carental.dtos.deliveryguy;

import java.util.Date;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ma.crm.carental.dtos.interfaces.validationgroups.CreateValidationGroup;

@Data
public class DeliveryGuyRequestDto{
    
    @NotBlank(message = "Firstname is required", groups = CreateValidationGroup.class)
    @Size(min = 2, max = 40, message = "Firstname must be between 2 and 40 characters" )
    @Pattern(regexp = "^[A-Za-z]+$", message = "Firstname must only contain alphabetic characters" )
    private String firstname;

    @NotBlank(message = "LastName is required", groups = CreateValidationGroup.class)
    @Size(min = 2, max = 30, message = "LastName must be between 2 and 30 characters")
    @Pattern(regexp = "^[A-Za-z]+$", message = "LastName must only contain alphabetic characters")
    private String lastname;

    @NotBlank(message = "Cin/Passport is required", groups = CreateValidationGroup.class)
    @Size(min = 8, max = 20, message = "Cin/Passport must be between 8 and 20 characters")
    private String cinOrPassport;

    @NotBlank(message = "Licence is required", groups = CreateValidationGroup.class)
    @Size(min = 4, max = 20, message = "Licence must be between 4 and 20 characters")
    private String licence;

    @Size(min = 4, max = 40, message = "Nationality must be between 4 and 40 characters")
    private String nationality;

    @Size(min = 8, max = 50, message = "Address must be between 8 and 50 characters")
    private String address;

    @Size(max = 50, message = "City name should not exceed 50 characters")
    private String ville;

    private int codePostal;

    @Pattern(regexp = "^\\+\\d{1,15}$", message = "Phone number must start with + and should not exceed 15 digits")
    private String phone1;

    @Pattern(regexp = "^\\+\\d{1,15}$", message = "Alternate phone number must start with + and should not exceed 15 digits")
    private String phone2;

    @Email(message = "Please provide a valid email address")
    private String email;

    private Date cinIsValideUntil;
    private Date licenceIsValideUntil;
}
