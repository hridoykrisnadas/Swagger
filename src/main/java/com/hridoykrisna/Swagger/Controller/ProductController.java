package com.hridoykrisna.Swagger.Controller;

import com.hridoykrisna.Swagger.DTO.ProductDTO;
import com.hridoykrisna.Swagger.DTO.Response;
import com.hridoykrisna.Swagger.Service.ProductService;
import com.hridoykrisna.Swagger.Util.ResponseBuilder;
import com.hridoykrisna.Swagger.Util.URLConstraint;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(URLConstraint.ProductManagement.ROOT)
@Tag(name = "Product Controller", description = "To Perform Operation of Products")
public class ProductController {

    private final ProductService productService;

    @Operation(
            summary = "POST Operation of Product",
            description = "It use to be save products to the database"
    )
    @PostMapping(URLConstraint.ProductManagement.CREATE)
    public Response createProduct(@Valid @RequestBody ProductDTO productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return productService.save(productDto);
    }
    @Operation(
            summary = "PUT Operation of Product",
            description = "It use to be update products to the database"
    )
    @PutMapping(URLConstraint.ProductManagement.UPDATE)
    public Response updateProduct(@Valid @PathVariable("id") long id, @RequestBody ProductDTO productDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseBuilder.getFailureMessage(result, "Bean Binding Error");
        }
        return productService.update(id, productDto);
    }

    @Operation(
            summary = "GET Operation of Product",
            description = "It use to be Get Details of products from the database"
    )
    @GetMapping(URLConstraint.ProductManagement.GET_DETAILS)
    public Response updateProduct(@Valid @PathVariable("id") long id) {
        return productService.getDetails(id);
    }

    @Operation(
            summary = "DELETE Operation of Product",
            description = "It use to be Delete product from the database"
    )
    @DeleteMapping(URLConstraint.ProductManagement.DELETE)
    public Response deleteProduct(@Valid @PathVariable("id") long id) {
        return productService.delete(id);
    }

    @Operation(
            summary = "GET Operation of Product",
            description = "It use to be Get All Products from the database"
    )
    @GetMapping(URLConstraint.ProductManagement.GET_ALL)
    public Response getAllProduct() {
        return productService.getAllProduct();
    }



}
