package com.taller.oauth.backend.apirest.controllers;

import com.taller.oauth.backend.apirest.models.entity.Area;
import com.taller.oauth.backend.apirest.models.services.IAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class AreaRestController {

    @Autowired
    private IAreaService areaService;

    @GetMapping("/areas")
    public List<Area> index(){
        return areaService.findAll();
    }

    @GetMapping("/areas/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {

        Area area =null;
        Map <String, Object> response = new HashMap<>();

        try {
            area =areaService.findById(id);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar la consulta");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (area==null){
            response.put("mensaje", "El Area ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Area>(area, HttpStatus.OK);
    }

    @PostMapping("/areas")
    public ResponseEntity<?> create(@RequestBody Area area){

        Area areaNew= null;
        Map<String ,Object> response = new HashMap<>();

        try{
            areaNew= areaService.save(area);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al realizar el registro en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "El area ha sido creada con exito");
        response.put("area", areaNew);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
    }

    @PutMapping("/areas/{id}")
    public ResponseEntity<?> update(@RequestBody Area area, @PathVariable Long id) {

        Area areaActual = areaService.findById(id);

        Area areaUpdated = null;

        Map<String ,Object> response = new HashMap<>();

        if (areaActual==null){
            response.put("mensaje", "El Area ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }

        try{

            areaActual.setArea(area.getArea());
            areaActual.setDescripcion(area.getDescripcion());
            areaActual.setStatus(area.getStatus());

            areaUpdated=areaService.save(areaActual);

        }catch (DataAccessException e){
            response.put("mensaje", "Error al actualizar registros en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }


        response.put("mensaje", "El area ha sido actualizada con exito");
        response.put("area", areaUpdated);
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }

    @DeleteMapping("/areas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Map<String ,Object> response = new HashMap<>();

        try{
            areaService.delete(id);
        }catch (DataAccessException e){
            response.put("mensaje", "Error al elkiminar el cliente de  la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }



        response.put("mensaje", "El area ha sido borrada con exito");
        return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);

    }


}
