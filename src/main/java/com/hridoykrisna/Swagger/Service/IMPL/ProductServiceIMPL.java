package com.hridoykrisna.Swagger.Service.IMPL;

import com.hridoykrisna.Swagger.DTO.ProductDTO;
import com.hridoykrisna.Swagger.DTO.Response;
import com.hridoykrisna.Swagger.Model.Product;
import com.hridoykrisna.Swagger.Repository.ProductRepo;
import com.hridoykrisna.Swagger.Service.ProductService;
import com.hridoykrisna.Swagger.Util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceIMPL implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepo productRepo;

    @Override
    public Response save(ProductDTO productDto) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        Product product = modelMapper.map(productDto, Product.class);
        product = productRepo.save(product);

        if (product != null) {
            return ResponseBuilder.getSuccessMessage(HttpStatus.CREATED, "Product Save Successfully", product);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
    }

    @Override
    public Response update(long id, ProductDTO productDto) {
        Optional<Product> productOptional = productRepo.findByIdAndIsActiveTrue(id);
        if (productOptional.isPresent()) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            Product product = modelMapper.map(productDto, Product.class);
            product.setId(id);
            product = productRepo.save(product);
            if (product != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Update Successfully", product);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found");
    }

    @Override
    public Response getDetails(long id) {
        Optional<Product> product = productRepo.findByIdAndIsActiveTrue(id);
        if (product.isPresent()) {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDTO productDTO = modelMapper.map(product.get(), ProductDTO.class);
            if (productDTO != null) {
                return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Retrieve Successfully", productDTO, 1, productDTO.getClass().getDeclaredFields().length);
            }
            return ResponseBuilder.getFailureMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found");
    }

    @Override
    public Response delete(long id) {
        Optional<Product> productOptional = productRepo.findByIdAndIsActiveTrue(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setIsActive(false);
            product = productRepo.save(product);
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Product Delete Successfully", productDTO);
        }
        return ResponseBuilder.getFailureMessage(HttpStatus.NOT_FOUND, "Not Found");
    }

    @Override
    public Response getAllProduct() {
        List<Product> productList = productRepo.findAllByIsActiveTrue();
        List<ProductDTO> productDTOList = getProductDTOList(productList);
        return ResponseBuilder.getSuccessMessage(HttpStatus.OK, "Products Retrieve Successfully", productDTOList, productDTOList.size(), productDTOList.get(0).getClass().getDeclaredFields().length);
    }

    private List<ProductDTO> getProductDTOList(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        productList.forEach(product -> {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
            productDTOList.add(productDTO);
        });
        return productDTOList;
    }
}
