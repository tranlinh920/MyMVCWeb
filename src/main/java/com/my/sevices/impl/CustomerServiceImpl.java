package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.converter.CustomerConverter;
import com.my.dto.CustomerDTO;
import com.my.entities.CustomerEntity;
import com.my.repositories.CustomerRepository;
import com.my.services.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CustomerConverter customerConverter;

	@Override
	public List<CustomerDTO> findAll() {
		List<CustomerDTO> dtos = new ArrayList<CustomerDTO>();
		List<CustomerEntity> entities = customerRepository.findAll();
		entities.forEach(entity -> {
			dtos.add(customerConverter.toDTO(entity));
		});
		return dtos;
	}

	@Override
	public CustomerDTO save(CustomerDTO dto) {
		CustomerEntity entity = customerRepository.save(customerConverter.toEntity(dto));
		if (entity == null)
			throw new RuntimeException("Không thể save customer entity");
		return customerConverter.toDTO(entity);
	}

}
