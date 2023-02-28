package soccer;

import utility.PlayerDatabase;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class League {

   public static void main(String[] args) {

	  Team[] theTeams = createTeams("The Reds, The Greens, The Blue", 3);
	  Game[] theGames = createGames(theTeams);

	  System.out.println(League.getLeagueAnnouncement(theGames));

	  for (Game currGame : theGames) {
		 currGame.playGame();
		 System.out.println(currGame.getDescription());
	  }

	  showBestTeam(theTeams);

   }

   public static void showBestTeam(Team[] theTeams) {

	  Team currBestTeam = theTeams[0];

	  System.out.println("\nTeam Points");
	  for (Team currTeam : theTeams) {
		 System.out.println(currTeam.getTeamName() + ": " + currTeam.getPointsTotal() + ":" + currTeam.getGoalsTotal());
		 if (currTeam.getGoalsTotal() > currBestTeam.getPointsTotal()) {
			currBestTeam = currTeam;
		 } else if (currTeam.getPointsTotal() == currBestTeam.getPointsTotal()) {
			if (currTeam.getGoalsTotal() > currBestTeam.getGoalsTotal()) {
			   currBestTeam = currTeam;
			}
		 }
	  }

	  System.out.println("\nWinner of the league is " + currBestTeam.getTeamName());
   }


   public static Team[] createTeams(String teamNames, int teamSize) {
	  PlayerDatabase playerDB = new PlayerDatabase();
	  StringTokenizer teamNameTokens = new StringTokenizer(teamNames, ",");
	  Team[] theTeams = new Team[teamNameTokens.countTokens()];

	  for (int i = 0; i < theTeams.length; i++) {
		 theTeams[i] = new Team(teamNameTokens.nextToken(), playerDB.getTeam(teamSize));
	  }

	  return theTeams;
   }

   public static Game[] createGames(Team[] theTeams) {
	  ArrayList<Game> theGames = new ArrayList<>();
	  int daysBetweenGames = 0;
	  for (Team homeTeam : theTeams) {
		 for (Team awayTeam : theTeams) {
			if (homeTeam != awayTeam) {
			   daysBetweenGames += 7;
			   theGames.add(new Game(homeTeam, awayTeam, LocalDateTime.now().plusDays(daysBetweenGames)));
			}
		 }
	  }
	  return theGames.toArray(new Game[1]);
   }

   public static String getLeagueAnnouncement(Game[] theGames) {
	  Period thePeriod = Period.between(theGames[0].getTheDateTime().toLocalDate(), theGames[theGames.length - 1].getTheDateTime().toLocalDate());
	  return "The League is scheduled to run for " + thePeriod.getMonths() +
			  " month(s), and " + thePeriod.getDays() + " day(s).\n";
   }
}
