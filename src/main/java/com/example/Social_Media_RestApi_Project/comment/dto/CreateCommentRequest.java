package com.example.Social_Media_RestApi_Project.comment.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
public class CreateCommentRequest {
    @Length(min = 1, max = 1000)
    private String content;
}
