package com.my.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.my.entities.ProductEntity;
import com.my.entities.ProductTypeEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	public Page<ProductEntity> findByIsActiveTrue(Pageable pageable);

	public ProductEntity findByProIdAndIsActive(Long id, boolean active);

	public Long countByIsActiveTrue();

	public Page<ProductEntity> findByProTypeAndIsActiveTrue(ProductTypeEntity entity, Pageable pageable);

//	@Query(value = "SELECT P.* FROM PRODUCTS P " + //
//			"JOIN PRODUCTTYPE PT ON P.PRO_ID = PT.PRO_ID " + //
//			"WHERE P.PROTYPE_ID = :proTypeId", nativeQuery = true)
//	public List<ProductEntity> findWithProductType(@Param("proTypeId") Long proTypeId);
//
//	@Query(value = "select p from ProductEntity p join ProductTypeEntity pt")
//	public List<ProductEntity> findAllByMe();

//	@Query("select p from ProductEntity p")
//	Page<List<ProductEntity>> findAllAndSort(Pageable pageable, Sort sort);

//	@Query("select p from ProductEntity p where p.proPrice = ?1")
//	List<ProductEntity> findByProPriceAndSort(double proPrice, Sort sort);

//	List<ProductEntity> findByAgeOrderByLastnameDesc();

}
