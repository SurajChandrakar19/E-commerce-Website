package com.sastaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sastaa.model.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	public void deleteByCartIdAndProductId(Long cartId, Long productId);

	public CartItem findByCartIdAndProductId(Long cartId, Long productId);

	public List<CartItem> findByCartId(Long cartId);
	
}
