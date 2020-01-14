package server.logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import MessagesBase.PlayerRegistration;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.EPlayerGameState;
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
		return gameInfo.getPlayerIdList().contains(playerID);
	}
	
	public GameState setUpGameState(String gameID, String playerID) {
		List<Player> players = gameInfo.getGames().get(gameID);
		Collection<PlayerState> playerStateCollection = new ArrayList<>();
		for(Player player : players) {
			if(player.isYourTurn()) {
				PlayerState playerState = new PlayerState(player.getPlayerFirstName(), player.getPlayerLastName(), player.getPlayerStudentID(), EPlayerGameState.ShouldActNext,new UniquePlayerIdentifier(playerID), false);
				playerStateCollection.add(playerState);
			} else {
				PlayerState playerState = new PlayerState(player.getPlayerFirstName(), player.getPlayerLastName(), player.getPlayerStudentID(), EPlayerGameState.ShouldWait,new UniquePlayerIdentifier(playerID), false);
				playerStateCollection.add(playerState);
			}
		}
		System.out.println(playerStateCollection.size());
		
		GameState gameState = new GameState(playerStateCollection, UUID.randomUUID().toString());
		return gameState;
	}

}
