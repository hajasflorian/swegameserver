package server.logic;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import MessagesBase.PlayerRegistration;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.GameState;
import MessagesGameState.PlayerState;
import server.exceptions.InvalidGameIdException;

public class ServerLogic {

	private ServerGameInformation gameInfo = new ServerGameInformation();
	private Player player = new Player();

	public String newGameCreation(int len) {
		final String alphaNumericContainer = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder strgbldr = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			strgbldr.append(alphaNumericContainer.charAt(rnd.nextInt(alphaNumericContainer.length())));

		String gameID = strgbldr.toString();

		gameInfo.setGameID(gameID);
		gameInfo.getGames().put(gameID, null);
		return gameID;
	}

	public boolean isGameIdValid(String gameID) {
		if (gameInfo.getGames().containsKey(gameID)) {
			return true;
		} else {
			return false;
		}
	}

	public UniquePlayerIdentifier newPlayerCreation(String gameID, PlayerRegistration playerRegistration) {
		UniquePlayerIdentifier playerID = new UniquePlayerIdentifier();

		if (isGameIdValid(gameID)) {			
			playerID = new UniquePlayerIdentifier(UUID.randomUUID().toString());
			player.setPlayerFirstName(playerRegistration.getStudentFirstName());
			player.setPlayerLastName(playerRegistration.getStudentLastName());
			player.setPlayerStudentID(playerRegistration.getStudentID());
			player.setId(playerID.getUniquePlayerID());
			gameInfo.getPlayerIdList().add(playerID.getUniquePlayerID());
			if(gameInfo.getPlayerList().isEmpty()) {
				player.setYourTurn(true);
			} else {
				player.setYourTurn(false);
			}
			gameInfo.getPlayerList().add(player);
			gameInfo.getGames().put(gameID, gameInfo.getPlayerList());
		} else {
			throw new InvalidGameIdException("Name: InvalidGameIdException",
					"Message: The game ID used during the registration is not exists or not valid anymore!");
		}
		
		
		return playerID;
	}

	public boolean isPlayerIdValid(String gameID, String playerID) {
//		gameInfo.getPlayerList().contains(playerID)
		List<Player> playerList = gameInfo.getPlayerList();
//		if (gameInfo.getGames().containsValue(gameInfo.getPlayer(playerList, playerID))) {
		if(gameInfo.getPlayerIdList().contains(playerID)) {
			return true;
		} else {
			return false;
		}
	}
	
	public void setUpGameState(String gameID, String playerID) {
		List<Player> players = gameInfo.getGames().get(gameID);
//		if(gameInfo.getGames().get(gameID).contains(gameInfo.getPlayer(players, playerID))) {
//			
//		}
		
//		PlayerState playerstate = new PlayerState(firstName, lastName, studentID, state, identifier, collectedTreasure)
//		GameState gameState = new GameState(map, players, gameStateID);
//		return gameState;
	}

}
