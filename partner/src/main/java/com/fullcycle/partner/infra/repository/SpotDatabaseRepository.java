package com.fullcycle.partner.infra.repository;

import com.fullcycle.partner.domain.entities.Spot;
import com.fullcycle.partner.infra.models.Event.EventModel;
import com.fullcycle.partner.infra.models.Spot.SpotJPA;
import com.fullcycle.partner.infra.models.Spot.SpotModel;
import com.fullcycle.partner.services.repository.SpotRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class SpotDatabaseRepository implements SpotRepository {
    private SpotJPA spotJPA;

    public SpotDatabaseRepository(SpotJPA spotJPA) {
        this.spotJPA = spotJPA;
    }
    @Transactional
    @Override
    public void save(Spot spot) {
        EventModel eventModel = new EventModel(spot.getEventId());
        SpotModel spotModel = new SpotModel(spot.getId(), spot.getName(), spot.getStatus(), spot.getCreatedAt(), spot.getUpdatedAt(), eventModel);
        this.spotJPA.save(spotModel);
    }
    @Transactional
    @Override
    public Optional<Spot> getByNameAndEventId(String name, String eventId) {
        SpotModel spotModel = this.spotJPA.getByNameAndEventId(name, eventId).orElse(null);
        if (spotModel == null) return Optional.empty();
        Spot spot = Spot.restore(spotModel.getId(), spotModel.getName(), spotModel.getStatus(), spotModel.getCreatedAt(), spotModel.getUpdatedAt(), spotModel.getEvent().getId());
        return Optional.of(spot);
    }
}
