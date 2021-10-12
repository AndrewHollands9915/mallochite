package mallochite.models.classes;

import java.security.Key;
import java.util.ArrayList;

public class Contact {

	private String UUID;
	private String Username;
	private String ipAddress;
	private ArrayList<Message> messages = new ArrayList<Message>();
    private Key publicKey;
    
    public Key getPublicKey() {
    	return publicKey;
    }
    
    public void setPublicKey(Key key) {
    	publicKey = key;
    }
	
	
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	//re
	public ArrayList<Message> getMessages() {
		return messages;
	}
	public void setMessages(ArrayList<Message> messages) {
		this.messages = messages;
	}
	
}





