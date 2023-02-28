package soccer;

import utility.GameUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Game {
   public Team homeTeam;
   public Team awayTeam;
   public Goal[] goals;
   public LocalDateTime theDateTime;

   public Game(Team homeTeam, Team awayTeam, LocalDateTime theDateTime) {
	  this.homeTeam = homeTeam;
	  this.awayTeam = awayTeam;
	  this.theDateTime = theDateTime;
   }

   public void playGame() {
	  playGame(6);
   }

   public void playGame(int maxGoals) {
	  int numberOfGoals = (int) (Math.random() * (maxGoals + 1));
	  this.goals = new Goal[numberOfGoals];
	  GameUtils.addGameGoals(this);
   }

   public String getDescription() {

	  int homeTeamGoals = 0;
	  int awayTeamGoals = 0;

	  StringBuilder returnString = new StringBuilder();
	  returnString.append(homeTeam.getTeamName() + " vs. " +
			  awayTeam.getTeamName() + "\n" +
			  " Date: " + this.theDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) + "\n");

	  for (Goal currGoal : this.goals) {
		 if (currGoal.getTheTeam() == homeTeam) {
			homeTeamGoals++;
			homeTeam.incGoalsTotal(1);
		 } else {
			awayTeamGoals++;
			awayTeam.incGoalsTotal(1);
		 }
		 returnString.append("Goal scored after "
				 + currGoal.getTheTime() + " mins by "
				 + currGoal.getThePlayer().getPlayerName() + " of "
				 + currGoal.getTheTeam().getTeamName() +
				 "\n");
	  }

	  if (homeTeamGoals == awayTeamGoals) {
		 returnString.append("It's a draw!" +
				 " (" + homeTeamGoals + " - " + awayTeamGoals + ")"
				 + "\n");
		 homeTeam.incPointsTotal(1);
		 awayTeam.incPointsTotal(1);
	  } else if (homeTeamGoals > awayTeamGoals) {
		 returnString.append(homeTeam.getTeamName() + " wins" +
				 " (" + homeTeamGoals + " - " + awayTeamGoals + ")"
				 + "\n");
		 homeTeam.incPointsTotal(2);
	  } else {
		 returnString.append(awayTeam.getTeamName() + " wins" +
				 " (" + homeTeamGoals + " - " + awayTeamGoals + ")"
				 + "\n");
		 awayTeam.incPointsTotal(2);
	  }

	  return returnString.toString();
   }

   public Team getHomeTeam() {
	  return homeTeam;
   }

   public void setHomeTeam(Team homeTeam) {
	  this.homeTeam = homeTeam;
   }

   public Team getAwayTeam() {
	  return awayTeam;
   }

   public void setAwayTeam(Team awayTeam) {
	  this.awayTeam = awayTeam;
   }

   public Goal[] getGoals() {
	  return goals;
   }

   public void setGoals(Goal[] goals) {
	  this.goals = goals;
   }

   public LocalDateTime getTheDateTime() {
	  return theDateTime;
   }

   public void setTheDateTime(LocalDateTime theDateTime) {
	  this.theDateTime = theDateTime;
   }
}
