package ma.crm.carental.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class UnableToProccessIteamException extends Exception{
    

    public UnableToProccessIteamException(String message) {
        super(message);  // Pass the message to the superclass (Exception)
    }
    
}
