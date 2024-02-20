package com.hridoykrisna.Swagger.Repository;

import com.hridoykrisna.Swagger.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndIsActiveTrue(long id);

    List<Product> findAllByIsActiveTrue();
}
