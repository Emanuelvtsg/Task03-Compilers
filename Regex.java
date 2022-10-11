package com.company;

public class Regex {
	
	private static final String	numRegex = "(\\d)+";
	private static final String opRegex = "(\\+|-|\\*|/)";
	private static final String plusRegex = "(\\+)";
	private static final String minusRegex = "(\\-)";
	private static final String slashRegex = "(/)";
	private static final String starRegex = "(\\*)";

	// ao inves de passar o token, passarei a linha do arquivo que o programa está lendo no momento, pois fiz o codigo
	// de forma diferente da mostrada em sala, ao meu ver não causa nenhum problema
	public static boolean isNum(String linhaFile) {
		return linhaFile.matches(numRegex);
	}

	public static boolean isOP(String linhaFile) {
		return linhaFile.matches(opRegex);
	}

	public static boolean isPlus(String linhaFile) {
		return linhaFile.matches(plusRegex);
	}

	public static boolean isMinus(String linhaFile) {
		return linhaFile.matches(minusRegex);
	}
	public static boolean isSlash(String linhaFile) {
		return linhaFile.matches(slashRegex);
	}
	public static boolean isStar(String linhaFile) {
		return linhaFile.matches(starRegex);
	}

	public static TokenType returnTokenOPType(String linhaFile) {
		TokenType tokenType = null;
		if (isPlus(linhaFile)){
			tokenType = TokenType.PLUS;
		}
		else if (isMinus(linhaFile)){
			tokenType = TokenType.MINUS;
		}
		else if (isSlash(linhaFile)){
			tokenType = TokenType.SLASH;
		}
		else if (isStar(linhaFile)){
			tokenType = TokenType.STAR;
		}
		return tokenType;
	}
}
