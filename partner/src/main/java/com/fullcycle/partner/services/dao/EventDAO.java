package com.fullcycle.partner.services.dao;

import com.fullcycle.partner.domain.dto.ResponseEventDTO;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    List<ResponseEventDTO> findAll();
    Optional<ResponseEventDTO> findById(String id);
}
