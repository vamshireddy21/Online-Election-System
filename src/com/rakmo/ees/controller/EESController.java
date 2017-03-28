package com.rakmo.ees.controller;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rakmo.ees.entity.CArea;
import com.rakmo.ees.entity.Candidate;
import com.rakmo.ees.entity.Credentials;
import com.rakmo.ees.entity.Voter;
import com.rakmo.ees.exception.EESException;
import com.rakmo.ees.service.EESService;
import com.rakmo.ees.util.Constants;

@Controller
public class EESController {
	@Autowired
	private EESService eESServiceImpl;

	@PostConstruct
	public void display() {
		System.out.println("eESServiceImpl injected");
	}

	public EESService geteESServiceImpl() {
		return eESServiceImpl;
	}

	public void seteESServiceImpl(EESService eESServiceImpl) {
		this.eESServiceImpl = eESServiceImpl;
	}

	@RequestMapping(value = { "/", "", "welcome" })
	public ModelAndView getWelcomePage() {
		System.out.println("Application Start");
		ModelAndView modelView = new ModelAndView("welcomepage");
		return modelView;
	}

	@RequestMapping(value = "/addVoter")
	public ModelAndView addVoter(@ModelAttribute Voter voter, BindingResult result) {
		ModelAndView modelView = null;
		if (result.hasErrors() || checkVoterValidations(voter)) {
			modelView = getVoterRegModelAndView();
			// modelView.addObject("areas", areas);
			modelView.addObject("errorMessage", "Validation errors! Please enter all the fields properly");
			return modelView;
		}
		String userName = voter.getCredentials().getUserName();
		Voter voterExisted = null;
		try {
			voterExisted = eESServiceImpl.getVoter(userName);
		} catch (EESException ex) {
			modelView = getVoterRegModelAndView();
			// modelView.addObject("areas", areas);
			modelView.addObject("errorMessage", "User Name already Existed! Please choose a other one");
		}
		

		modelView = new ModelAndView("welcomepage");

		int areaId = voter.getAreaId();
		System.out.println("Area id of voter " + areaId);
		modelView = new ModelAndView("welcomepage");
		CArea area = eESServiceImpl.getAreaById(areaId);
		voter.setArea(area);
		String userId = eESServiceImpl.generateUserId(voter.getFirstName());
		String pass = eESServiceImpl.generatePass(voter.getFirstName());
		voter.setUserId(userId);
		voter.setPass(pass);
		voter.setStatus(1);
		// eESServiceImpl.addCredentials(voter.getCredentials());
		eESServiceImpl.addVoter(voter);
		modelView.addObject("successMessage", "Congrats! You are successfully registered as a voter");
		return modelView;
	}

	@RequestMapping(value = "/addCand")
	public ModelAndView addCandidate(@ModelAttribute Candidate candidate, BindingResult result) {
		ModelAndView modelView = null;
		if (result.hasErrors() || checkCandValidations(candidate)) {
			modelView = getCandRegModelAndView();
			// modelView.addObject("areas", areas);
			modelView.addObject("errorMessage", "Validation errors! Please enter all the fields properly");
			return modelView;
		}

		// System.out.println(request.getParameter("areaId"));
		// String areaStr = request.getParameter("areaId");
		String userName = candidate.getCredentials().getUserName();
		Candidate candidateExisted = null;
		try {
			candidateExisted = eESServiceImpl.getCandidate(userName);
		} catch (EESException ex) {
			int areaId = candidate.getAreaId();

			modelView = new ModelAndView("welcomepage");
			CArea area = eESServiceImpl.getAreaById(areaId);
			candidate.setArea(area);
			System.out.println("Area Name " + candidate.getArea().getAreaName());
			eESServiceImpl.addCredentials(candidate.getCredentials());
			// candidate.setArea(eESServiceImpl.getAreaById(areaId));
			eESServiceImpl.addCandidate(candidate);

			modelView.addObject("successMessage", "Congrats! You are successfully registered as a Candidate");

			return modelView;
		}

		modelView = getCandRegModelAndView();
		// modelView.addObject("areas", areas);
		modelView.addObject("errorMessage", "User name already Existed! Please choose a different one");
		return modelView;

	}

	private boolean checkCandValidations(Candidate candidate) {
		if (candidate.getFirstName().isEmpty() || candidate.getLastName().isEmpty() 
				|| candidate.getEmailId().isEmpty()
				|| candidate.getAboutCandidate().isEmpty() 
				|| candidate.getFatherName().isEmpty()
				|| candidate.getMobileNo().isEmpty() 
				|| candidate.getParty().isEmpty()
				|| candidate.getCredentials().getUserName().isEmpty()
				|| candidate.getCredentials().getPassword().isEmpty()) {
			return true;
		} else
			return false;
	}
	
	private boolean checkVoterValidations(Voter voter) {
		if (voter.getFirstName().isEmpty() || voter.getLastName().isEmpty() 
				|| voter.getEmailId().isEmpty()
				|| voter.getAddress().isEmpty()
				|| voter.getFatherName().isEmpty()
				|| voter.getMobileNo().isEmpty() 
				|| voter.getMobileNo().isEmpty()
				|| voter.getCredentials().getUserName().isEmpty()
				|| voter.getCredentials().getPassword().isEmpty()) {
			return true;
		} else
			return false;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam int type) {
		System.out.println("Enter login in Controller");
		ModelAndView modelView = null;
		if (type == 1)
			modelView = getVoterLoginModelView();
		else if (type == 2)
			modelView = getCandLoginModelView();
		else
			modelView = getAdminLoginModelView();
		return modelView;

	}

	private ModelAndView getAdminLoginModelView() {
		return new ModelAndView("adminlogin", "credentials", new Credentials());
	}

	private ModelAndView getCandLoginModelView() {
		return new ModelAndView("candlogin", "credentials", new Credentials());
	}

	private ModelAndView getVoterLoginModelView() {
		return new ModelAndView("voterlogin", "credentials", new Credentials());
	}

	@RequestMapping(value = "/reg")
	public ModelAndView reg(@RequestParam int type) {
		ModelAndView modelView = null;
		if (type == 1) {
			modelView = getVoterRegModelAndView();
		} else if (type == 2) {
			modelView = getCandRegModelAndView();
		}

		return modelView;

	}

	@RequestMapping(value = "/voterLogin")
	public ModelAndView voterLogin(@ModelAttribute Credentials credentials, BindingResult result) throws EESException {
		ModelAndView modelView = null;

		try {
			modelView = new ModelAndView("voter");
			Voter voter = eESServiceImpl.getVoter(credentials.getUserName());
			CArea area = null;
			if (voter.getArea() != null) {
				area = voter.getArea();
			} else {
				modelView = getVoterLoginModelView();
				modelView.addObject("errorMessage", "Area missing for Existing Voter! Please check");
			}
			List<Candidate> candidates = eESServiceImpl.getAllCandidatesOfArea(area);

			modelView.addObject("voter", voter);
			modelView.addObject("candidates", candidates);
			modelView.addObject("noOfCand", candidates.size());
		} catch (EESException eESE) {
		} catch (Exception ex) {
			ex.printStackTrace();
			modelView = getVoterLoginModelView();
			modelView.addObject("errorMessage", "Invalid credentials! Please check");
		}

		return modelView;
	}

	@RequestMapping(value = "/candLogin")
	public ModelAndView candidateLogin(@ModelAttribute Credentials credentials, BindingResult result) {
		ModelAndView modelView = null;
		Candidate candidate = null;
		try {
			modelView = new ModelAndView("candidate");

			candidate = eESServiceImpl.getCandidate(credentials.getUserName());
			modelView.addObject("candidate", candidate);
			List<Candidate> competitors = eESServiceImpl.getAllCandidatesOfArea(candidate.getArea());
			modelView.addObject("candidates", competitors);
			modelView.addObject("noOfCand", competitors.size());
		} catch (EESException eESex) {
			modelView = getCandLoginModelView();
			modelView.addObject("errorMessage", "Invalid credentials! Please check");
			return modelView;
		} catch (Exception ex) {
			modelView = getCandLoginModelView();
			modelView.addObject("errorMessage", "Invalid credentials! Please check");
		}
		return modelView;
	}

	@RequestMapping(value = "/adminLogin")
	public ModelAndView adminLogin(@ModelAttribute Credentials credentials, BindingResult result) {
		ModelAndView modelView = null;

		String userName = Constants.adminUserName;
		String password = Constants.adminPassword;
		boolean isValid = userName.compareTo(credentials.getUserName()) == 0
				&& password.compareTo(credentials.getPassword()) == 0;
		if (isValid) {
			
			modelView = getAdminView();
			return modelView;
		} else {
			modelView = new ModelAndView("adminlogin");
			modelView.addObject("errorMessage", "Invalid credentials! Please check");
		}
		return modelView;
	}

	@RequestMapping("admin")
	public ModelAndView getAdmin()
	{
		ModelAndView modelView = getAdminView();
		return modelView;
	}
	private ModelAndView getAdminView() {
		ModelAndView modelView;
		modelView = new ModelAndView("admin");

		List<Candidate> candidates = eESServiceImpl.getAllPendingCandidates();
		modelView.addObject("candidates", candidates);
		modelView.addObject("noOfCand", candidates.size());
		return modelView;
	}
	
	
	@RequestMapping(value = "/progress")
	public ModelAndView progress()
	{
		ModelAndView modelView = new ModelAndView("electionprogress");
		List<CArea> areas = eESServiceImpl.getAllAreas();
		modelView.addObject("areas", areas);
		return modelView;
	}

	@RequestMapping(value = "/candApproval")
	public ModelAndView candApproval(@RequestParam int id) {
		Candidate candidate = eESServiceImpl.getCandidate(id);
		ModelAndView modelView = new ModelAndView("candapproval");
		modelView.addObject("candidate", candidate);
		String imageText = new String(candidate.getiDCard());
		modelView.addObject("imageText", imageText);
		return modelView;
	}

	@RequestMapping(value = "/candInfo")
	public ModelAndView getCandInfo(@RequestParam int id) {
		Candidate candidate = eESServiceImpl.getCandidate(id);
		ModelAndView modelView = new ModelAndView("candinfo");
		modelView.addObject("candidate", candidate);
		String imageText = new String(candidate.getiDCard());
		modelView.addObject("imageText", imageText);
		return modelView;
	}

	@RequestMapping(value = "/approveCand")
	public ModelAndView approveCandidate(@RequestParam int id) {
		Candidate candidate = eESServiceImpl.getCandidate(id);
		candidate.setStatus(1);
		String userId = eESServiceImpl.generatePass(candidate.getCredentials().getUserName());
		String pass = eESServiceImpl.generatePass(candidate.getCredentials().getUserName());
		candidate.setUserId(userId);
		candidate.setPass(pass);
		eESServiceImpl.updateCandidate(candidate);
		ModelAndView modelView = new ModelAndView("admin");
		List<Candidate> candidates = eESServiceImpl.getAllPendingCandidates();
		modelView.addObject("candidates", candidates);
		modelView.addObject("noOfCand", candidates.size());
		modelView.addObject("confirmMessage",
				"Candidate " + candidate.getFirstName() + " " + candidate.getLastName() + " is successfully approved");
		return modelView;
	}

	@RequestMapping(value = "/rejectCand")
	public ModelAndView rejectCandidate(@RequestParam int id) {
		Candidate candidate = eESServiceImpl.getCandidate(id);
		candidate.setStatus(2);
		eESServiceImpl.updateCandidate(candidate);
		ModelAndView modelView = new ModelAndView("admin");
		List<Candidate> candidates = eESServiceImpl.getAllPendingCandidates();
		modelView.addObject("candidates", candidates);
		modelView.addObject("noOfCand", candidates.size());
		modelView.addObject("confirmMessage",
				"Candidate " + candidate.getFirstName() + " " + candidate.getLastName() + " is successfully rejected");
		return modelView;
	}
	
	@RequestMapping(value = "/vote")
	public ModelAndView vote(@RequestParam int vid, @RequestParam int cid) {
		Voter voter = eESServiceImpl.getVoter(vid);
		if(voter.getStatus() == 0)
		{
			eESServiceImpl.updateVoter(voter);
			ModelAndView modelView = getVoterLoginModelView();
			modelView.addObject("errorMessage", "You have already voted. Dont try to Cheat!!");
			return modelView;
		}
		Candidate candidate = eESServiceImpl.getCandidate(cid);
		candidate.setNoOfVotes(candidate.getNoOfVotes() + 1);		
		voter.setCandidate(candidate);
		voter.setStatus(0);
		eESServiceImpl.updateCandidate(candidate);
		eESServiceImpl.updateVoter(voter);
		ModelAndView modelView = getVoterLoginModelView();
		modelView.addObject("successMessage", "Congrats! You have casted your vote successfully");
		return modelView;
	}

	private ModelAndView getVoterRegModelAndView() {
		ModelAndView modelView;
		modelView = new ModelAndView("voterreg", "voter", new Voter());
		LinkedHashMap<Integer, String> areaMap = getAreaMap();

		modelView.addObject("areaMap", areaMap);
		return modelView;
	}

	private LinkedHashMap<Integer, String> getAreaMap() {
		LinkedHashMap<Integer, String> areaMap = new LinkedHashMap<Integer, String>();
		List<CArea> areas = eESServiceImpl.getAllAreas();
		for (CArea areaElement : areas) {
			areaMap.put(areaElement.getId(), areaElement.getAreaName() + " | " + areaElement.getPin());
		}
		return areaMap;
	}

	private ModelAndView getCandRegModelAndView() {
		ModelAndView modelView;
		modelView = new ModelAndView("candreg", "candidate", new Candidate());
		LinkedHashMap<Integer, String> areaMap = getAreaMap();
		modelView.addObject("areaMap", areaMap);
		LinkedHashMap<String, String> partyMap = getPartyMap();
		modelView.addObject("partyMap", partyMap);
		return modelView;
	}

	private LinkedHashMap<String, String> getPartyMap() {
		List<String> parties = Constants.parties;
		LinkedHashMap<String, String> partyMap = new LinkedHashMap<String, String>();
		for (String party : parties) {
			partyMap.put(party, party);
		}
		return partyMap;
	}

}