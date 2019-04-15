package com.taller.oauth.backend.apirest.models.services;

import com.taller.oauth.backend.apirest.models.repository.IAreaRepository;
import com.taller.oauth.backend.apirest.models.entity.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements IAreaService{

    @Autowired
    private IAreaRepository areaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Area> findAll() {
        return (List<Area>) areaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Area findById(Long id){
        return areaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Area save(Area area){
        return areaRepository.save(area);
    }

    @Override
    @Transactional
    public void delete(Long id){
        areaRepository.deleteById(id);
    }


}
