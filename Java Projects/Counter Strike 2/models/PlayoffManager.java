package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PlayoffManager{
    private final Event event;
    private final List<Team> brackets; // exactly 8 teams
    private final List<Team> semifinals;
    private final List<Team> finals;
    private Team champion;

    public PlayoffManager(Event event){
        if(event == null){
            throw new IllegalArgumentException("Event cannot be null.");
        }
        this.event = event;
        this.brackets = new ArrayList<>(event.getTeams());
        if(this.brackets.size() != 8){
            throw new IllegalStateException(String.format("Playoffs require exactly 8 teams.\nCurrent team count: %d", this.brackets.size()));
        }
        this.semifinals = new ArrayList<>();
        this.finals = new ArrayList<>();
        this.champion = null;
    }

    private Team simulateMatch(Team teamA, Team teamB){
        double ratingA = teamA.getAverageRating();
        double ratingB = teamB.getAverageRating();

        double winChance = 0.5 + (ratingA - ratingB);

        double roll = ThreadLocalRandom.current().nextDouble();
        Team winner = (roll < winChance) ? teamA : teamB;
        Team loser = (winner == teamA) ? teamB : teamA;
        int winnerScore = 2;
        int loserScore = ThreadLocalRandom.current().nextBoolean() ? 1 : 0;

        winner.addMatch(new MatchResult(loser.getName(), winnerScore, loserScore));
        for(Player player : winner.getPlayers()){
            player.addMatch(new MatchResult(loser.getName(), winnerScore, loserScore));
        }
        loser.addMatch(new MatchResult(winner.getName(), loserScore, winnerScore));
        for(Player player : loser.getPlayers()){
            player.addMatch(new MatchResult(winner.getName(), loserScore, winnerScore));
        }

        winner.addPoints((int) Math.round(loser.getAverageRating()/2.0));
        loser.subtractPoints(100 - (int) winner.getAverageRating());

        System.out.println(String.format("   [MATCH] %s vs %s -> %s wins (%d-%d)%n", teamA.getName(), teamB.getName(), winner.getName(), winnerScore, loserScore));

        return winner;
    }

    public void runPlayoffs(){
        System.out.println("\n========================================");
        System.out.println("STARTING PLAYOFFS FOR: " + event.getName());
        System.out.println("========================================");

        Collections.shuffle(brackets);

        System.out.println("\n--- QUARTERFINALS ---");
        for(int i = 0; i < brackets.size(); i += 2){
            Team winner = simulateMatch(brackets.get(i), brackets.get(i+1));
            semifinals.add(winner);
        }

        System.out.println("\n--- SEMIFINALS ---");
        for(int i = 0; i < semifinals.size(); i += 2){
            Team winner = simulateMatch(semifinals.get(i), semifinals.get(i+1));
            finals.add(winner);
        }

        System.out.println("\n--- GRAND FINALS ---");
        champion = simulateMatch(finals.get(0), finals.get(1));

        processTournamentRewards();
    }

    private void processTournamentRewards(){
        System.out.println("\n========================================");
        System.out.println("🏆 TOURNAMENT CHAMPION: " + champion.getName() + " 🏆");
        System.out.println("========================================");

        champion.addTrophy(this.event);

        champion.addBudget(event.getPrizeMoney());
        System.out.println(String.format("   %s awarded $%d prize money! New budget: $%d%n", champion.getName(), event.getPrizeMoney(), champion.getBudget()));

        switch(event.getTier()){
            case 'S':
                champion.addPoints(500);
                break;
            case 'A':
                champion.addPoints(400);
                break;
            case 'B':
                champion.addPoints(300);
                break;
            case 'C':
                champion.addPoints(200);
                break;
            case 'D':
                champion.addPoints(100);
                break;
        }

        List<Player> winningPlayers = champion.getPlayers();
        Player MVP = winningPlayers.stream().max(Comparator.comparing(Player::getRating)).orElseThrow();
        event.setMVP(MVP);

        System.out.println("⭐ TOURNAMENT MVP: " + event.getMVP().getName());
    }
}