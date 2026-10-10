package app;

import models.*;

public class Index {
    public static void main(String[] args){

        Event PWE = new Event("PWE Shanghai Major 2024", "Shanghai", "01-12-2024", "16-12-2024", 'S', 1_200_000);
        
        Player Donk = new Player("AA0212121", "Donk", 18, "Spirit", 1_087_750);
        Donk.setPlayerStatistics(100, 100, 85, 100, 90, 10, 60);
        Donk.addMatch(new MatchResult("Furia", 2, 0));

        Donk.addTop20Achievement(2024, 1);
        Donk.addTop20Achievement(2025, 2);
        Donk.addTop20Achievement(2023, 1);
        // Donk.displayTop20Achievements();

        // System.out.println(Donk);

        Donk.addTrophy(PWE);
        // Donk.displayTrophies();

        Team Spirit = new Team("XS0708907", "Spirit", 877, "BetBoom", 5_500_000);
        Spirit.addTrophy(PWE);
        Spirit.addPlayer(Donk);
        System.out.println(Spirit);
    }
}
