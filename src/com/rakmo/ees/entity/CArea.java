package com.rakmo.ees.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="areas")
public class CArea {
	@Id
	private int id;
	private String areaName;
	private String city;
	private String state;
	private String country;
	private String pin;
	
	@OneToMany(mappedBy="area", fetch= FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Voter> voters;
	@OneToMany(mappedBy="area", fetch= FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Candidate> candidates;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public List<Voter> getVoters() {
		return voters;
	}
	public void setVoters(List<Voter> voters) {
		this.voters = voters;
	}
	public List<Candidate> getCandidates() {
		return candidates;
	}
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}
	
	
}
