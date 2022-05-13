package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id")
    private Article article;

    @Column(name="debit")
    private Double debit;

    @Column(name="credit")
    private Double credit;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name="balance_id", referencedColumnName = "id")
    private Balance balance;

    public Operation(Article article, Double debit, Double credit, Date createDate, Balance balance) {
        this.article= article;
        this.debit=debit;
        this.credit=credit;
        this.createDate=createDate;
        this.balance=balance;
    }

    public Operation() {
    }

    public Double getDebit() {
        return debit;
    }

    public void setDebit(Double debit) {
        this.debit = debit;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Balance getBalance() {
        return balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
