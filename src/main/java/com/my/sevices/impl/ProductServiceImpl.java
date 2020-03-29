package com.my.sevices.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.converter.ProductConverter;
import com.my.converter.ProductImageConverter;
import com.my.converter.ProductTypeConverter;
import com.my.dto.ProductDTO;
import com.my.dto.ProductImageDTO;
import com.my.dto.ProductTypeDTO;
import com.my.entities.ProductEntity;
import com.my.entities.ProductImageEntity;
import com.my.entities.ProductTypeEntity;
import com.my.exception.FailedDeleteException;
import com.my.exception.RecordNotFoundException;
import com.my.models.ProductUpload;
import com.my.models.Result;
import com.my.repositories.ProductRepository;
import com.my.repositories.ProductTypeRepository;
import com.my.services.ProductService;
import com.my.sevices.impl.StorageServiceImpl.FileType;
import com.my.sevices.impl.StorageServiceImpl.UploadResult;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductTypeRepository productTypeRepository;

	@Autowired
	private ProductConverter productConverter;

	@Autowired
	private ProductTypeConverter productTypeConverter;

	@Autowired
	private ProductImageConverter productImageConverter;

	@Autowired
	private ProductImageServiceImpl productImageService;

	@Autowired
	private StorageServiceImpl storageService;

	/** Find products with pagination and sort **/
	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findAllByIsActiveTrue(pageable).getContent();
		entities.forEach(entity -> {
			dtos.add(productConverter.toDTO(entity));
		});
		return dtos;
	}

	public List<ProductDTO> findByProPriceAndSort(double proPrice, Direction sortType, String sortFieldName) {

		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
//		List<ProductEntity> entities = (List<ProductEntity>) //
//		productRepository.findByProPriceAndSort(proPrice, new Sort(sortType, sortFieldName));
//
//		entities.forEach(entity -> {
//			dtos.add(productConverter.toDTO(entity));
//		});

		return dtos;
	}

	/** Find the product that is active **/
	@Override
	public ProductDTO findOne(Long proId) {
		ProductEntity entity = productRepository.findByProIdAndIsActive(proId, true);
		if (entity == null)
			throw new RecordNotFoundException("Not found product with id=" + proId);
		return productConverter.toDTO(entity);
	}

	@Override
	public List<ProductDTO> findByProType(ProductTypeDTO typeDTO, Pageable pageable) {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = //
				productRepository.findByProTypeAndIsActiveTrue(productTypeConverter.toEntity(typeDTO), pageable)
						.getContent();

		entities.forEach(entity -> {
			dtos.add(productConverter.toDTO(entity));
		});

		return dtos;
	}

	@Override
	public List<ProductImageDTO> findImages(Long proId) {
		ProductEntity entity = productRepository.findOne(proId);
		if (entity == null)
			throw new RecordNotFoundException("Not found product with id=" + proId);

		List<ProductImageDTO> proImages = new ArrayList<ProductImageDTO>();
		List<ProductImageEntity> proImageEntities = entity.getProImages();
		proImageEntities.forEach(proImageEntity -> {
			proImages.add(productImageConverter.toDto(proImageEntity));
		});

		return proImages;
	}

	@Override
	public Long count() {
		return productRepository.countByIsActiveTrue();
	}

	@Override
	public Long countByProType(String proTypeCode) {
		ProductTypeEntity entity = productTypeRepository.findOneByProTypeCode(proTypeCode);
		return productRepository.countByProTypeAndIsActiveTrue(entity);

	}

	@Override
	public Long countByProNameContaining(String searchString) {
		return productRepository.countByProNameContainingIgnoreCase(searchString);
	}

	@Override
	public ResponseEntity<?> save(ProductDTO dto) {
		ProductTypeEntity productType = productTypeRepository.findByProTypeName(dto.getProType().getProTypeName());
		ProductEntity entity = productConverter.toEntity(dto);
		entity.setProType(productType);
		entity.setCreatedDate(Calendar.getInstance());

		dto = productConverter.toDTO(productRepository.save(entity));
		dto.setCreatedDate(Calendar.getInstance());
		Result<ProductDTO> result = new Result<ProductDTO>(200, dto);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> save(ProductUpload pro, String uploadPath) {
		ProductTypeEntity productType = productTypeRepository.findByProTypeName(pro.getProTypeName());
		ProductEntity entity;
		if (pro.getProId() == null) {
			entity = productConverter.toEntity(pro);
			entity.setProType(productType);
			entity.setCreatedDate(Calendar.getInstance());
			entity.setActive(true);
		} else {
			entity = productRepository.findOne(pro.getProId());
			entity.update(pro);
			entity.setProType(productType);
			entity.setModifiedDate(Calendar.getInstance());
		}
		ProductDTO dto = productConverter.toDTO(productRepository.save(entity));

		// upload file
		UploadResult uploadResult = uploadFile(pro, uploadPath, dto);
		// delete file at edit product function
		deleteFile(pro, uploadPath, entity);

		Result<ProductDTO> result = new Result<ProductDTO>(200, dto, uploadResult.getMassage());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	private UploadResult uploadFile(ProductUpload pro, String uploadPath, ProductDTO dto) {
		UploadResult uploadResult = storageService.store(pro.getProFiles(), FileType.IMAGE, uploadPath);
		if (!uploadResult.getUploadedFiles().isEmpty()) {
			for (File uploadedfile : uploadResult.getUploadedFiles()) {
				ProductImageEntity imageEntity = new ProductImageEntity(//
						uploadedfile.getName(), new ProductEntity(dto.getProId()));
				try {
					productImageService.save(imageEntity);
				} catch (Exception e) {
					storageService.delete(uploadPath, uploadedfile.getName());
				}
			}
		}
		return uploadResult;
	}

	private void deleteFile(ProductUpload pro, String uploadPath, ProductEntity entity) {
		if (pro.getProImages() != null) {
			String[] imgIds = pro.getProImages();
			List<ProductImageEntity> listImg = entity.getProImages();
			for (ProductImageEntity img : listImg) {
				for (String imgId : imgIds) {
					if (img.getProImageId() == Integer.parseInt(imgId)) {
						productImageService.delete(img.getProImageId());
						boolean isDel = storageService.delete(uploadPath, img.getProImageName());
						if (!isDel)
							throw new FailedDeleteException(img.getProImageName());
					}
				}
			}
		}
	}

	@Override
	public void delete(Long proId) {
		ProductEntity entity = productRepository.findOne(proId);
		entity.setActive(false);
		productRepository.save(entity);
	}

	@Override
	public List<ProductDTO> findByProNameContaining(String proName,Pageable pageable) {
		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
		List<ProductEntity> entities = productRepository.findByProNameContainingIgnoreCase(proName,pageable);
		entities.forEach(entity -> {
			dtos.add(productConverter.toDTO(entity));
		});
		return dtos;
	}
}
