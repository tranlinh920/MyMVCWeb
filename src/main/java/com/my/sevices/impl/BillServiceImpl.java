package com.my.sevices.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.converter.BillConverter;
import com.my.converter.BillStatusConverter;
import com.my.converter.ProductBillConverter;
import com.my.dto.BillDTO;
import com.my.dto.BillStatusDTO;
import com.my.dto.ProductBillDTO;
import com.my.dto.ProductDTO;
import com.my.dto.UserDTO;
import com.my.entities.BillEntity;
import com.my.entities.BillStatusEnitity;
import com.my.entities.ProductBillEntity;
import com.my.repositories.BillRepository;
import com.my.repositories.BillStatusRepository;
import com.my.services.BillService;
import com.my.services.BillStatusService;
import com.my.services.CustomerService;
import com.my.services.ProductBillService;
import com.my.services.ProductService;
import com.my.services.UserService;
import com.my.utils.SecurityUtil;

@Service
@Transactional
public class BillServiceImpl implements BillService {

	@Autowired
	private BillRepository billRepository;

	@Autowired
	private BillStatusRepository billStatusRepository;

	@Autowired
	private BillConverter billconverter;

	@Autowired
	private BillStatusConverter billStatusConverter;

	@Autowired
	private ProductBillConverter productBillConverter;

	@Autowired
	private BillStatusService billStatusService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductBillService pbService;

	@Override
	public List<BillDTO> findAll(Pageable pageable) {
		List<BillDTO> dtos = new ArrayList<BillDTO>();
		List<BillEntity> entities = billRepository.findByIsActiveTrue(pageable).getContent();
		entities.forEach(entity -> {
			dtos.add(billconverter.toDTO(entity));
		});
		return dtos;

	}

	@Override
	public BillDTO findOne(Long bilId) {
		BillEntity entity = billRepository.findOne(bilId);
		BillDTO dto = billconverter.toDTO(entity);
		return dto;
	}

	@Override
	public List<BillDTO> findByBilStatus(BillStatusDTO bilStaDto, Pageable pageable) {
		List<BillDTO> dtos = new ArrayList<BillDTO>();
		List<BillEntity> entities = billRepository.findAllByBilStatusAndIsActiveTrue(//
				billStatusConverter.toEntity(bilStaDto), pageable).getContent();
		entities.forEach(entity -> {
			dtos.add(billconverter.toDTO(entity));
		});
		return dtos;
	}

	@Override
	public BillDTO save(BillDTO billDto) {

		// thiết lập thông tin sản phẩm
		billDto.getBilProducts().forEach(ele -> {
			ProductDTO proDto = productService.findOne(ele.getPbProduct().getProId());
			ele.setPbProduct(proDto);
		});

		// thiết lập trạng thái đơn hàng
		billDto.setBilStatus(billStatusService.findByBsCode(billDto.getBilStatus().getBsCode()));

		if (SecurityUtil.getUserDetails() == null) {
			// lưu thông tin khách hàng
			billDto.getBilCus().setCreatedDate(Calendar.getInstance());
			billDto.setBilCus(customerService.save(billDto.getBilCus()));
		} else {
			// lưu thông tin người dùng nếu có thay đổi
			String username = SecurityUtil.getUserDetails().getUsername();
			UserDTO userDto = userService.findByUserName(username);
			if (isUserFieldValueChange(billDto, userDto)) {
				userDto = setUserValue(billDto, userDto);
				userDto = userService.save(userDto);
			}
			billDto.setBilUser(userDto);
			// phải set null vì Cus chưa được lưu vào bảng customer
			// nên không lưu vào bảng bill được
			billDto.setBilCus(null);
		}

		// lưu thông tin hóa đơn
		billDto.setCreatedDate(Calendar.getInstance());
		BillEntity billEntity = billRepository.save(billconverter.toEntity(billDto));
		if (billEntity == null)
			throw new RuntimeException("Không thể lưu thông tin hóa đơn");

		// lưu thông tin sản phẩm trong hóa đơn
		ProductBillDTO dtoEle;
		for (ProductBillEntity ele : billEntity.getBilProducts()) {
			;
			ele.setPbBill(new BillEntity(billEntity.getBilId()));
			dtoEle = productBillConverter.toDTO(ele);
			pbService.save(dtoEle);
		}

		return billconverter.toDTO(billEntity);
	}

	/** Kiểm tra thông tin user có thay đổi không **/
	private boolean isUserFieldValueChange(BillDTO billDto, UserDTO userDto) {
		if (!userDto.getUserFullName().equals(billDto.getBilCus().getCusFullName()))
			return true;
		if (!userDto.getUserEmail().equals(billDto.getBilCus().getCusEmail()))
			return true;
		if (userDto.getUserPhoneNumber() != billDto.getBilCus().getCusPhoneNumber())
			return true;
		if (!userDto.getUserAddress().equals(billDto.getBilCus().getCusAddress()))
			return true;
		return false;
	}

	/** Cập nhật lại thông tin user nếu có thay đổi **/
	private UserDTO setUserValue(BillDTO billDto, UserDTO userDto) {
		userDto.setUserFullName(billDto.getBilCus().getCusFullName());
		userDto.setUserEmail(billDto.getBilCus().getCusEmail());
		userDto.setUserPhoneNumber(billDto.getBilCus().getCusPhoneNumber());
		userDto.setUserAddress(billDto.getBilCus().getCusAddress());
		userDto.setModifiedDate(Calendar.getInstance());
		return userDto;
	}

	@Override
	public Long count() {
		return billRepository.countByIsActiveTrue();
	}

	@Override
	public Long countByBilStatus(String bilStaCode) {
		BillStatusEnitity entity = billStatusRepository.findByBsCode(bilStaCode);
		return billRepository.countByBilStatusAndIsActiveTrue(entity);
	}

	@Override
	public BillStatusDTO updateStatus(Long billId, BillDTO dto) {
		BillStatusEnitity bsCode = billStatusRepository.findByBsCode(dto.getBilStatus().getBsCode());
		BillEntity entity = billRepository.findOne(billId);
		entity.setBilStatus(bsCode);
		entity.setModifiedDate(Calendar.getInstance());
		return billStatusConverter.toDTO(entity.getBilStatus());
	}

	@Override
	public void delete(Long proId) {
		BillEntity entity = billRepository.findOne(proId);
		entity.setActive(false);
		billRepository.save(entity);
	}

}
