package com.orienteering.rest.demo.model;

import javax.persistence.*;

/**
 * Child class for Photograph, relationship with Control
 */
@Entity
public class ControlPhotograph extends Photograph<Control> {

}
