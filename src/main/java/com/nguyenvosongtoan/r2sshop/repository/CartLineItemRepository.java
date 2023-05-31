package com.nguyenvosongtoan.r2sshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nguyenvosongtoan.r2sshop.entity.CartLineItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartLineItemRepository extends JpaRepository<CartLineItem, Long> {
	@Query("SELECT c FROM CartLineItem c WHERE cart.id = :cartId")
	List<CartLineItem> findAllByCartId(@Param("cartId") long cartId);

	Optional<CartLineItem> findByCartIdAndVariantProductId(long cartId, long variantProductId);
}
