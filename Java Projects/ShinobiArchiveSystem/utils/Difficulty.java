package utils;

import java.util.Arrays;
import java.util.Optional;

public enum Difficulty{
    D(1_000, 9_999, "Beginner mission"),
    C(10_000, 49_999, "Intermediate mission"),
    B(50_000, 99_999, "Advanced mission"),
    A(100_000, 999_999, "Highly advanced mission"),
    S(1_000_000, Integer.MAX_VALUE, "Extremely advanced mission");

    private final int minCredits;
    private final int maxCredits;
    private final String description;

    Difficulty(int minCredits, int maxCredits, String description){
        this.minCredits = minCredits;
        this.maxCredits = maxCredits;
        this.description = description;
    }

    public int getMinCredits(){
        return minCredits;
    }

    public int getMaxCredits(){
        return maxCredits;
    }

    public String getDescription(){
        return description;
    }

    public boolean isValidCredit(int credits){
        return credits >= this.minCredits && credits <= this.maxCredits;
    }

    public static Optional<Difficulty> fromCredits(int credits){
        return Arrays.stream(values()).filter(diff -> diff.isValidCredit(credits)).findFirst();
    }
}