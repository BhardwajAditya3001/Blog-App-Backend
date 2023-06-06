package com.blog.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ResourceNotException extends RuntimeException{

    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotException(String resourceName,String fieldName,long fieldValue){
        super(String.format("%s not found with %s : %s" , resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

}
