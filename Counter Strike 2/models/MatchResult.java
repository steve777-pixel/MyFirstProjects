package models;

import utils.Validation;
import java.util.Objects;

public class MatchResult{
    private final String opponent;
    private final int playerTeamScore;
    private final int opponentScore;

    public MatchResult(String opponent, int playerTeamScore, int opponentScore){
        this.opponent = Validation.validateNotBlank(opponent, "Opposing team name");
        this.playerTeamScore = Validation.validateNotNegative(playerTeamScore, "Player team score");
        this.opponentScore = Validation.validateNotNegative(opponentScore, "Opposing team score");
        if(playerTeamScore == opponentScore){
            throw new IllegalArgumentException("Match cannot end in a draw!");
        }
    }

    public String getOpponent(){
        return opponent;
    }

    public int getPlayerTeamScore(){
        return playerTeamScore;
    }

    public int getOpposingTeamScore(){
        return opponentScore;
    }

    @Override 
    public String toString(){
        if(playerTeamScore > opponentScore){
            return String.format("%s : %d-%d (Win)", opponent, playerTeamScore, opponentScore);
        }
        // No draw!
        return String.format("%s : %d-%d (Loss)", opponent, playerTeamScore, opponentScore);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        MatchResult other = (MatchResult) obj;
        return Objects.equals(this.opponent, other.opponent) && this.playerTeamScore == other.playerTeamScore && this.opponentScore == other.opponentScore;
    }

    @Override
    public int hashCode(){
        return Objects.hash(opponent, playerTeamScore, opponentScore);
    }
}