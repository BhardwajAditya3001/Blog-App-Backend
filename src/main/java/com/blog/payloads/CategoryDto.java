package com.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CategoryDto {

    private Integer id;

    @NotEmpty
    @Size(min=4,max=15,message = "Size of the title is minimum 4 and maximum 15")
    private String title;
    @NotEmpty(message = "Description is mandate")
    private String description;
}
