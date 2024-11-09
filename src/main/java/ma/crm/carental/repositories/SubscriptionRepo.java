package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import ma.crm.carental.entities.Reservation;
import ma.crm.carental.entities.Subscription;
import ma.crm.carental.repositories.interfaces.SubscriptionInterface;

public class SubscriptionRepo implements SubscriptionInterface{


    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private  int batchSize = 10;


    @PersistenceContext
    private EntityManager em ;


    @Override
    public List<Subscription> insertSubscriptionInBatch(List<Subscription> subscriptions) {
        
        if (subscriptions == null || subscriptions.isEmpty()) {
            return subscriptions ;
        }

        for (int i = 0; i < subscriptions.size(); i++) {
            
            em.persist(subscriptions.get(i));

            if (i > 0 && i % batchSize == 0) {
                em.flush();
                em.clear();
            }
        }

        return subscriptions ;
    }

    @Override
    public int deleteSubscriptions(List<Long> subscriptionsIds) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteSubscriptions'");
    }

    @Override
    public int updateSubscriptionsInBatch(List<Long> subscriptionIds, Subscription subscription) {
        throw new UnsupportedOperationException("Unimplemented method 'updateSubscriptionsInBatch'");
    }

    @Override
    public List<Subscription> subscriptionsWithPagination(int page, int pageSize) {
        throw new UnsupportedOperationException("Unimplemented method 'subscriptionsWithPagination'");
    }

    @Override
    public Subscription find(long id) {

        String hql = "FROM Subscription r WHERE r.id = :id";
            
        return (Subscription) em.createQuery(hql)
                                    .setParameter("id", id)
                                    .getSingleResult() ;
    }

    @Override
    public Long count() {
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    
}
