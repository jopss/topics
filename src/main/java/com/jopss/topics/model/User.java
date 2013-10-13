package com.jopss.topics.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.jopss.topics.util.Repository;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "topic_user") //para diferenciar da tabela "user" existente internamente no banco de dados.
public class User extends Repository {

    private static final long serialVersionUID = -1114862055167038787L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "generatorName")
    @TableGenerator(name = "generatorName", allocationSize = 1)
    private Long id;

    @NotEmpty
    @NotNull
    private String username;
    
    @NotEmpty
    @NotNull
    private String password;
    
    @NotNull
    private Boolean active;
    
    @ManyToOne
    private Permission permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    
}

