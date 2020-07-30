package com.orienteering.rest.demo;

public class StatusResponseEntity<T> {

    Boolean status;
    String message;
    T entity;

    public StatusResponseEntity() {
    }

    public StatusResponseEntity(Boolean status, String message, T entity) {
        this.status = status;
        this.message = message;
        this.entity = entity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "StatusResponseEntity{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", entity=" + entity +
                '}';
    }
}
