package models;

import utils.Validation;
import java.util.Objects;

public class Statistics{
    private double rating; // average of all
    private int firepower;
    private int entrying;
    private int trading;
    private int opening;
    private int clutching;
    private int sniping;
    private int utility;

    public Statistics(int firepower, int entrying, int trading, int opening, int clutching, int sniping, int utility){
        this.firepower = Validation.validateBetween(firepower, 0, 100, "Firepower");
        this.entrying = Validation.validateBetween(entrying, 0, 100, "Entrying");
        this.trading = Validation.validateBetween(trading, 0, 100, "Trading");
        this.opening = Validation.validateBetween(opening, 0, 100, "Opening");
        this.clutching = Validation.validateBetween(clutching, 0, 100, "Clutching");
        this.sniping = Validation.validateBetween(sniping, 0, 100, "Sniping");
        this.utility = Validation.validateBetween(utility, 0, 100, "Utility");
        recalculateRating();
    }

    private void recalculateRating(){
        this.rating = (firepower + entrying + trading + opening + clutching + sniping + utility) / 7.0;
    }

    public double getRating(){
        return rating;
    }

    public int getFirepower(){
        return firepower;
    }

    public int getEntrying(){
        return entrying;
    }

    public int getTrading(){
        return trading;
    }

    public int getOpening(){
        return opening;
    }

    public int getClutching(){
        return clutching;
    }

    public int getSniping(){
        return sniping;
    }

    public int getUtility(){
        return utility;
    }

    public void setFirepower(int firepower){
        this.firepower = Validation.validateBetween(firepower, 0, 100, "Firepower");
        recalculateRating();
    }

    public void setEntrying(int entrying) {
        this.entrying = Validation.validateBetween(entrying, 0, 100, "Entrying");
        recalculateRating();
    }

    public void setTrading(int trading) {
        this.trading = Validation.validateBetween(trading, 0, 100, "Trading");
        recalculateRating();
    }

    public void setOpening(int opening) {
        this.opening = Validation.validateBetween(opening, 0, 100, "Opening");
        recalculateRating();
    }

    public void setClutching(int clutching) {
        this.clutching = Validation.validateBetween(clutching, 0, 100, "Clutching");
        recalculateRating();
    }

    public void setSniping(int sniping) {
        this.sniping = Validation.validateBetween(sniping, 0, 100, "Sniping");
        recalculateRating();
    }

    public void setUtility(int utility) {
        this.utility = Validation.validateBetween(utility, 0, 100, "Utility");
        recalculateRating();
    }

    public void updateAll(int firepower, int entrying, int trading, int opening, int clutching, int sniping, int utility){
        this.firepower = Validation.validateBetween(firepower, 0, 100, "Firepower");
        this.entrying = Validation.validateBetween(entrying, 0, 100, "Entrying");
        this.trading = Validation.validateBetween(trading, 0, 100, "Trading");
        this.opening = Validation.validateBetween(opening, 0, 100, "Opening");
        this.clutching = Validation.validateBetween(clutching, 0, 100, "Clutching");
        this.sniping = Validation.validateBetween(sniping, 0, 100, "Sniping");
        this.utility = Validation.validateBetween(utility, 0, 100, "Utility");
        recalculateRating();
    }

    public void resetAll(){
        updateAll(0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public String toString() {
        return String.format(
            "Firepower  : %d%n" +
            "Entrying   : %d%n" +
            "Trading    : %d%n" +
            "Opening    : %d%n" +
            "Clutching  : %d%n" +
            "Sniping    : %d%n" +
            "Utility    : %d%n" +
            "----------------%n" +
            "Overall    : %.2f", 
            firepower, entrying, trading, opening, clutching, sniping, utility, rating
        );
    }

    @Override 
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Statistics other = (Statistics) obj;
        return this.firepower == other.firepower && this.entrying == other.entrying && this.trading == other.trading && this.opening == other.opening && this.clutching == other.clutching && this.sniping == other.sniping && this.utility == other.utility;
    }

    @Override
    public int hashCode(){
        return Objects.hash(firepower, entrying, trading, opening, clutching, sniping, utility);
    }
}