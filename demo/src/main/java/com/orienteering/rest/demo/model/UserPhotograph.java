package com.orienteering.rest.demo.model;

import com.orienteering.rest.demo.model.Photograph;
import com.orienteering.rest.demo.model.User;

import javax.persistence.Entity;

@Entity
public class UserPhotograph extends Photograph<User> {
}
