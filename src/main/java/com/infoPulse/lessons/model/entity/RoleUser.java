//package com.infoPulse.lessons.model.entity;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "role_user")
//public class RoleUser {
//
//    // Fields
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//
//
//    @ManyToOne
//    private Role role;
//
//
//    @ManyToOne
//    private User user;
//
//
//    // Constructors
//
//    public RoleUser() {
//    }
//
//
//    // Getters and Setters
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public Role getRole() {
//        return role;
//    }
//
//    public void setRole(Role role) {
//        this.role = role;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
