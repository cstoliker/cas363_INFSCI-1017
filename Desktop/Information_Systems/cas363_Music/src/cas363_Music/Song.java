package cas363_Music;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.util.*;

public class Song {
	
	//private variables--------------------------------------------------------------------------------------------=---------------------------//
	private String songID, title, filePath, releaseDate, recordDate;
	private int length;
	private DbUtilities db;
	
	//constructors----------------------------------------------------------------------------------------------------------------------------//	
	public Song(String title, int length, String releaseDate, String recordDate) {
		db = new DbUtilities();
		this.title = title;
		this.length = length;
		this.releaseDate = releaseDate;
		this.recordDate = recordDate;
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d"); 
		String sql = "INSERT INTO song (song_id, title, length, release_date, record_date)"
				+ " VALUES ('"+ uid.randomUUID() + "', '" + title + "', '" + length + "', '" + releaseDate + "', '" + recordDate + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public Song(String songID) {
		db = new DbUtilities();
		this.songID = songID;
		String sql = "SELECT title, length, release_date, record_date FROM song WHERE song_id = " + this.songID + ";";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) {
				this.title = rs.getString("title");
				this.length = rs.getInt("length");
				this.releaseDate = rs.getString("release_date");
				this.recordDate = rs.getString("record_date");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//getters and setters for private variables-----------------------------------------------------------------------------------------------//
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		String sql = "UPDATE song SET title = '" + title + "' WHERE song_id =" + this.songID + ";";
		this.title = title;
		db.executeQuery(sql);
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		String sql = "UPDATE song SET file_path = '" + filePath + "' WHERE song_id =" + this.songID + ";";
		this.filePath = filePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE song SET release_date = '" + releaseDate + "' WHERE song_id =" + this.songID + ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		String sql = "UPDATE song SET record_date = '" + recordDate + "' WHERE song_id =" + this.songID + ";";
		this.recordDate = recordDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "UPDATE song SET length = '" + length + "' WHERE song_id =" + this.songID + ";";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getSongID() {
		return songID;
	}

	//Map------------------------------------------------------------------------------------------------------------------------------------//
	public interface Map<String, Artist> {
		
	}
	
	//Methods-------------------------------------------------------------------------------------------------------------------------------//
	public void deleteSong(String songID) {
		String sql = "DELETE FROM song WHERE song_id = " + songID + ";";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public void addArtist(Artist artist) {
		String sql = "INSERT INTO song_artist (fk_song_id, fk_artist_id) VALUES ('" + this.songID + "', ' " + artist.getArtistID() + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public void deleteArtist(String artistID) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = " + artistID + ";";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public void deleteArtist(Artist artist) {
		String sql = "DELETE FROM song_artist WHERE fk_artist_id = " + artist.getArtistID() + ";";
		System.out.println(sql);
		db.executeQuery(sql);
	}
}
