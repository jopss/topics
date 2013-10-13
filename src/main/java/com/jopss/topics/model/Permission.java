package com.jopss.topics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jopss.topics.util.Repository;
import javax.persistence.TableGenerator;

@Entity
public class Permission extends Repository {

    private static final long serialVersionUID = -2224862055167038787L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generatorName")
    @TableGenerator(name = "generatorName", allocationSize = 1)
    private Long id;

    @NotEmpty
    @NotNull
    private String role;
    
    @NotNull
    private Boolean active;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}

