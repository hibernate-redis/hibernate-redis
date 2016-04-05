package org.hibernate.test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * org.hibernate.test.domain.UuidItem
 *
 * @author sunghyouk.bae@gmail.com
 * @since 13. 4. 6. 오전 12:54
 */
@Entity
public class UuidItem implements Serializable {

	private static final long serialVersionUID = 855411710458442104L;

	@Id
	@GeneratedValue
	private String id;

	private String name;

	private String description;

    private Float score = 100.6f;

    private Double amount = 134.9d;


	public String getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public Float getScore() {
		return this.score;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
