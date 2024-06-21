package com.elcorkum.post_api.entity;


import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "POST_ID")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Post post;
}