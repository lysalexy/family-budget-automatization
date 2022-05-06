package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "balance")
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "debit")
    private Double debit;

    @Column(name = "credit")
    private Double credit;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "is_valid")
    private Boolean isValid;

    public Balance(){}

    public Balance(Date createDate, Double debit, Double credit) {
        this.createDate = createDate;
        this.debit = debit;
        this.credit = credit;
        this.amount = debit-credit;
        this.isValid=true;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "ID=" + id +
                ", createDate=" + createDate +
                ", debit=" + debit +
                ", credit=" + credit +
                ", amount=" + amount +
                ", isValid=" + isValid +
                '}';
    }
}
