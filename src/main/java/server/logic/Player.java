package server.logic;

public class Player {
	
	private String id;
	private String firstName;
	private String lastName;
	private String studentID;
	private boolean isYourTurn;
	
	
	public String getPlayerFirstName() {
		return firstName;
	}
	public void setPlayerFirstName(String playerFirstName) {
		this.firstName = playerFirstName;
	}
	public String getPlayerLastName() {
		return lastName;
	}
	public void setPlayerLastName(String playerLastName) {
		this.lastName = playerLastName;
	}
	public String getPlayerStudentID() {
		return studentID;
	}
	public void setPlayerStudentID(String playerStudentID) {
		this.studentID = playerStudentID;
	}
	public boolean isYourTurn() {
		return isYourTurn;
	}
	public void setYourTurn(boolean isYourTurn) {
		this.isYourTurn = isYourTurn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
