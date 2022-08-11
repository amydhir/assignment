package com.example.assignment.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)// no setter for ID
    @Column(name = "id")
    private long id;

    @Column(name = "quarter")
    private Integer quarter;

    @Column(name = "stock")
    private String stock;

    @Column(name = "date")
    private Date date;

    @Column(name = "open")
    private Double open;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    @Column(name = "close")
    private Double close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "percent_change_price")
    private Double percent_change_price;

    @Column(name = "percent_change_volume_over_last_wk")
    private Double percent_change_volume_over_last_wk;

    @Column(name = "previous_weeks_volume")
    private Long previous_weeks_volume;

    @Column(name = "next_weeks_open")
    private Double next_weeks_open;

    @Column(name = "next_weeks_close")
    private Double next_weeks_close;

    @Column(name = "percent_change_next_weeks_price")
    private Double percent_change_next_weeks_price;

    @Column(name = "days_to_next_dividend")
    private Integer days_to_next_dividend;

    @Column(name = "percent_return_next_dividend")
    private Double percent_return_next_dividend;

}
