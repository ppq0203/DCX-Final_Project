package project.momento.test.controller;

public class CompileDTO {
	private String source;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public CompileDTO(String source) {
		super();
		this.source = source;
	}

	@Override
	public String toString() {
		return "CompileDTO [source=" + source + "]";
	}
}
