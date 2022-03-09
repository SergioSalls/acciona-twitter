package com.acciona.twitter.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ignite.cache.query.annotations.QuerySqlField;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Twitter implements Serializable {

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String name;

    @QuerySqlField
    private String location;

    @QuerySqlField
    private String text;

    @QuerySqlField
    private Boolean isVerified;

}
