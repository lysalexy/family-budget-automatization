package com.example.demo.web;

import java.io.Serializable;

public class ArticleWeb implements Serializable{
    private final String name;


    public ArticleWeb(String name,String age) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
