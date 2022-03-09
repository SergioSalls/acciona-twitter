package com.acciona.twitter.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TwitterEntity {

    @Id
    private Long id;

    private String name;

    private String location;

    private String text;

    private Boolean isVerified;


}
