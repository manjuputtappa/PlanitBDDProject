package webinteractivies;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import com.github.javafaker.Faker;

import engine.IWebActions;

public class RandomDataGenerator {

	public static enum valueType{
		FIRSTNAME,
		LASTNAME,
		EMAIL,
		MOBILEPHONE,
		RANDOMSTRING,
		RANDOMNUMBER
	}
	
	private static final char[] letters = "abcdefghijklnmopqrstuvwxyz".toCharArray();
	private static final char[] numbers = "1234567890".toCharArray();
	private static Map<String, String> storeValues = new LinkedHashMap<>();
	
	public static String generate(valueType genValueFor) {
		Faker faker = new Faker(Locale.ENGLISH);
		switch(genValueFor) {
		case FIRSTNAME:
			storeValues.put(valueType.FIRSTNAME.toString(), faker.firstName());
			break;
		case LASTNAME:
			storeValues.put(valueType.LASTNAME.toString(), faker.lastName());
			break;
		case MOBILEPHONE:
			storeValues.put(valueType.MOBILEPHONE.toString(), getRandomMobile());
			break;
		case EMAIL:
			storeValues.put(valueType.EMAIL.toString(), getRandomEmail());
			break;
		case RANDOMSTRING:
			storeValues.put(valueType.RANDOMSTRING.toString(), getRandomString(5));
			break;
		}
		return storeValues.get(genValueFor.toString());
	}
	
	public static String generate(valueType genValueFor, String storeGeneratedValueInIdentifier) {
		switch(genValueFor) {
		case RANDOMSTRING:
			storeValues.put(storeGeneratedValueInIdentifier, getRandomString(5));
			break;
		case RANDOMNUMBER:
			storeValues.put(storeGeneratedValueInIdentifier, getRandomNumber(5));
			break;
		}
		return storeValues.get(storeGeneratedValueInIdentifier);
	}
	
	public static String generate(valueType genValueFor, int length, String storeGeneratedValueInIdentifier) {
		switch(genValueFor) {
		case RANDOMSTRING:
			storeValues.put(storeGeneratedValueInIdentifier, getRandomString(length));
			break;
		case RANDOMNUMBER:
			storeValues.put(storeGeneratedValueInIdentifier, getRandomNumber(length));
			break;
		}
		return storeValues.get(storeGeneratedValueInIdentifier);
	}

	private static String getRandomEmail() {
		return getRandomString(5) + "@gmail.com";
	}

	private static String getRandomString(int length) {
		return getRandom(length, letters);
	}

	private static String getRandomMobile() {
		return "04" + getRandomNumber(8);
	}

	private static String getRandomNumber(int length) {
		return getRandom(length, numbers);
	}

	private static String getRandom(int length, char[] chars) {
		StringBuilder sb= new StringBuilder();
		Random random=new Random();
		for(int i=0; i<length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
	
	public static void captureWebelementValueAndSave(String captureFrom, String saveUnderName) {
		storeValues.put(saveUnderName, IWebActions.validate.getTextOf(captureFrom));
	}
	
	public static void captureValueAndSave(String captureValue, String saveUnderName) {
		storeValues.put(saveUnderName, captureValue);
	}
	
	public static String fetchGeneratevalueOf(valueType key) {
		return storeValues.get(key.toString());
	}
}
