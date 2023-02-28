package utility;

import soccer.Game;
import soccer.Goal;
import soccer.Team;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class GameUtils {

   public static void addGameGoals(Game currGame) {

	  // Or possibly throw an Exception?
	  if (currGame.goals == null) {
		 currGame.goals = new Goal[(int) (Math.random() * 10)];   // If goals not initialized max will be 9
	  }

	  int i = 0;

	  for (Goal currGoal : currGame.goals) {
		 currGoal = new Goal();
		 currGoal.setTheTeam(Math.random() > 0.5 ? getHomeTeam(currGame, "home") : getHomeTeam(currGame, "away"));
		 currGoal.setThePlayer(currGoal.getTheTeam().getPlayerArray()[(int) (Math.random() * currGoal.getTheTeam().getPlayerArray().length)]);
		 currGoal.setTheTime((int) (Math.random() * 90));
		 currGame.goals[i] = currGoal;
		 i++;
	  }
	  Arrays.sort(currGame.goals, Comparator.comparing(g -> g.getTheTime()));

   }

   // Uses reflection so works with getter method or public field
   private static Team getHomeTeam(Game currGame, String homeOrAway) {
	  Team theTeam = null;
	  Method m;
	  Field f;
	  try {
		 m = Game.class.getMethod("get" + Character.toUpperCase(homeOrAway.charAt(0)) + homeOrAway.substring(1) + "Team");
		 theTeam = (Team) m.invoke(currGame);
	  } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException em) {
		 try {
			f = Game.class.getField(homeOrAway + "Team");
			theTeam = (Team) f.get(currGame);
		 } catch (NoSuchFieldException | IllegalAccessException ef) {
			System.out.println("""
					The addGoals() utility requires the Goal class to contain either:
					public String fields called homeTeam and awayTeam, OR,
					public accessor methods called getHomeTeam() and getAwayTeam().""");
		 }
	  }
	  return theTeam;
   }
}
