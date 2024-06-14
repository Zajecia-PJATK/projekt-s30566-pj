package pj.s30566.utils.mysql;

import pj.s30566.model.Location;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocationDriver extends MysqlDriver {
    private static final Logger logger = LoggerFactory.getLogger(LocationDriver.class);
    private static final String INSERT_LOCATION_SQL = "INSERT INTO Locations (name, address, city, postal_code, country) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_VENUE_SQL = "INSERT INTO Venues (venue_name, capacity, location_id) VALUES (?, ?, ?)";
    public LocationDriver(){

    }

    public void addLocation(Location location) throws SQLException{
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_LOCATION_SQL)){
            preparedStatement.setString(1, location.getLocationName());
            preparedStatement.setString(2, location.getAddress());
            preparedStatement.setString(3, location.getCity());
            preparedStatement.setString(4, location.getPostalCode());
            preparedStatement.setString(5, location.getCountry().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding location {}", location.getLocationName(), e);
        }
    }

    public void addVenue(Location.Venue venue) throws SQLException{
        try (PreparedStatement preparedStatement = super.getConnection().prepareStatement(INSERT_VENUE_SQL)){
            preparedStatement.setString(1, venue.getVenueName());
            preparedStatement.setInt(2, venue.getCapacity());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while adding venue {}", venue.getVenueName(), e);
        }
    }
}

