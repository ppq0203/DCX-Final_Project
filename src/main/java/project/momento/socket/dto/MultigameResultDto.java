package project.momento.socket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultigameResultDto {
	private String roomId;
    private String teamNo;
    private int userNo;
    private String userName;
    private String type;
}
