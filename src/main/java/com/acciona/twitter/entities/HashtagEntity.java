package com.acciona.twitter.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashtagEntity {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Integer count;
}
