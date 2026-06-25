package models;

import utils.Difficulty;
import utils.Validation;
import java.util.Objects;

public class Mission{
    private final String code;
    private final String title;
    private final Difficulty difficulty;
    private final int reward;

    public Mission(String code, String title, Difficulty difficulty, int reward){
        this.code = Validation.validateNotBlank(code, "Mission code");
        this.title = Validation.validateNotBlank(title, "Mission title");
        this.difficulty = Objects.requireNonNull(difficulty, "Difficulty cannot be null.");
        if(!difficulty.isValidCredit(reward)){
            throw new IllegalArgumentException(String.format("Invalid reward (%d credits) for %s-rank mission. Allowed range: %d - %d credits.", reward, difficulty.name(), difficulty.getMinCredits(), difficulty.getMaxCredits()));
        }
        this.reward = reward;
    }

    public String getCode(){
        return code;
    }

    public String getTitle(){
        return title;
    }

    public Difficulty geDifficulty(){
        return difficulty;
    }

    public int getReward(){
        return reward;
    }

    @Override
    public String toString(){
        return String.format("Title: %s\nCode: %s\nDifficulty: %s (up to %d credits)\nReward: %d", title, code, difficulty.getDescription(), difficulty.getMaxCredits(), reward);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Mission other = (Mission) obj;
        return Objects.equals(this.code, other.code);
    }

    @Override
    public int hashCode(){
        return Objects.hash(code);
    }
}