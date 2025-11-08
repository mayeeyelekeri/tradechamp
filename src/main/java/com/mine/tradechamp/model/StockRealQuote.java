package com.mine.tradechamp.model;

public class StockRealQuote { 
	
	String symbol;
	double price; 	
	double change;
	double volume;
	
	public StockRealQuote(String symbol, double price, double change, double volume) {
		super();
		this.symbol = symbol;
		this.price = price;
		this.change = change;
		this.volume = volume;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getChange() {
		return change;
	}
	public void setChange(double change) {
		this.change = change;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "StockRealQuote [symbol=" + symbol + ", price=" + price + ", change=" + change + ", volume=" + volume
				+ "]";
	} 
	
}
