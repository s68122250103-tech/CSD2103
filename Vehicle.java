package models;

public class Vehicle {
    private String licensePlate;
    private String ownerName;
    private String entryTime;
    private String vehicleType;

    public Vehicle(String licensePlate, String ownerName, String entryTime, String vehicleType) {
        this.licensePlate = licensePlate;
        this.ownerName = ownerName;
        this.entryTime = entryTime;
        this.vehicleType = vehicleType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    @Override
    public String toString() {
        return "[" + licensePlate + "]";
    }
}