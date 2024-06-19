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
    private int userId;
    private ArrayList<Venue> venues;
    public Location(int locationID, String locationName, String city, String address, String postalCode, Countries country, int userId){
        this.locationID = locationID;
        this.locationName = locationName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.venues = new ArrayList<>();
        this.userId = userId;
    }

    public Location(String locationName, String city, String address, String postalCode, Countries country, int userId){
        this.locationName = locationName;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.venues = new ArrayList<>();
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static class Venue{
        private int venueId;
        private int location_id;
        private String venueName;
        private int capacity;

        public Venue(int venueId, int location_id, String venueName, int capacity){
            this.venueId = venueId;
            this.location_id = location_id;
            this.venueName = venueName;
            this.capacity = capacity;
        }
        public Venue (int location_id, String venueName, int capacity){
            this.location_id = location_id;
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

        public int getLocation_id() {
            return location_id;
        }

        public void setLocation_id(int location_id) {
            this.location_id = location_id;
        }
    }
}
