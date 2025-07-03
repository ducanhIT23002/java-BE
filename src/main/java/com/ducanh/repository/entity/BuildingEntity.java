package com.ducanh.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="building")
public class BuildingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name ="name") // Đây đã đúng rồi
    private String name;
    
    @Column(name ="districtid") // Thêm annotation này
    private Integer districtId;
    
    @Column(name ="street") // Đây đã đúng rồi
    private String street;
    
    @Column(name ="ward") // Đây đã đúng rồi
    private String ward;

    @Column(name ="numberofbasement") // Thêm annotation này
    private Integer numberOfBasement;

    @Column(name ="floorarea") // Thêm annotation này
    private Long floorArea;

    @Column(name ="rentprice") // Thêm annotation này
    private Long rentPrice;

    @Column(name ="managername") // Thêm annotation này
    private String managerName;

    @Column(name ="managerphonenumber") // Thêm annotation này
    private String managerPhoneNumber;

    @Column(name ="servicefee") // Thêm annotation này
    private Long serviceFee;

    @Column(name ="brokeragefee") // Thêm annotation này
    private Long brokerageFee;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public Long getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Long floorArea) {
		this.floorArea = floorArea;
	}
	public Long getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Long rentPrice) {
		this.rentPrice = rentPrice;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(String managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	public Long getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Long serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Long getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Long brokerageFee) {
		this.brokerageFee = brokerageFee;
	}


}
