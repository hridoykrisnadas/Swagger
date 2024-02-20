package com.hridoykrisna.Swagger.Service;

import com.hridoykrisna.Swagger.DTO.ProductDTO;
import com.hridoykrisna.Swagger.DTO.Response;
import jakarta.validation.Valid;

public interface ProductService {
    Response save(ProductDTO productDto);

    Response update(long id, ProductDTO productDto);

    Response getDetails(long id);

    Response delete(long id);

    Response getAllProduct();
}
