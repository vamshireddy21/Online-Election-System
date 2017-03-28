package com.rakmo.ees.dao;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.rakmo.ees.entity.CArea;
import com.rakmo.ees.entity.Candidate;
import com.rakmo.ees.entity.Credentials;
import com.rakmo.ees.entity.Voter;
import com.rakmo.ees.exception.EESException;

public class EESDAOImpl implements EESDAO {
	@Autowired
	SessionFactory sessionFactory;

	@PostConstruct
	public void display() {
		System.out.println("sessionFactory injected");
	}

	@Override
	public boolean verifyUser(Credentials credentials) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(Credentials.class);
		int number = 0;
		try {
			number = criteria.add(Restrictions.eq("userName", credentials.getUserName()))
					.add(Restrictions.eq("password", credentials.getPassword())).list().size();
		} catch (IndexOutOfBoundsException iOBEx) {
			return false;
		}
		session.flush();
		session.close();
		if (number == 0)
			return false;
		else
			return true;
	}

	@Override
	public void addVoter(Voter voter) {
		Session session = sessionFactory.openSession();
		session.save(voter);
		session.flush();
		session.close();

	}

	@Override
	public void addCandidate(Candidate candidate) {
		Session session = sessionFactory.openSession();
		session.save(candidate);
		session.flush();
		session.close();

	}

	@Override
	public void addCredentials(Credentials credentials) {
		Session session = sessionFactory.openSession();
		session.save(credentials);
		session.flush();
		session.close();

	}

	@Override
	public void rejectCandiate(Candidate candidate) {
		Session session = sessionFactory.openSession();
		session.update(candidate);
		session.flush();
		session.close();

	}

	@Override
	public void addArea(CArea area) {
		Session session = sessionFactory.openSession();
		session.save(area);
		session.flush();
		session.close();

	}

	@Override
	public List<CArea> getAllAreas() {
		Session session = sessionFactory.openSession();
		List<CArea> areas = session.createCriteria(CArea.class).list();
		session.flush();
		session.close();
		return areas;
	}

	@Override
	public List<Candidate> getAllCandidatesOfArea(CArea area) {
		Session session = sessionFactory.openSession();
		List<Candidate> candidates = session.createCriteria(Candidate.class)
				.add(Restrictions.eq("area.id", area.getId())).add(Restrictions.eq("status", 1)).list();
		session.flush();
		session.close();
		return candidates;
	}

	@Override
	public List<Candidate> getAllPendingCandidates() {
		Session session = sessionFactory.openSession();
		List<Candidate> candidates = session.createCriteria(Candidate.class).add(Restrictions.eq("status", 0)).list();
		session.flush();
		session.close();
		return candidates;
	}

	@Override
	public List<Voter> getAllVotersOfArea(CArea area) {
		Session session = sessionFactory.openSession();
		List<Voter> voters = session.createCriteria(Voter.class).add(Restrictions.eq("area.id", area.getId())).list();
		session.flush();
		session.close();
		return voters;
	}

	@Override
	public Candidate getCandidate(int id) {
		Session session = sessionFactory.openSession();
		Candidate candidate = (Candidate) session.createCriteria(Candidate.class).add(Restrictions.eq("id", id)).list()
				.get(0);

		session.flush();
		session.close();
		return candidate;
	}

	@Override
	public Voter getVoter(int id) {
		Session session = sessionFactory.openSession();
		Voter voter = (Voter) session.createCriteria(Voter.class).add(Restrictions.eq("id", id)).list().get(0);
		session.flush();
		session.close();
		return voter;
	}

	@Override
	public CArea getAreaById(int id) {
		Session session = sessionFactory.openSession();
		CArea area = (CArea) session.createCriteria(CArea.class).list().get(0);
		session.flush();
		session.close();
		return area;
	}

	@Override
	public Candidate getCandidate(String userName) throws EESException {
		Session session = sessionFactory.openSession();
		Candidate candidate = null;
		try {
			candidate = (Candidate) session.createCriteria(Candidate.class).createAlias("credentials", "cred")
					.add(Restrictions.eq("cred.userName", userName)).list().get(0);
		} catch (IndexOutOfBoundsException iOBE) {
			throw new EESException("No Candidate Existed");
		} catch (Exception ex) {
			throw new EESException("No Candidate Existed");
		}
		session.flush();
		session.close();
		return candidate;
	}

	@Override
	public Voter getVoter(String userName) throws EESException {
		Session session = sessionFactory.openSession();
		Voter voter = null;
		try {
			voter = (Voter) session.createCriteria(Voter.class).createAlias("credentials", "cred")
					.add(Restrictions.eq("cred.userName", userName)).list().get(0);
		} catch (IndexOutOfBoundsException iOBE) {
			throw new EESException("No Voter Existed");
		} catch (Exception ex) {
			throw new EESException("No Voter Existed");
		}
		session.flush();
		session.close();
		return voter;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		Session session = sessionFactory.openSession();
		session.update(candidate);
		session.flush();
		session.close();

	}

	@Override
	public void updateVoter(Voter voter) {
		Session session = sessionFactory.openSession();
		session.update(voter);
		session.flush();
		session.close();

	}

}
