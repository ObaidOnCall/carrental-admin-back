package ma.crm.carental.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import ma.crm.carental.entities.Reservation;
import ma.crm.carental.repositories.interfaces.ReservationInterface;


@Repository
public class ReservationRepo implements ReservationInterface{


    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private  int batchSize = 10;


    @Override
    public List<Reservation> insertReservationInBatch(List<Reservation> reservations) {
        throw new UnsupportedOperationException("Unimplemented method 'insertReservationInBatch'");
    }

    @Override
    public int deleteReservations(List<Long> reservationsIds) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteReservations'");
    }

    @Override
    public int updateReservationsInBatch(List<Long> reservationIds, Reservation reservation) {
        throw new UnsupportedOperationException("Unimplemented method 'updateReservationsInBatch'");
    }

    @Override
    public List<Reservation> reservationsWithPagination(int page, int pageSize) {
        throw new UnsupportedOperationException("Unimplemented method 'reservationsWithPagination'");
    }

    @Override
    public Reservation find(long id) {
        throw new UnsupportedOperationException("Unimplemented method 'find'");
    }

    @Override
    public Long count() {
        throw new UnsupportedOperationException("Unimplemented method 'count'");
    }
    
}
