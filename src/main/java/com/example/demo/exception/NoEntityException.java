package com.example.demo.exception;

public class NoEntityException extends Exception{
    private final Integer id;
    private final String name;
    public NoEntityException(Integer id)
    {
        this.id=id;
        this.name="Unknown";
    }

    public NoEntityException(String name)
    {
        this.id=-1;
        this.name=name;
    }

    @Override
    public String getMessage() {
        return "No entity with id "+id+" and name " +name;
    }
}
