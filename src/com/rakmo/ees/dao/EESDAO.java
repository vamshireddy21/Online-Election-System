package com.rakmo.ees.dao;
import java.util.List;

import com.rakmo.ees.entity.CArea;
import com.rakmo.ees.entity.Candidate;
import com.rakmo.ees.entity.Credentials;
import com.rakmo.ees.entity.Voter;
import com.rakmo.ees.exception.EESException;


public interface EESDAO {
	public void addVoter(Voter voter);
	public void addCandidate(Candidate candidate);
	public void addCredentials(Credentials credentials);
	public void rejectCandiate(Candidate candidate);
	public void addArea(CArea area);
	
	public List<CArea> getAllAreas();
	public List<Candidate> getAllCandidatesOfArea(CArea area);
	public List<Candidate> getAllPendingCandidates();
	public List<Voter> getAllVotersOfArea(CArea area);
	
	
	public Candidate getCandidate(int id);
	public Candidate getCandidate(String userName) throws EESException;
	public void updateCandidate(Candidate candidate);
	public Voter getVoter(int id);
	public Voter getVoter(String userName) throws EESException;
	public void updateVoter(Voter voter);
	public CArea getAreaById(int id);
	boolean verifyUser(Credentials credentials);
	
	}
