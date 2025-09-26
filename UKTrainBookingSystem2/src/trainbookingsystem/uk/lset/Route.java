package trainbookingsystem.uk.lset;

public class Route {
    private String startStation;
    private String endStation;
    private double baseFare;

    public Route(String startStation, String endStation, double baseFare){
        this.startStation = startStation;
        this.endStation = endStation;
        this.baseFare = baseFare;
    }

    public String getStartStation() {
        return startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public double getBaseFare() {
        return baseFare;
    }

    @Override
    public String toString() {
        return getStartStation() + " -> " + getEndStation() + " (£" + getBaseFare() + ")";
    }
}
