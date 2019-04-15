package com.taller.oauth.backend.apirest.models.repository;

import com.taller.oauth.backend.apirest.models.entity.Area;
import org.springframework.data.repository.CrudRepository;

public interface IAreaRepository extends CrudRepository <Area, Long> {
}
