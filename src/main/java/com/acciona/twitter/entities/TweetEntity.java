package com.acciona.twitter.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TweetEntity {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private TwitterUserEntity user;

    @Column(name = "location")
    private String location;

    @Column(name = "text")
    private String text;

    @Column(name = "is_validated")
    private Boolean isValidated = false;


}
