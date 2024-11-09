package ma.crm.carental.repositories.interfaces;

import java.util.List;

import ma.crm.carental.entities.Subscription;


public interface SubscriptionInterface {

    List<Subscription> insertSubscriptionInBatch(List<Subscription> subscriptions) ;

    int deleteSubscriptions(List<Long> subscriptionsIds ) ;

    int updateSubscriptionsInBatch(List<Long> subscriptionIds , Subscription subscription) ;

    List<Subscription> subscriptionsWithPagination(int page, int pageSize) ;

    Subscription find(long id) ;

    Long count() ;
}