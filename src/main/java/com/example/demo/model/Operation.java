package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer ID;

    @ManyToOne
    @JoinColumn(name="article_id", referencedColumnName = "id")
    private Article article;

    @Column(name="debit")
    private Double debit;

    @Column(name="credit")
    private Double credit;


    @Column(name="create_date")
    private Date createDate;

    @Override
    public String toString() {
        return "Operation{" +
                "ID=" + ID +
                ", article=" + article +
                ", debit=" + debit +
                ", credit=" + credit +
                ", createDate=" + createDate +
                ", balance=" + balance +
                '}';
    }

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
}
