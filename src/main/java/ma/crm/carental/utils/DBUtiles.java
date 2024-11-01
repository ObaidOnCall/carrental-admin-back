package ma.crm.carental.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DBUtiles {
    



    public static <T> Map<String, Object> convertToMap(T entity) {
        Map<String, Object> map = new HashMap<>();
    
        // Get declared fields and filter out Hibernate internal fields
        Arrays.stream(entity.getClass().getDeclaredFields())
            .filter(field -> !field.getName().startsWith("$$_"))  // Exclude Hibernate internal fields
            .forEach(field -> {
                field.setAccessible(true);
                try {
                    Object value = field.get(entity);
                    // Add to map only if the value is not null or 0 (for numeric types)
                    if (value != null && !(value instanceof Number && ((Number) value).intValue() == 0)) {
                        map.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    // Handle exception or log
                    e.printStackTrace();
                }
            });
    
        return map;
    }
}
