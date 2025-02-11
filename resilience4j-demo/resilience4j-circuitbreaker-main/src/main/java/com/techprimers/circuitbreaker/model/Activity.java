package com.techprimers.circuitbreaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/* sample message
    {
        "activity": "Start a family tree",
            "type": "social",
            "participants": 1,
            "price": 0,
            "link": "https://en.wikipedia.org/wiki/Family_tree",
            "key": "6825484",
            "accessibility": 1
    }*/
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Activity {
    private String activity;
    private String type;
    private String link;
    private String key;
    private Integer participants;
    private Double price;
    private Double accessibility;
}
