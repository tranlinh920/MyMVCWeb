package com.my.sevices.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.converter.ProductImageConverter;
import com.my.dto.ProductImageDTO;
import com.my.entities.ProductImageEntity;
import com.my.exception.RecordNotFoundException;
import com.my.repositories.ProductImageRepository;

@Service
@Transactional
public class ProductImageServiceImpl {

	@Autowired
	ProductImageRepository productImageRepository;

	@Autowired
	ProductImageConverter productImageConverter;

	public ProductImageDTO findOne(Long imgId) {
		ProductImageEntity image = productImageRepository.findByProImageIdAndIsActive(imgId, true);
		if (image == null)
			throw new RecordNotFoundException("Not found images with id=" + imgId);
		return productImageConverter.toDto(image);
	}

	public ProductImageDTO save(ProductImageEntity entity) {
		entity.setActive(true);
		ProductImageEntity image = productImageRepository.save(entity);
		return productImageConverter.toDto(image);
	}

	public void delete(Long imgId) {
		productImageRepository.delete(imgId);
	}
}
