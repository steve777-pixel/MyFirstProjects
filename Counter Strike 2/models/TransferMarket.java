package models;

import java.util.HashMap;
import java.util.Map;

public class TransferMarket{

    private Map<String, Player> freeAgents;

    public TransferMarket(Player... players){
        this.freeAgents = new HashMap<>();

        if(players != null){
            for(Player player : players){
                if(player != null){
                    freeAgents.put(player.getID(), player);
                }
            }
        }
    }
    
    public static void executeTransfer(Player player, Team seller, Team buyer, int transferFee){
        if(player == null || seller == null || buyer == null){
            throw new IllegalArgumentException("Player, seller, or buyer cannot be null.");
        }
        if(seller.equals(buyer)){
            throw new IllegalArgumentException("The seller and buyer teams cannot be the same.");
        }
        seller.searchPlayerByID(player.getID());
        if(transferFee < 0){
            transferFee = 0;
        }
        if(buyer.getBudget() < transferFee){
            throw new IllegalStateException(String.format("Insufficient budget!\nBuyer team budget: %d\nTransfer fee: %d", buyer.getBudget(), transferFee));
        }
        seller.removePlayer(player.getID());
        buyer.addPlayer(player);
        player.setCurrentTeam(buyer.getName());

        buyer.subtractBudget(transferFee);
        seller.addBudget(transferFee);
    }   

    public void benchPlayer(Team team, Player player){
        if(team == null || player == null){
            throw new IllegalArgumentException("Player or team cannot be null.");
        }
        team.searchPlayerByID(player.getID());
        team.removePlayer(player.getID());
        freeAgents.put(player.getID(), player);
        player.setCurrentTeam("None");
    }

}