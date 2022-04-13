package com.example.bootstar.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Data
public class Post {
    @Id
    private int post_id;
    private int author_id;
    private String image;
    private String caption;
    private Timestamp created_at;
    private Timestamp updated_at;
}
