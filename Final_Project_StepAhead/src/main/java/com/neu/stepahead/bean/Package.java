package com.neu.stepahead.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "tblPackage")
public class Package {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "packageId", nullable = false, unique = true)
	private long packageId;

	@Column(name = "minSal")
	private int minSal;

	@Column(name = "maxSal")
	private int maxSal;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "positionId")
	private Position position;

	public Package() {

	}

	public long getPackageId() {
		return packageId;
	}

	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}

	public int getMinSal() {
		return minSal;
	}

	public void setMinSal(int minSal) {
		this.minSal = minSal;
	}

	public int getMaxSal() {
		return maxSal;
	}

	public void setMaxSal(int maxSal) {
		this.maxSal = maxSal;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return this.position.getPositionTitle() + " [$" + this.minSal + " - $" + this.maxSal + "]";
	}
}
