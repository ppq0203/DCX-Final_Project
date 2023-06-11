package project.momento.socket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultigameResultDto {
	private String roomId;
    private String teamNo;
    private String UserNo;
    private String UserName;
    private String type;
}
