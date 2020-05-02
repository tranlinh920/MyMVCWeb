package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.converter.PageConverter;
import com.my.dto.PageDTO;
import com.my.entities.PageEntity;
import com.my.repositories.PageRepository;
import com.my.services.PageService;

@Service
public class PageServiceImpl implements PageService {

	@Autowired
	private PageRepository pageRepository;

	@Autowired
	private PageConverter pageConverter;

	@Override
	public List<PageDTO> findAll() {
		List<PageDTO> dtos = new ArrayList<PageDTO>();
		List<PageEntity> entities = pageRepository.findAllByIsActiveTrue();
		entities.forEach(entity -> {
			dtos.add(pageConverter.toDTO(entity));
		});
		return dtos;
	}

	@Override
	public PageDTO findOne(Long pagId) {
		PageEntity entity = pageRepository.findOne(pagId);
		return pageConverter.toDTO(entity);
	}

	@Override
	public PageDTO findByCode(String pageCode) {
		PageEntity entity = pageRepository.findByPagCodeAndIsActiveTrue(pageCode);
		return pageConverter.toDTO(entity);
	}

	@Override
	public PageDTO save(PageEntity entity) {
		PageDTO dto = null;
		if (entity.getPagId() == null) {
			dto = pageConverter.toDTO(pageRepository.save(entity));
		} else {
			PageEntity oldEntity = pageRepository.findOne(entity.getPagId());
			oldEntity.setPagName(entity.getPagName());
			oldEntity.setPagCode(entity.getPagCode());
			oldEntity.setPagContent(entity.getPagContent());
			dto = pageConverter.toDTO(pageRepository.save(oldEntity));
		}
		return dto;
	}

	@Override
	public boolean delete(Long proId) {
		boolean result = false;
		try {
			PageEntity oldEntity = pageRepository.findOne(proId);
			result = oldEntity != null ? true : false;
			oldEntity.setActive(false);
			pageRepository.save(oldEntity);
			return result;
		} catch (Exception e) {
			return result;
		}
	}

}
