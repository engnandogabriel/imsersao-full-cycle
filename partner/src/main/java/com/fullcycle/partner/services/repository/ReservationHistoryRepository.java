package com.fullcycle.partner.services.repository;

import com.fullcycle.partner.domain.entities.ReservationHistory;

public interface ReservationHistoryRepository {
    void save(ReservationHistory reservationHistory);
}
