package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer ID;

    @Column(name = "name")
    private String name;

    @Column(name ="is_valid")
    private Boolean isValid;

    public Article(){}

    public Article(String name) {
        this.name = name;
        this.isValid=true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Article{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", isValid=" + isValid +
                '}';
    }
}

