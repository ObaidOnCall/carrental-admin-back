package ma.crm.carental.dtos.violation;

import java.util.Date;

import lombok.Data;


@Data
public class ViolationRequestDto {
    
    private String description ;

    private double finAmount ;

    private Date date ;

    private Boolean isPaid ;

    private long client ;

    private long charge ;
}
