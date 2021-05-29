package Entity;

public class ChatMessage { 
	  private String message; 
	  private ChatUser author; 
	  private String timestamp;  

	public ChatMessage(String message, ChatUser author, String timestamp) { 
	    super(); 
	    this.message = message; 
	    this.author = author; 
	    this.timestamp = timestamp;
	  } 
	   
	  public String getMessage() { 
	    return message; 
	  } 
	   
	  public void setMessage(String message) { 
		    this.message = message; 
		  } 
	   
	  public ChatUser getAuthor() { 
		    return author; 
		  } 
	   
	  public void setAuthor(ChatUser author) { 
	    this.author = author; 
	  } 
	   
	  public String getTimestamp() {
		  return timestamp; 
		  }
	   
	  public void setTimestamp(String timestamp) { 
	    this.timestamp = timestamp; 
	    }  
	  }  
