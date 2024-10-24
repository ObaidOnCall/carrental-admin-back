package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import ma.crm.carental.entities.Violation;
import ma.crm.carental.repositories.interfaces.ViolationRepoInterface;


@Slf4j
@Repository
public class ViolationRepo implements ViolationRepoInterface{

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private  int batchSize = 10;

    @PersistenceContext
    private EntityManager em ;

    @Override
    public List<Violation> insertViolationInBatch(List<Violation> violations) {
        log.debug("batch size is : {} 🔖\n" , batchSize);

        if (violations == null || violations.isEmpty()) {
            return violations ;
        }

        for (int i = 0; i < violations.size(); i++) {
            
            em.persist(violations.get(i));

            if (i > 0 && i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }
        
        return violations ;
    }

    @Override
    public int deleteViolations(List<Long> violationsIds) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteViolations'");
    }

    @Override
    public int updateViolationsInBatch(List<Long> violationsIds, Violation violation) {
        throw new UnsupportedOperationException("Unimplemented method 'updateViolationsInBatch'");
    }

    @Override
    public List<Violation> volationsWithPagination(int page, int pageSize) {
        throw new UnsupportedOperationException("Unimplemented method 'volationsWithPagination'");
    }

    @Override
    public Violation find(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }
    
}
