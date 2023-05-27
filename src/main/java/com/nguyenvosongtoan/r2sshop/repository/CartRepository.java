package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyenvosongtoan.r2sshop.entity.Cart;

import java.util.List;
import java.util.Optional;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query(value = "SELECT * FROM `cart` WHERE id = ?1;", nativeQuery = true)
    Optional<Cart> getByCartId(Integer id);

    @Query("Select c from Cart c where status = :status and user.id = :userId")
    List<Cart> findCartByUserIdAndStatus(@Param("userId") long userId, @Param("status") String status);

    List<Cart> findAllByUserId(@Param("userId") long userId);
}
