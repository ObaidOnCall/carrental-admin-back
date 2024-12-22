package ma.crm.carental.dtos.docs;

import lombok.Data;
import ma.crm.carental.dtos.interfaces.ClientIdentifiable;

@Data
public class MetaData implements ClientIdentifiable{

    private Long client;
    
}
