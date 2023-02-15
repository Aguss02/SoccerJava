package soccer;

import utility.GameUtils;

public class Game {
    public Team homeTeam;
    public Team awayTeam;
    public Goal[] goals;

    public void playGame() {
        playGame(6);
    }

    public void playGame(int maxGoals) {
        int numberOfGoals = (int)(Math.random() * (maxGoals + 1));
        this.goals = new Goal[numberOfGoals];
        GameUtils.addGameGoals(this);
    }

    public String getDescription() {
        StringBuilder returnString = new StringBuilder();
        for (Goal currGoal: this.goals) {
            returnString.append("Goal scored after "
                    + currGoal.theTime + " mins by "
                    + currGoal.thePlayer.playerName + " of "
                    + currGoal.theTeam.teamName +
                    "\n");
        }
        if (returnString.toString().isEmpty()){
            returnString.append("No goals were scored.");
        }
        return returnString.toString();
    }
}
