package com.mine.tradechamp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mine.tradechamp.dto.DividendPayDto;
import com.mine.tradechamp.service.DividendPayService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController 
@RequestMapping("/api/dividendpays")
public class DividendPayController {

	@Autowired
	private DividendPayService service; 
	
	@PostMapping 
	public ResponseEntity<DividendPayDto> createDividendPay(@RequestBody DividendPayDto dto) {
		
		System.out.println("inside DividendPayController::createDividendPay()"); 
		DividendPayDto savedDividendPayDto = service.createDividendPay(dto); 
		
		return new ResponseEntity<>(savedDividendPayDto, HttpStatus.CREATED); 
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DividendPayDto> getDividendPayById(@PathVariable("id") Long id) { 
		DividendPayDto dividendPayDto = service.getDividendPayById(id); 
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Get all DividendPays 
	@GetMapping
	public ResponseEntity<List<DividendPayDto>> getAllDividendPays() { 
		List<DividendPayDto> dividendPayDtos = service.getAllDividendPayDtos(); 
		return ResponseEntity.ok(dividendPayDtos); 
	}
	
	// Get all DividendPays 
	@PutMapping("{id}")
	public ResponseEntity<DividendPayDto> updateDividendPays(@PathVariable("id") Long id, DividendPayDto newDividendPayDto) { 
		DividendPayDto dividendPayDto = service.updateDividendPay(id, newDividendPayDto);  
		return ResponseEntity.ok(dividendPayDto); 
	}
	
	// Delete  
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteDividendPays(@PathVariable("id") Long id) { 
		service.deleteDividendPay(id);  
		return ResponseEntity.ok("DividendPay record deleted successfully"); 
	}
	
}
