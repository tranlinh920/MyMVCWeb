package com.my.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.my.dto.ProductDTO;
import com.my.dto.ProductImageDTO;
import com.my.dto.ProductTypeDTO;
import com.my.models.ProductUpload;

public interface ProductService {

	public List<ProductDTO> findAll(Pageable pageable);

	public ProductDTO findOne(Long proId);

	public List<ProductImageDTO> findImages(Long proId);

	public Long count();

	public Long countByProType(String proTypeCode);

	public ResponseEntity<?> save(ProductDTO dto);

	public ResponseEntity<?> save(ProductUpload pro, String uploadPath);

	public void delete(Long proId);

	public List<ProductDTO> findByProType(ProductTypeDTO typeDTO, Pageable pageable);

	public Long countByProNameContaining(String searchString);

	public List<ProductDTO> findByProNameContaining(String searchString, Pageable pageable);

}
