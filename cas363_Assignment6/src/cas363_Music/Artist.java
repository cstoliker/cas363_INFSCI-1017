package cas363_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Artist {
	
	//private variables--------------------------------------------------------------------------------------------=---------------------------//
	private String artistID, firstName, lastName, bandName, bio;
	private DbUtilities db;
	
	//constructors----------------------------------------------------------------------------------------------------------------------------//
	public Artist(String firstName, String lastName, String bandName) {
		db = new DbUtilities();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bandName = bandName;
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String sql = "INSERT INTO artist (artist_id, first_name, last_name, band_name)"
				+ " VALUES ('" + uid.randomUUID() + "', '" + firstName + "', '" + lastName + "', '" + bandName + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public Artist(String artistID) {
		db = new DbUtilities();
		this.artistID = artistID;	
		String sql = "SELECT first_name, last_name, band_name FROM artist WHERE artist_id = " + this.artistID + ";";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) {
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.bandName = rs.getString("band_name");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//getters and setters for private variables-----------------------------------------------------------------------------------------------//
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		String sql = "UPDATE artist SET first_name = '" + firstName + "' WHERE artist_id =" + this.artistID + ";";
		this.firstName = firstName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		String sql = "UPDATE artist SET last_name = '" + lastName + "' WHERE artist_id =" + this.artistID + ";";
		this.lastName = lastName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		String sql = "UPDATE artist SET band_name = '" + bandName + "' WHERE artist_id =" + this.artistID + ";";
		this.bandName = bandName;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		String sql = "UPDATE artist SET bio = '" + bio + "' WHERE artist_id =" + this.artistID + ";";
		this.bio = bio;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getArtistID() {
		return artistID;
	}
	
	//Methods-------------------------------------------------------------------------------------------------------------------------------//
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM artist WHERE artist_id = " + artistID + ";";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
