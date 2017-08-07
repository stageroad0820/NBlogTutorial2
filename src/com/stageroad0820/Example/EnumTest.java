package com.stageroad0820.Example;

public enum EnumTest {
	APPLE("apple", true),
	CHOCOLATE("chocolate", false),
	RAMEN("ramen", false),
	RICE("rice", true),
	CORN("corn", true),
	SUGAR_CUBE("sugar_cube", false);
	
	public String foodName;
	public boolean healthy;
	
	private EnumTest(String name, boolean healthy) {
		foodName = name;
		this.healthy = healthy;
	}
	
	public boolean isHealthy() {
		return healthy;
	}
	
	public static EnumTest[] getFoods() {
		EnumTest[] et = {APPLE, CHOCOLATE, RAMEN, RICE, CORN, SUGAR_CUBE};
		
		return et;
	}
	
	public String getString() {
		return foodName;
	}
	
}
