package com.orienteering.rest.demo.model;

import javax.persistence.Entity;
/**
 * Child class for Photograph, relationship with Event
 */
@Entity
public class EventPhotograph extends Photograph<Event> {
}
