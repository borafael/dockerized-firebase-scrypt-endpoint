package com.frubana.core.services.fscrypt;

public class FScryptParametersPayload {

	private String raw;
	private String key;
	private String salt;
	private String separator;
	private int rounds;
	private int memCost;

	public String getRaw() {
		return raw;
	}

	public String getKey() {
		return key;
	}

	public String getSalt() {
		return salt;
	}

	public String getSeparator() {
		return separator;
	}

	public int getRounds() {
		return rounds;
	}

	public int getMemCost() {
		return memCost;
	}

}
