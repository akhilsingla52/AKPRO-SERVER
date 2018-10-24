package com.akpro.enums;

/**
 * Enum that shows various categories id and description.
 * 
 */
public enum UserRoleEnum {
	ADMIN(1, "ADMIN"), PARTNER(2, "PARTNER");

	private int userCode;
	private String userDescription;

	UserRoleEnum(int userCode, String userDescription) {
		this.userCode = userCode;
		this.userDescription = userDescription;
	}

	/**
	 * @return the userCode
	 */
	public int getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode
	 *            the userCode to set
	 */
	public void setUserCode(int userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the userDescription
	 */
	public String getUserDescription() {
		return userDescription;
	}

	/**
	 * @param userDescription
	 *            the userDescription to set
	 */
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public static UserRoleEnum getUserRoleByCode(int userRoleCode) {
		for (UserRoleEnum userRoleEnum : UserRoleEnum.values()) {
			if (userRoleEnum.getUserCode() == userRoleCode) {
				return userRoleEnum;
			}
		}
		return null;
	}
}

