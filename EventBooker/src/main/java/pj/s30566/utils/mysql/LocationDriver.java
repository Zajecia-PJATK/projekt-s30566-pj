package pj.s30566.utils.mysql;

import pj.s30566.enums.Countries;
import pj.s30566.enums.EventStatus;
import pj.s30566.enums.EventType;
import pj.s30566.model.Event;
import pj.s30566.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(LocationDriver.class);
    private static final String INSERT_LOCATION_SQL = "INSERT INTO Locations (address, city, country, location_name, postal_code) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_VENUE_SQL = "INSERT INTO Venues (capacity, location_id, venue_name) VALUES (?, ?, ?)";
    private static final String GET_LOCATION_SQL = "SELECT * FROM Locations WHERE location_name = ?";

    public LocationDriver(){

    }

    public void addLocation(Location location) throws SQLException{
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_LOCATION_SQL)){
            preparedStatement.setString(1, location.getAddress());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getCountry().name());
            preparedStatement.setString(4, location.getLocationName());
            preparedStatement.setString(5, location.getPostalCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding location {}", location.getLocationName(), e);
        }
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
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(GET_LOCATION_SQL)){
            preparedStatement.setString(1, name);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()){
                locationId = result.getInt("location_id");
                locationAddress = result.getString("address");
                city = result.getString("city");
                country = Countries.valueOf(result.getString("country")) ;
                postalCode = result.getString("postal_code");

                location = new Location(locationId, name, city, locationAddress, postalCode, country);
            } else {
                return null;
            }
        } catch (SQLException e) {
            logger.error("An error occurred while getting events ", e);
            return null;
        }
        return location;
    }


}

