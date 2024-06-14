package pj.s30566.model;

import pj.s30566.enums.Countries;

import java.awt.*;
import java.util.ArrayList;

public class Location {
    private int locationID;
    private String locationName;
    private String city;
    private String address;
    private String postalCode;
    private Countries country;
    private ArrayList<Venue> venues;
    public Location(int locationID, String locationName, String city, String address, String postalCode, Countries country){
        this.locationID = locationID;
        this.locationName = locationName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.venues = new ArrayList<>();
    }

    public Location(String locationName, String city, String address, String postalCode, Countries country){
        this.locationName = locationName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.venues = new ArrayList<>();
    }

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationID=" + locationID +
                ", locationName='" + locationName + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", venues=" + venues +
                '}';
    }

    public static class Venue{
        private int venueId;
        private String venueName;
        private int capacity;

        public Venue(int venueId, String venueName, int capacity){
            this.venueId = venueId;
            this.venueName = venueName;
            this.capacity = capacity;
        }

        public int getVenueId() {
            return venueId;
        }

        public void setVenueId(int venueId) {
            this.venueId = venueId;
        }

        public String getVenueName() {
            return venueName;
        }

        public void setVenueName(String venueName) {
            this.venueName = venueName;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return "Venue{" +
                    "venueId=" + venueId +
                    ", venueName='" + venueName + '\'' +
                    ", capacity=" + capacity +
                    '}';
        }
    }
}
