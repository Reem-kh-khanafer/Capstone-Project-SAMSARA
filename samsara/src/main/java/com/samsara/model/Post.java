package com.samsara.model;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Version;

import com.samsara.enums.PostStatus;
import com.samsara.enums.WaterType;

@Entity
@Table(name = "Post")
@TableGenerator(name = "tabPost", initialValue = 0, allocationSize = 1)
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "tabPost")
	@Column(name = "id", updatable = false, nullable = false)
	private Long  id;
	@Version
	@Column(name = "version")
	private int version;
	
	private String city;
	private int bedroomNb;
	private int bathroomNb;
	private PostStatus status;
	private WaterType waterType;
	private boolean available;
	private int price;
	private int area;
	private int floor;
	private int buildingFloorNb;
	private String description;
	private long longitude;
	private long altitude;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "post_id")
	private List<PostPicture> pictures;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Review> reviews;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	private List<Report> reports;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getBedroomNb() {
		return bedroomNb;
	}

	public void setBedroomNb(int bedroomNb) {
		this.bedroomNb = bedroomNb;
	}

	public int getBathroomNb() {
		return bathroomNb;
	}

	public void setBathroomNb(int bathroomNb) {
		this.bathroomNb = bathroomNb;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public WaterType getWaterType() {
		return waterType;
	}

	public void setWaterType(WaterType waterType) {
		this.waterType = waterType;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getBuildingFloorNb() {
		return buildingFloorNb;
	}

	public void setBuildingFloorNb(int buildingFloorNb) {
		this.buildingFloorNb = buildingFloorNb;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Post)) {
			return false;
		}
		Post other = (Post) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (id != null)
			result += "id: " + id;
		result += ", version: " + version;
		return result;
	}

	public List<PostPicture> getPictures() {
		return pictures;
	}

	public void setPictures(List<PostPicture> pictures) {
		this.pictures = pictures;
	}

	public long getLongitude() {
		return longitude;
	}

	public void setLongitude(long longitude) {
		this.longitude = longitude;
	}

	public long getAltitude() {
		return altitude;
	}

	public void setAltitude(long altitude) {
		this.altitude = altitude;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}