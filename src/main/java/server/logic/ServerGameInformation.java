package server.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerGameInformation {
	
	private String gameID;
	private Player player1;
	private Player player2;
	private String gameStateID;
	private boolean majorChange;

//	private Map<String, List<Player>> games = new HashMap<String, List<Player>>();

	public String getGameID() {
		return gameID;
	}

	public void setGameID(String gameID) {
		this.gameID = gameID;
	}


//	public  void setGames(Map<String, List<Player>> games) {
//		this.games = games;
//	}
	
//	public List<Player> getPlayerList() {
//		return playerList;
//	}
//
//	public void setPlayerList(List<Player> playerList) {
//		this.playerList = playerList;
//	}
	
	public Player getPlayer(List<Player> playerList, String playerId) {
		for(int i=0; i<playerList.size();i++) {
			if(playerList.get(i).getId().equals(playerId)) {
				return playerList.get(i);
			}
		}
		return null;
		
	}

	public boolean isPlayerValid(String playerId) {
//		if(player1 != null && playerId.equals(player1.getId()) && player1.isYourTurn()) return true;
//		if(player2 != null && playerId.equals(player2.getId()) && player2.isYourTurn()) return true;
		if((player1 != null && playerId.equals(player1.getId()) || (player2 != null && playerId.equals(player2.getId())))) return true;
		return false;
	}
	
	public boolean isPlayerTurn(String playerId) {
		if(player1 != null && playerId.equals(player1.getId()) && player1.isYourTurn()) return true;
		if(player2 != null && playerId.equals(player2.getId()) && player2.isYourTurn()) return true;
		return false;
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public String getGameStateID() {
		return gameStateID;
	}

	public void setGameStateID(String gameStateID) {
		this.gameStateID = gameStateID;
	}

	public boolean isMajorChange() {
		return majorChange;
	}

	public void setMajorChange(boolean majorChange) {
		this.majorChange = majorChange;
	}

//	public List<Player> getPlayerlist() {
//		return playerlist;
//	}
//
//	public void setPlayerlist(List<Player> playerlist) {
//		this.playerlist = playerlist;
//	}

}
