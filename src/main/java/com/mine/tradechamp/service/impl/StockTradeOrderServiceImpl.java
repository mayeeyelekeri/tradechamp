package com.mine.tradechamp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mine.tradechamp.dto.StockTradeOrderDto;
import com.mine.tradechamp.dto.mapper.DividendAnnouncementMapper;
import com.mine.tradechamp.dto.mapper.StockTradeOrderMapper;
import com.mine.tradechamp.exception.ResourceNotFoundException;
import com.mine.tradechamp.model.DividendAnnouncement;
import com.mine.tradechamp.model.StockTradeOrder;
import com.mine.tradechamp.repo.DividendAnnouncementRepository;
import com.mine.tradechamp.repo.StockTradeOrderRepository;
import com.mine.tradechamp.service.StockTradeOrderService;

@Service
public class StockTradeOrderServiceImpl implements StockTradeOrderService {

	@Autowired
	private StockTradeOrderRepository repo; 

	@Override
	public StockTradeOrderDto createStockTradeOrder(StockTradeOrderDto dto) {
		StockTradeOrder order = StockTradeOrderMapper.mapToStockTradeOrder(dto); 
		StockTradeOrder savedOrder = repo.save(order); 
		
		return StockTradeOrderMapper.mapToStockTradeOrderDto(savedOrder); 
	}

	@Override
	public StockTradeOrderDto getStockTradeOrderById(Long id) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + id)); 
			
		return StockTradeOrderMapper.mapToStockTradeOrderDto(order);
	}

	@Override
	public List<StockTradeOrderDto> getAllStockTradeOrders() {
		// get a list all dividend pays 
		List<StockTradeOrder> orders = repo.findAll(); 
				
		// convert all DividendPay records list to DividendPay List using Lambda expression  
		return orders.stream().map((order) -> StockTradeOrderMapper.mapToStockTradeOrderDto(order))
				.collect(Collectors.toList());
	}

	@Override
	public StockTradeOrderDto updateStockTradeOrder(Long id, StockTradeOrderDto newOrder) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StockTradeOrder doesn't exist with ID : " + id));
		
		order.setStockSymbol(newOrder.getStockSymbol());
		order.setAccountId(newOrder.getAccountId());
		order.setOrderType(newOrder.getOrderType());
		order.setQuantity(newOrder.getQuantity()); 
		order.setPrice(newOrder.getPrice());
		order.setExecutionDate(newOrder.getExecutionDate());
		order.setComments(newOrder.getComments());
		
		StockTradeOrder updatedOrder = repo.save(order); 
		
		return StockTradeOrderMapper.mapToStockTradeOrderDto(updatedOrder); 
	}

	@Override
	public void deleteStockTradeOrder(Long id) {
		StockTradeOrder order = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dividend Announcement doesn't exist with ID : " + id)); 
			
		repo.deleteById(id);  
	}

}
