package com.sp.store.dao.repository;

import com.sp.store.dao.entity.ProductEntity;
import com.sp.store.dto.CategoryProductCountDto;
import com.sp.store.dto.NameAndPriceDto;
import com.sp.store.dao.projection.ProductDiscountProjection;
import com.sp.store.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    /**
     * Finds products with prices between the specified range and orders them by name.
     */
    @Query(value = "SELECT * FROM product WHERE price BETWEEN :price1 AND :price2 ORDER BY name", nativeQuery = true)
    List<ProductEntity> findByPriceBetweenOrderByName(@Param("price1") Double price1, @Param("price2") Double price2);

    /**
     * Finds products whose names contain the specified substring.
     */
    @Query(value = "SELECT * FROM product WHERE \"name\" LIKE CONCAT('%', :nameContains, '%')", nativeQuery = true)
    List<ProductEntity> findByNameContaining(@Param("nameContains") String nameContains);

    /**
     * Calculates the total sum of quantities for all products.
     */
    @Query(value = "SELECT SUM(quantity) FROM product", nativeQuery = true)
    Integer sumQuantity();

    /**
     * Finds the product with the maximum price.
     */
    @Query(value = "SELECT * FROM product WHERE price = (SELECT MAX(price) from product)", nativeQuery = true)
    ProductEntity findWithMaxPrice();

    /**
     * Fetches the 10 most recently added products, sorted by the creation date in descending order.s
     */
    @Query(value = "SELECT * FROM product ORDER BY created_at DESC LIMIT 10", nativeQuery = true)
    List<ProductEntity> findTop10RecentByCreatedAtDesc();

    /**
     * Calculates the average price of all products.
     */
    @Query(value = "SELECT AVG(price) FROM product", nativeQuery = true)
    Integer findAveragePrice();

    /**
     * Groups products by category and counts the number of products in each category.
     */
    @Query(value = "SELECT category.name, COUNT(product.id) as product_count FROM category " +
            "RIGHT JOIN product ON category.id = product.category_id GROUP BY category.name", nativeQuery = true)
    List<CategoryProductCountDto> countByCategory();

    /**
     * Retrieves only the product names and prices from the table.
     */
    @Query(value = "SELECT new com.sp.store.dto.NameAndPriceDto(p.name, p.price) FROM ProductEntity p")
    List<NameAndPriceDto> findNamesAndPrices();

    /**
     * Finds products added on a specific creation date.
     */
    @Query(value = "SELECT * FROM product WHERE created_at = :createdAt", nativeQuery = true)
    List<ProductEntity> findByCreatedAt(@Param("createdAt") LocalDateTime createdAt);

    /**
     * Retrieves distinct product names along with their prices, quantities abd categories.
     */
    @Query(value = "SELECT DISTINCT ON (p.name) p.name, p.price, p.quantity, p.category_id" +
            " FROM product p" +
            " ORDER BY p.name, p.id", nativeQuery = true)
    List<ProductDto> findDistinctNames();

    /**
     * Finds products whose names contain a specific word and have a price greater than the specified value.
     */
    @Query(value = "SELECT * FROM product WHERE name LIKE CONCAT('%', :name, '%' ) AND price > :price", nativeQuery = true)
    List<ProductEntity> findByNameAndPriceGreaterThan(@Param("name") String name, @Param("price") Double price);

    /**
     * Performs a full join between the product and discount tables to retrieve product and discount details.
     */
    @Query(value = "SELECT product.name, product.price, product.quantity, discount.start_date, discount.discount_percentage, discount.end_date" +
            " FROM product FULL JOIN discount ON product.id = discount.product_id", nativeQuery = true)
    List<ProductDiscountProjection> findWithDiscountDetails();
}
