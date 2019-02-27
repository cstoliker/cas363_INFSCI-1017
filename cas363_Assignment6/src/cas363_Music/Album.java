package cas363_Music;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public class Album {
	
	//private variables--------------------------------------------------------------------------------------------=---------------------------//
	private String albumID, title, releaseDate, coverImagePath, recordingCompany, pmrcRating;
	private int numberOfTracks, length;
	private DbUtilities db;
	public Map<String, Song> albumSongs;
	
	//constructors----------------------------------------------------------------------------------------------------------------------------//
	public Album(String title, String releaseDate, String recordingCompany, int numberOfTracks) {
		db = new DbUtilities();
		this.title = title;
		this.releaseDate = releaseDate;
		this.recordingCompany = recordingCompany;
		this.numberOfTracks = numberOfTracks;
		UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		String sql = "INSERT INTO album (album_id, title, release_date, recording_company, number_of_tracks)"
				+ " VALUES ('"+ uid.randomUUID()+ "', '" + title + "', '" + releaseDate + "', '" + recordingCompany+ "', '" + numberOfTracks + "');";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public Album(String albumID) {
		db = new DbUtilities();
		this.albumID = albumID;
		String sql = "SELECT title, release_date, recording_company, number_of_tracks FROM album WHERE album_id = " + this.albumID + ";";
		try {
			ResultSet rs = db.getResultSet(sql);
			if (rs.next()) {
				this.title = rs.getString("title");
				this.releaseDate = rs.getString("release_date");
				this.recordingCompany = rs.getString("recording_company");
				this.numberOfTracks = rs.getInt("number_of_tracks");
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
		String sql = "UPDATE album SET title = '" + title + "' WHERE album_id =" + this.albumID + ";";
		this.title = title;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		String sql = "UPDATE album SET release_date = '" + releaseDate + "' WHERE album_id =" + this.albumID + ";";
		this.releaseDate = releaseDate;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getCoverImagePath() {
		return coverImagePath;
	}

	public void setCoverImagePath(String coverImagePath) {
		String sql = "UPDATE album SET cover_image_path = '" + coverImagePath + "' WHERE album_id =" + this.albumID + ";";
		this.coverImagePath = coverImagePath;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getRecordingCompany() {
		return recordingCompany;
	}

	public void setRecordingCompany(String recordingCompany) {
		String sql = "UPDATE album SET recording_company = '" + recordingCompany + "' WHERE album_id =" + this.albumID + ";";
		this.recordingCompany = recordingCompany;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getPmrcRating() {
		return pmrcRating;
	}

	public void setPmrcRating(String pmrcRating) {
		String sql = "UPDATE album SET pmrc_rating = '" + pmrcRating + "' WHERE album_id =" + this.albumID + ";";
		this.pmrcRating = pmrcRating;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getNumberOfTracks() {
		return numberOfTracks;
	}

	public void setNumberOfTracks(int numberOfTracks) {
		String sql = "UPDATE album SET number_of_tracks = '" + numberOfTracks + "' WHERE album_id =" + this.albumID + ";";
		this.numberOfTracks = numberOfTracks;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		String sql = "UPDATE album SET length = '" + length + "' WHERE album_id =" + this.albumID + ";";
		this.length = length;
		System.out.println(sql);
		db.executeQuery(sql);
	}

	public String getAlbumID() {
		return albumID;
	}
	
	//Methods-------------------------------------------------------------------------------------------------------------------------------//
	public void deleteAlbum(String songID) {
		String sql = "DELETE FROM album where song_id = " + songID + ";";
		System.out.println(sql);
		db.executeQuery(sql);
	}
	
	public void addSong(Song song) {
		String sql = "INSERT INTO album_song (fk_album_id, fk_song_id) values ('" + this.albumID + "', '" + song.getSongID() + "');";
		System.out.println(sql);
		db.executeQuery(sql);
		albumSongs.put(song.getSongID(), song);
		
	}
	
	public void deleteSong(String songID) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = " + songID + ";";
		System.out.println(sql);
		db.executeQuery(sql);
		albumSongs.remove(songID);
	}
	
	public void deleteSong(Song song) {
		String sql = "DELETE FROM album_song WHERE fk_song_id = " + song.getSongID() + ";";
		System.out.println(sql);
		db.executeQuery(sql);
		albumSongs.remove(song.getSongID());
	}
}
