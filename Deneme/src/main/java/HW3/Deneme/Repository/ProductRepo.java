package HW3.Deneme.Repository;

import HW3.Deneme.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:searchText IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :searchText, '%'))) " +
            "AND (:categoryNames IS NULL OR LOWER(p.category.name) IN :categoryNames)")
    Page<Product> findByCategoryOrProductNames(
            @Param("searchText") String searchText,
            @Param("categoryNames") List<String> categoryNames,
            Pageable pageable);

}
