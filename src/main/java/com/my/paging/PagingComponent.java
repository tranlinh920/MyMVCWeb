package com.my.paging;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.my.entities.BaseEntity;
import com.my.models.Paging;

import lombok.Data;

@Component
@Data
public class PagingComponent {

	private Pageable pageable;
	private Paging paging;

	public PagingComponent() {
	}

	public PagingComponent(Pageable pageable, Paging paging) {
		this.pageable = pageable;
		this.paging = paging;
	}

	public PagingComponent doPagingAndSort(int page, int limit, int totalItem, int visiblePages, String sortType,
			String sortParam, boolean entityExitField) {
		int totalPages = (int) Math.ceil((double) totalItem / limit);

		if (sortParam != null && (entityExitField || BaseEntity.isExitField(sortParam)))
			if (sortType != null && sortType.toLowerCase().equals("desc"))
				pageable = new PageRequest(page - 1, limit, new Sort(Sort.Direction.DESC, sortParam));
			else
				pageable = new PageRequest(page - 1, limit, new Sort(Sort.Direction.ASC, sortParam));
		else
			pageable = new PageRequest(page - 1, limit);

		paging = new Paging(page, totalPages, totalItem, visiblePages);

		return new PagingComponent(pageable, paging);

	}

}
