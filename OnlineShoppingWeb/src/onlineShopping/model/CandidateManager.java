package onlineShopping.model;

import java.util.List;

import onlineShopping.entities.Candidate;
import onlineShopping.exceptions.CandidateException;

public class CandidateManager { 
	
    public CandidateManager() {
        super();
    }
    
    // First four methods do not need to be implemented because 
    // they not required in this exercise. However they would be 
    // implemented in a more generic bean used in other situations.
    
    public Candidate getCandidate(String CandidateCode) throws CandidateException {
    	// method not required for this exercise
    	return null;
    }
    
    public Candidate addCandidate(Candidate candidate) throws CandidateException {
    	// method not required for this exercise
    	return null;
    }
    
    public Candidate deleteCandidate(String CandidateCode) throws CandidateException {
    	// method not required for this exercise
    	return null;
    }
    
    public Candidate updateCandidate(Candidate Candidate) throws CandidateException {
    	// method not required for this exercise
    	return null;
    }
    
    // returns candidate information used to generate buttons on the ballot form
	public List<Candidate> getCandidates() {
		// complete this method and modify the next line to 
		// return a list of candidates
		return null;
	}
}
