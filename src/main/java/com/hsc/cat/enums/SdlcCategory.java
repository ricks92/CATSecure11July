package com.hsc.cat.enums;

//New added class
public enum SdlcCategory {
	REQUIREMENT_GATHERING(1, "Requirement Gathering and Analysis"), DESIGN(2, "Design"), CODING(3,
			"Coding"), TESTING(4, "Testing"),INVALID(5,"Invalid");
	int value;
	String name;

	private SdlcCategory(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public static String getSdlcCategoryName(int value) {
		SdlcCategory[] array=SdlcCategory.values();
		String name="Invalid";
		for(int i=0;i<array.length;i++) {
			int v=array[i].value;
			if(v==value)
				name=array[i].name;
		}
		
		return name;
	}
	
	
	public static int getCategoryFromName(String name) {
		SdlcCategory[] array=SdlcCategory.values();
		
		int value=5;
		for(int i=0;i<array.length;i++) {
			String text=array[i].name;
			if(text.equalsIgnoreCase(name))
				value=array[i].value;
		}
		
		return value;
		
	}
}
