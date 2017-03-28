package com.rakmo.ees.service;



import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;


import com.rakmo.ees.dao.EESDAO;
import com.rakmo.ees.entity.CArea;
import com.rakmo.ees.entity.Candidate;
import com.rakmo.ees.entity.Credentials;
import com.rakmo.ees.entity.Voter;
import com.rakmo.ees.exception.EESException;

public class EESServiceImpl implements EESService{

	@Autowired
	private EESDAO eESDAOImpl;
	
	@PostConstruct
	public void display()
	{
		System.out.println("EADAOImpl injected");
	}

	@Override
	public void addVoter(Voter voter) {
		 eESDAOImpl.addVoter(voter);
		
	}

	@Override
	public void addCandidate(Candidate candidate) {
		eESDAOImpl.addCandidate(candidate);
		
	}

	@Override
	public void addCredentials(Credentials credentials) {
		eESDAOImpl.addCredentials(credentials);
		
	}

	@Override
	public void rejectCandiate(Candidate candidate) {
		eESDAOImpl.rejectCandiate(candidate);
		
	}

	@Override
	public void addArea(CArea area) {
		eESDAOImpl.addArea(area);
		
	}

	@Override
	public List<CArea> getAllAreas() {
		return eESDAOImpl.getAllAreas();
	}



	@Override
	public Candidate getCandidate(int id) {
		return eESDAOImpl.getCandidate(id);
	}

	@Override
	public Voter getVoter(int id) {
		return eESDAOImpl.getVoter(id);
	}

	@Override
	public CArea getAreaById(int id) {
		return eESDAOImpl.getAreaById(id);
	}

	@Override
	public List<Candidate> getAllCandidatesOfArea(CArea area) {
		return eESDAOImpl.getAllCandidatesOfArea(area);
	}

	@Override
	public List<Voter> getAllVotersOfArea(CArea area) {
		return eESDAOImpl.getAllVotersOfArea(area);
	}

	@Override
	public boolean verifyUser(Credentials credentials) {
		return eESDAOImpl.verifyUser(credentials);
	}

	@Override
	public Candidate getCandidate(String userName) throws EESException {
		return eESDAOImpl.getCandidate(userName);
	}

	@Override
	public Voter getVoter(String userName) throws EESException{
		return eESDAOImpl.getVoter(userName);
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		eESDAOImpl.updateCandidate(candidate);
		
	}

	@Override
	public void updateVoter(Voter voter) {
		eESDAOImpl.updateVoter(voter);
		
	}

	@Override
	public List<Candidate> getAllPendingCandidates() {
		return eESDAOImpl.getAllPendingCandidates();
	}

	@Override
	public String generateUserId(String userName) {
		return userName + new Random().nextInt(1000);
	}

	@Override
	public String generatePass(String userName) {
		return ""+ new Random().nextInt(999999);
	}

	

	

	

	
	
}
