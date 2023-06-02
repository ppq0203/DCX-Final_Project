package project.momento.room.controller;

import javax.swing.Spring;

import org.springframework.context.annotation.PropertySource;

import groovy.lang.GString;
import lombok.Data;

@Data
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
/*방*/
public class RoomController {
	
	private Spring roomName;
	private int roomPwd;
	private Spring roomType;
	private int roomPlay;
	private Spring roomPlayTime;
	private String roomId;
	private Spring roomTime;
	private Spring roomUpDate;
	
	public void name() {
		
	}
	
	
}