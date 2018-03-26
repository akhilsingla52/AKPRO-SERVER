package com.akpro.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * Enum that shows various Status id and description.
 *
 */
public enum StatusEnum {
	ACTIVE(1, "Active"), INACTIVE(2, "InActive"), EXPIRED(3, "Expired");

	private int code;
	private String description;

	StatusEnum(int code, String description) {
		this.code = code;
		this.description = description;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public static StatusEnum getStatusByCode(int code) {
		for (StatusEnum statusEnum : StatusEnum.values()) {
			if (statusEnum.getCode() == code) {
				return statusEnum;
			}
		}
		return null;
	}

	public static StatusEnum getStatusEnumByDesc(String description) {
		for (StatusEnum statusEnum : StatusEnum.values()) {
			if (StringUtils.equalsIgnoreCase(description, statusEnum.getDescription())) {
				return statusEnum;
			}
		}
		return null;
	}
}

