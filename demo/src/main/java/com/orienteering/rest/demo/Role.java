package com.orienteering.rest.demo;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

/**
 * Model Role represent User roles
 */
@Entity
public class Role {

    /**
     * Generated Id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    /**
     * Role for authorization
     */
    @Enumerated(EnumType.STRING)
    @NaturalId
    private ERole role;

    /**
     * User join
     */
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "userRoles")
    private List<User> roleUsers;

    /**
     * Default constructor
     */
    public Role(){
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public ERole getERole() {
        return role;
    }

    public void setERole(ERole role) {
        this.role = role;
    }

    public List<User> getRoleUsers() {
        return roleUsers;
    }

    public void setRoleUsers(List<User> roleUsers) {
        this.roleUsers = roleUsers;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", role=" + role +
                '}';
    }
}
