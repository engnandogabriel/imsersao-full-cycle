package com.fullcycle.partner.infra.repository;

import com.fullcycle.partner.domain.entities.ReservationHistory;
import com.fullcycle.partner.infra.models.ReservationHistory.ReservationHistoryJPA;
import com.fullcycle.partner.infra.models.ReservationHistory.ReservationHistoryModel;
import com.fullcycle.partner.infra.models.Spot.SpotModel;
import com.fullcycle.partner.services.repository.ReservationHistoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ReservationHistoryDataBaseRepository implements ReservationHistoryRepository {
    private ReservationHistoryJPA reservationHistoryJPA;

    public ReservationHistoryDataBaseRepository(ReservationHistoryJPA reservationHistoryJPA) {
        this.reservationHistoryJPA = reservationHistoryJPA;
    }

    @Transactional
    @Override
    public void save(ReservationHistory reservationHistory) {
        SpotModel spotModel = new SpotModel(reservationHistory.getSpotId());
        ReservationHistoryModel reservationHistoryModel = new ReservationHistoryModel(reservationHistory.getId(), reservationHistory.getEmail(), reservationHistory.getTicketKind(), reservationHistory.getStatus(), reservationHistory.getCreatedAt(), reservationHistory.getUpdatedAt(), spotModel);
        this.reservationHistoryJPA.save(reservationHistoryModel);
    }
}
