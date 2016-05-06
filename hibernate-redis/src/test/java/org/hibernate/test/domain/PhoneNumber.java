package org.hibernate.test.domain;

import java.io.Serializable;
import java.util.Objects;

/**
 * org.hibernate.test.domain.PhoneNumber
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:54
 */
public class PhoneNumber implements Serializable {

	private static final long serialVersionUID = 8568232753916897060L;

	private long personId = 0;
	private String numberType = "home";
	private long phone = 0;

	public boolean equals(Object obj) {
		if ((obj != null) && (obj instanceof PhoneNumber))
			return hashCode() == obj.hashCode();
		return false;
	}

	public int hashCode() {
		return Objects.hash(numberType, personId, phone);
	}

	public String toString() {
		return numberType + ":" + phone;
	}

	public long getPersonId() {
		return this.personId;
	}

	public String getNumberType() {
		return this.numberType;
	}

	public long getPhone() {
		return this.phone;
	}

	public void setPersonId(long personId) {
		this.personId = personId;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}
}
