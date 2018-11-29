package onlineShopping.model;

import java.io.PrintStream;

import onlineShopping.entities.Candidate;
import onlineShopping.entities.Voter;
import onlineShopping.exceptions.ElectionException;

public class ElectionManager { 
	
    public ElectionManager() {
       super();
    }
    
    // user login: returns a Voter if user authenticated or null otherwise  
	public Voter authenticateVoter(int voterId, String password) throws ElectionException  {
		    // complete this method to return either an authenticated Voter
		    // or null
			return null;
	}

	// user votes: set Voter.hasVoted to true or 1
	//  and 1 to count of votes for the candidate	
	public void castBallot(Voter v, Candidate c ) throws ElectionException, DataInputException {
	// complete this method process one ballot form
    // throw exceptions as appropriate
		return;
	}
	
	// for testing and debugging only: 
	// print current vote count for each candidate to the server console 	
	public void printVoteCount(PrintStream out) throws ElectionException {
	// complete this method
		return;
	}
}
