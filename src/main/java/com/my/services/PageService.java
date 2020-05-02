package com.my.services;

import java.util.List;

import com.my.dto.PageDTO;
import com.my.entities.PageEntity;

public interface PageService {

	public List<PageDTO> findAll();

	public PageDTO findOne(Long pagId);

	public PageDTO findByCode(String pageCode);

	public PageDTO save(PageEntity entity);

	public boolean delete(Long proId);

}
