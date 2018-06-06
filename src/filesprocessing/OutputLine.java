package filesprocessing;

public class OutputLine {

	/**
	 * enum of available statuses
	 */
	public enum STATUS {
		VALID, WARNING, ERROR
	}
	
	private String text;
	
	private STATUS status;

	/**
	 * Class' Constructor
	 * @param text the text of the line
	 * @param status the status of the line
	 */
	public OutputLine(String text, STATUS status) {
		this.text = text;
		this.status = status;
	}

	@Override
	public String toString() {
		return this.text;
	}
}
