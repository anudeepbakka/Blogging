package com.anudeep.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotEmpty
    @Size(min=4 , message="User name must be more than 4 characters")
    private String categoryTitle;

    @NotEmpty
    private String categoryDes;
}
