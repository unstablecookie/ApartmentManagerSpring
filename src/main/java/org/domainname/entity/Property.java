package org.domainname.entity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import org.domainname.entity.AptType;

import org.hibernate.annotations.Type;

@Entity
@Table(name="PROPERTY")
public class Property {

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "property_id_seq")
	@SequenceGenerator(name = "property_id_seq",sequenceName="property_id_seq", allocationSize = 1)
	private long id;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private AptType type;
	
	@Column(name="area")
	private int area;
	@Column(name="build")
	private int build;

	
	@javax.persistence.Lob
	@Column(name="photo")
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] photo;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user; // FIX THIS
	
	public Property() {}
	public Property(AptType type,int area,int build,User usr,String photoArr) {
		this.type = type;
		this.area = area;
		this.build = build;
		this.user = usr;
		this.photo = new byte[] {};

		
		/* legacy
		if(photoArr.length>0) {
			this.photo = photoArr;
		}else {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			try (InputStream in = getClass().getResourceAsStream("/DefaultHouse.png")){
				int length;
				byte[] buffer = new byte[1024];
				while((length = in.read(buffer)) != -1) out.write(buffer,0,length);
			}catch(IOException e) {
				
			}
			this.photo = out.toByteArray();
		}*/
		

	}
	
	public long getId() {
		return id;
	}
	
	public AptType getType() {
		return this.type;
	}
	
	public void setType(AptType type) {
		this.type = type;
	}
	
	public int getArea() {
		return area;
	}
	
	public void setArea(int area) {
		this.area = area;
	}
	
	public int getBuild() {
		return this.build;
	}
	
	public void setBuild(int build) {
		this.build = build;
	}
	
	public byte[] getPhoto() {
		return this.photo;
	}
	
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
}
