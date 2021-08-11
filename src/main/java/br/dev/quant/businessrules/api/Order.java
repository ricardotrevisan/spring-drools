package br.dev.quant.businessrules.api;

import java.util.ArrayList;

public class Order {

	private String name;
	private String cardType;
	private int discount;
	private int price;
	private ArrayList<Object> list = new ArrayList<Object>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ArrayList<Object> getList() {
		return this.list;
	}

	public void setList(ArrayList<Object> list) {
		this.list = list;
	}


}
