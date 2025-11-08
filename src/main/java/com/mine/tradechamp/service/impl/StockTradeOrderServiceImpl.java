package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.StockTradeOrder;
import com.mine.tradechamp.repo.StockTradeOrderRepository;
import com.mine.tradechamp.service.SharedService;
import com.mine.tradechamp.service.StockTradeOrderService;

@Service
public class StockTradeOrderServiceImpl implements StockTradeOrderService {

	@Autowired
	private StockTradeOrderRepository repo; 

	@Autowired 
	private SharedService sharedService; 
	
	private Logger logger = LoggerFactory.getLogger(StockTradeOrderServiceImpl.class);
	
	@Override
	public StockTradeOrderDto createStockTradeOrder(StockTradeOrderDto dto) {
		StockTradeOrder order = mapToStockTradeOrder(dto); 
		StockTradeOrder savedOrder = repo.save(order); 
		
		return mapToStockTradeOrderDto(savedOrder); 
	}

	@Override
	public StockTradeOrderDto getStockTradeOrderById(Long id) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + id)); 
			
		return mapToStockTradeOrderDto(order);
	}

	@Override
	public List<StockTradeOrderDto> getAllStockTradeOrders() {
		// get a list all dividend pays 
		List<StockTradeOrder> orders = repo.findAll(); 
				
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return orders.stream().map((order) -> mapToStockTradeOrderDto(order))
				.collect(Collectors.toList());
	}

	@Override
	public StockTradeOrderDto updateStockTradeOrder(Long id, StockTradeOrderDto newOrder) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StockTradeOrder doesn't exist with ID : " + id));
		
		order.setStock(sharedService.getStockByStockSymbol(newOrder.getStockSymbol()));
		order.setAccount(sharedService.getAccountByAccountId(newOrder.getAccountId()));
		order.setOrderType(newOrder.getOrderType());
		order.setQuantity(newOrder.getQuantity()); 
		order.setPrice(newOrder.getPrice());
		order.setExecutionDate(newOrder.getExecutionDate());
		order.setComments(newOrder.getComments());
		
		StockTradeOrder updatedOrder = repo.save(order); 
		
		return mapToStockTradeOrderDto(updatedOrder); 
	}

	@Override
	public void deleteStockTradeOrder(Long id) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + id)); 
			
		repo.deleteById(id);  
	}

	public StockTradeOrderDto mapToStockTradeOrderDto(StockTradeOrder entity) {
		
		StockTradeOrderDto newDto =  new StockTradeOrderDto(
				entity.getId(), 
				entity.getAccount().getAccountId(), 
				entity.getStock().getStockSymbol(),
				entity.getOrderType(),
				entity.getQuantity(), 
				entity.getPrice(), 
				entity.getExecutionDate(), 
				entity.getComments()			
		); 
		
		return newDto; 
	}
	
	public StockTradeOrder mapToStockTradeOrder(StockTradeOrderDto dto) {
		StockTradeOrder newOrder =  new StockTradeOrder(
				dto.getId(),  
				sharedService.getAccountByAccountId(dto.getAccountId()),  
				sharedService.getStockByStockSymbol(dto.getStockSymbol()),
				dto.getOrderType(),
				dto.getQuantity(), 
				dto.getPrice(), 
				dto.getExecutionDate(), 
				dto.getComments()
		); 
		
		return newOrder; 
	}
	
}
