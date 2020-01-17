package server.logic;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import MessagesBase.HalfMap;
import MessagesBase.PlayerRegistration;
import MessagesBase.UniquePlayerIdentifier;
import MessagesGameState.EPlayerGameState;
import MessagesGameState.GameState;
import MessagesGameState.PlayerState;
import server.exceptions.InvalidGameIdException;

public class ServerLogic {
	
	private Map<String, ServerGameInformation> games = new HashMap<>();

	public String createNewGame(int len) {
		ServerGameInformation gameInfo = new ServerGameInformation();
		final String alphaNumericContainer = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		SecureRandom rnd = new SecureRandom();

		StringBuilder strgbldr = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			strgbldr.append(alphaNumericContainer.charAt(rnd.nextInt(alphaNumericContainer.length())));

		String gameID = strgbldr.toString();
		
		gameInfo.setGameID(gameID);
		games.put(gameID,gameInfo);
		return gameID;
	}

	public boolean isGameIdValid(String gameID) {
		return games.containsKey(gameID);
	}

	public UniquePlayerIdentifier newPlayerCreation(String gameID, PlayerRegistration playerRegistration) {
		UniquePlayerIdentifier playerID;

		if (isGameIdValid(gameID)) {
			if(games.get(gameID).getPlayer1() == null) {
				playerID = new UniquePlayerIdentifier(UUID.randomUUID().toString());
				Player player1 = new Player(playerID.getUniquePlayerID());
				player1.setPlayerFirstName(playerRegistration.getStudentFirstName());
				player1.setPlayerLastName(playerRegistration.getStudentLastName());
				player1.setPlayerStudentID(playerRegistration.getStudentID());
				player1.setYourTurn(false);
				games.get(gameID).setPlayer1(player1);
			} else if(games.get(gameID).getPlayer2() == null) {
				playerID = new UniquePlayerIdentifier(UUID.randomUUID().toString());
				Player player2 = new Player(playerID.getUniquePlayerID());
				player2.setPlayerFirstName(playerRegistration.getStudentFirstName());
				player2.setPlayerLastName(playerRegistration.getStudentLastName());
				player2.setPlayerStudentID(playerRegistration.getStudentID());
				player2.setYourTurn(true);
				games.get(gameID).setPlayer2(player2);
				System.out.println("Player1: " + games.get(gameID).getPlayer1().getId() + ", Player2: " + games.get(gameID).getPlayer2().getId());
			} else {
				throw new RuntimeException("Game is full");
			}
		} else {
			throw new InvalidGameIdException("Name: InvalidGameIdException",
					"Message: The game ID used during the registration is not exists or not valid anymore!");
		}
		return playerID;
	}

	public boolean isPlayerIdValid(String gameID, String playerID) {
//		System.out.println("GameID: " + gameID + ", PlayerID: " + playerID + ", Player1: " + games.get(gameID).getPlayer1().getId() + ", Player2: " + games.get(gameID).getPlayer2().getId());
//		System.out.println(games.get(gameID));
//		System.out.println(games.get(gameID).getActiveplayer().getId());
		return games.get(gameID).isPlayerValid(playerID);
		
	}
	
	public GameState setUpGameState(String gameID, String playerID) {
		Collection<PlayerState> playerStateCollection = new ArrayList<>();
		Player player1 = games.get(gameID).getPlayer1();
		if(player1 != null) {
			System.out.println("Player1 should act next: " + player1.isYourTurn());
			if(player1.isYourTurn()) {
				if(player1.getId().equals(playerID)) {
					PlayerState playerState1 = new PlayerState(player1.getPlayerFirstName(), player1.getPlayerLastName(), player1.getPlayerStudentID(), EPlayerGameState.ShouldActNext, new UniquePlayerIdentifier(playerID), false);
					playerStateCollection.add(playerState1);
				} else {
					PlayerState playerState1 = new PlayerState(player1.getPlayerFirstName(), player1.getPlayerLastName(), player1.getPlayerStudentID(), EPlayerGameState.ShouldActNext, UniquePlayerIdentifier.random(), false);
					playerStateCollection.add(playerState1);
				}
			} else {
				if(player1.getId().equals(playerID)) {
					PlayerState playerState1 = new PlayerState(player1.getPlayerFirstName(), player1.getPlayerLastName(), player1.getPlayerStudentID(), EPlayerGameState.ShouldWait, new UniquePlayerIdentifier(playerID), false);
					playerStateCollection.add(playerState1);
				} else {
					PlayerState playerState1 = new PlayerState(player1.getPlayerFirstName(), player1.getPlayerLastName(), player1.getPlayerStudentID(), EPlayerGameState.ShouldWait, UniquePlayerIdentifier.random(), false);
					playerStateCollection.add(playerState1);
				}
			}
		}
		Player player2 = games.get(gameID).getPlayer2();
		if(player2 != null) {
			System.out.println("Player2 should act next: " + player2.isYourTurn());
			if(player2.isYourTurn()) {
				if(player2.getId().equals(playerID)) {
					PlayerState playerState2 = new PlayerState(player2.getPlayerFirstName(), player2.getPlayerLastName(), player2.getPlayerStudentID(), EPlayerGameState.ShouldActNext, new UniquePlayerIdentifier(playerID), false);
					playerStateCollection.add(playerState2);
				} else {
					PlayerState playerState2 = new PlayerState(player2.getPlayerFirstName(), player2.getPlayerLastName(), player2.getPlayerStudentID(), EPlayerGameState.ShouldActNext, UniquePlayerIdentifier.random(), false);
					playerStateCollection.add(playerState2);
				}
			} else {
				if(player2.getId().equals(playerID)) {
					PlayerState playerState2 = new PlayerState(player2.getPlayerFirstName(), player2.getPlayerLastName(), player2.getPlayerStudentID(), EPlayerGameState.ShouldWait, new UniquePlayerIdentifier(playerID), false);
					playerStateCollection.add(playerState2);
				} else {
					PlayerState playerState2 = new PlayerState(player2.getPlayerFirstName(), player2.getPlayerLastName(), player2.getPlayerStudentID(), EPlayerGameState.ShouldWait, UniquePlayerIdentifier.random(), false);
					playerStateCollection.add(playerState2);
				}
			}
		}
		
//		System.out.println(player1.isYourTurn() + "" + player2.isYourTurn());
		System.out.println(playerStateCollection.size());
		
		GameState gameState = new GameState(playerStateCollection, games.get(gameID).getGameStateID());
		return gameState;
	}
	
	public void togglePlayer(String gameID) {
		if(games.get(gameID).getPlayer1().isYourTurn()) {
			games.get(gameID).getPlayer1().setYourTurn(false);
			games.get(gameID).getPlayer2().setYourTurn(true);
		} else {
			games.get(gameID).getPlayer1().setYourTurn(true);
			games.get(gameID).getPlayer2().setYourTurn(false);
		}
	}
	
	public void createGameStateId(String gameID) {
		games.get(gameID).setGameStateID(UUID.randomUUID().toString());
	}
	
	public boolean isMapValid(HalfMap halfmap) {
		if(halfmap.getNodes().size()
		return true;
	}
	
}
