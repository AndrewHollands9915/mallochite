package mallochite.models.classes;

public class Message {

	//declare
	private int ID;
	private String text;
	private String date;
	private int sent;
	private int ReadRecipent;
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}
	public int getReadRecipent() {
		return ReadRecipent;
	}
	public void setReadRecipent(int readRecipent) {
		ReadRecipent = readRecipent;
	}
	
	public Message(int iD, String text, String date, int sent, int readRecipent) {
		ID = iD;
		this.text = text;
		this.date = date;
		this.sent = sent;
		ReadRecipent = readRecipent;
	}
	
}
