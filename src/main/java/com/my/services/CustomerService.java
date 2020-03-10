package com.my.services;

import java.util.List;

import com.my.dto.CustomerDTO;

public interface CustomerService {

	public List<CustomerDTO> findAll();

	public CustomerDTO save(CustomerDTO bilCus);

}
