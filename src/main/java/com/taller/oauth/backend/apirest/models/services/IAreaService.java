package com.taller.oauth.backend.apirest.models.services;

import com.taller.oauth.backend.apirest.models.entity.Area;

import java.util.List;

public interface IAreaService {
    public List<Area> findAll();

    public Area findById(Long id);

    public Area save (Area area);

    public void delete (Long id);

}
