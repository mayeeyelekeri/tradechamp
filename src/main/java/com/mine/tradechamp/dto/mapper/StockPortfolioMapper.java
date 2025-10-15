package com.mine.tradechamp.dto.mapper;

import com.mine.tradechamp.dto.StockPortfolioDto;
import com.mine.tradechamp.model.StockPortfolio;

public class StockPortfolioMapper {

	public static StockPortfolioDto mapToStockPortfolioDto(StockPortfolio stock) {
		return new StockPortfolioDto(
				stock.getId(), 
				stock.getAccountId(), 
				stock.getStockSymbol(),
				stock.getStockName(),
				stock.getProductType(), 
				stock.getStockQuantity(), 
				stock.getCurrentStockPrice(), 
				stock.getAverageStockPrice(), 
				stock.getCurrentMarketCap(), 
				stock.getOriginalInvestment(), 
				stock.getDrip(), 
				stock.getPurchaseDate(), 
				stock.getDividendFrequency(),
				stock.getComments(), 
				stock.getTotalDividendAmount(),
				stock.getPlCurrent(),
				stock.getPlWithDividend(), 
				stock.getCurrentYield()
		); 
	}
	
	public static StockPortfolio mapToStockPortfolio(StockPortfolioDto stock) {
		return new StockPortfolio(
				stock.getId(), 
				stock.getAccountId(), 
				stock.getStockSymbol(),
				stock.getStockName(),
				stock.getProductType(), 
				stock.getStockQuantity(), 
				stock.getCurrentStockPrice(), 
				stock.getAverageStockPrice(), 
				stock.getCurrentMarketCap(), 
				stock.getOriginalInvestment(), 
				stock.getDrip(), 
				stock.getPurchaseDate(), 
				stock.getDividendFrequency(),
				stock.getComments(),
				stock.getTotalDividendAmount(),
				stock.getPlCurrent(),
				stock.getPlWithDividend(), 
				stock.getCurrentYield()
		); 
	}
}
