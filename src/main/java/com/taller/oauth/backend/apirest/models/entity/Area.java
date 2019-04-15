package com.taller.oauth.backend.apirest.models.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="areas")
public class Area implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String area;
    private String descripcion;
    private String status;

    @Column(name="create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @PrePersist
    public void prePersist(){
        createAt = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID =1L;
}
