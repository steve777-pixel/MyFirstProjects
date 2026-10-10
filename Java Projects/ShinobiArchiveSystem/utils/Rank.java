package utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;;

public enum Rank{
    GENIN(1, "Junior Ninja"),
    CHUNIN(2, "Journeyman Ninja"), 
    JONIN(3, "Elite Ninja"),
    ANBU(4, "Special Ops"),
    KAGE(5, "Village Leader");

    private final int level;
    private final String description;

    private static final Map<Integer, Rank> BY_LEVEL = Arrays.stream(values()).collect(Collectors.toUnmodifiableMap(Rank::getLevel, Function.identity()));

    private Rank(int level, String description){
        this.level = level;
        this.description = description;
    }

    public int getLevel(){
        return level;
    }

    public String getDescription(){
        return description;
    }

    public static Optional<Rank> fromLevel(int level){
        return Optional.ofNullable(BY_LEVEL.get(level));
    }

    public boolean isGenin(){ return this == GENIN; }

    public boolean isChunin(){ return this == CHUNIN; }

    public boolean isJonin(){ return this == JONIN; }

    public boolean isAnbu(){ return this == ANBU; }

    public boolean isKage(){ return this == KAGE; }

    @Override
    public String toString(){
        return String.format("%s (Level %d: %s)", name(), level, description);
    }
}