package pj.s30566.utils.mysql;

import pj.s30566.enums.Countries;

import pj.s30566.model.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pj.s30566.model.user.User;

public class LocationDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(LocationDriver.class);
    private static final String INSERT_LOCATION_SQL = "INSERT INTO Locations (address, city, country, location_name, postal_code, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_VENUE_SQL = "INSERT INTO Venues (capacity, location_id, venue_name) VALUES (?, ?, ?)";
    private static final String GET_LOCATION_SQL = "SELECT * FROM Locations WHERE location_name = ?";
    private static final String GET_VENUES_BY_LOCATION_ID = "SELECT * FROM Venues WHERE location_id = ?";
    private static final String GET_ALL_LOCATIONS_BY_USER_ID = "SELECT * FROM Locations WHERE user_id = ?";

    public LocationDriver(){

    }

    public void addLocation(Location location) throws SQLException{
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_LOCATION_SQL)){
            preparedStatement.setString(1, location.getAddress());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getCountry().name());
            preparedStatement.setString(4, location.getLocationName());
            preparedStatement.setString(5, location.getPostalCode());
            preparedStatement.setInt(6, location.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding location {}", location.getLocationName(), e);
        }
    }

    public List<Location> getLocationsByUser(int userID) {
        int locationId;
        String address;
        String city;
        Countries country;
        String locationName;
        String postalCode;
        ResultSet result;
        List<Location> locations = new ArrayList<>();
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(GET_ALL_LOCATIONS_BY_USER_ID)){
            preparedStatement.setInt(1, userID);
            result = preparedStatement.executeQuery();
            while (result.next()){
                locationId = result.getInt("location_id");
                locationName = result.getString("location_name");
                city = result.getString("city");
                address = result.getString("address");
                postalCode = result.getString("postal_code");
                country = Countries.valueOf(result.getString("country"));

                Location location = new Location(locationId, locationName, city, address, postalCode, country, userID);
                locations.add(location);
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting events ", e);
        }
        return locations;
    }

    public void addVenue(Location.Venue venue) throws SQLException{
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_VENUE_SQL)){
            preparedStatement.setInt(1, venue.getCapacity());
            preparedStatement.setInt(2, venue.getLocation_id());
            preparedStatement.setString(3, venue.getVenueName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding venue {}", venue.getVenueName(), e);
        }
    }

    public Location getLocationByName(String name){
        int locationId;
        String locationAddress;
        String city;
        Countries country;
        String postalCode;
        Location location;
        int userId;
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(GET_LOCATION_SQL)){
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                locationId = result.getInt("location_id");
                locationAddress = result.getString("address");
                city = result.getString("city");
                country = Countries.valueOf(result.getString("country")) ;
                postalCode = result.getString("postal_code");
                userId = result.getInt("user_id");


                location = new Location(locationId, name, city, locationAddress, postalCode, country, userId);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting events ", e);
            return null;
        }
        return location;
    }

    public List<Location.Venue> getVenuesByLocationID(int locationId){
        int capacity;
        int venueId;
        String venueName;
        List<Location.Venue> venues = new ArrayList<>();
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(GET_VENUES_BY_LOCATION_ID)){
            preparedStatement.setInt(1, locationId);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                capacity = result.getInt("capacity");
                venueId = result.getInt("venue_id");
                venueName = result.getString("venue_name");

                Location.Venue venue = new Location.Venue(venueId, locationId, venueName, capacity);
                venues.add(venue);
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting venue ", e);
            return null;
        }
        return venues;

    }

}

