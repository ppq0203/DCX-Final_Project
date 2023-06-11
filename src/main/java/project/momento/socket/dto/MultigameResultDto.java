package project.momento.socket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MultigameResultDto {
	private String roomId;
    private String teamNum;
    private String UserNum;
    private String type;
}
