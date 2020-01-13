package server.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerGameInformation {
	
	private String gameID = "";
	private List<String> gameIdList = new ArrayList<String>();
	private List<String> playerIdList = new ArrayList<String>(); 
	private List<Player> playerList = new ArrayList<Player>();

	private Map<String, List<Player>> games = new HashMap<String, List<Player>>();
	
//	public ServerGameInformation(List<String> gameList, List<String> playerList) {
//		this.gameList = gameList;
//		this.playerList = playerList;
//	}
//	

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}

	public List<String> getGameList() {
		return gameIdList;
	}

	public  void setGameList(List<String> gameList) {
		this.gameIdList = gameList;
	}

	public List<String> getPlayerIdList() {
		return playerIdList;
	}

	public  void setPlayerIdList(List<String> playerList) {
		this.playerIdList = playerList;
	}

	public  Map<String, List<Player>> getGames() {
		return games;
	}

	public  void setGames(Map<String, List<Player>> games) {
		this.games = games;
	}
	
	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}
	
	public Player getPlayer(List<Player> playerList, String playerId) {
		for(int i=0; i<playerList.size();i++) {
			if(playerList.get(i).getId().equals(playerId)) {
				return playerList.get(i);
			}
		}
		return null;
		
	}

}
