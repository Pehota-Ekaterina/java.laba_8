package Entity;

public class ChatUser { 
	  private String name; 
	 
	  private String sessionId; 
	  private static int Kol = 0; 
	  
	   
	  public ChatUser(String name,String sessionId) { 
		    super(); 
		    this.name = name; 
		 
		    this.sessionId = sessionId;
		    Kol+=1;
		  } 
	 
	  public static int getKol() {
			return Kol;
		}

		public static void setKol(int kol) {
			Kol -= kol;
		}

		public String getName() { 
		    return name; 
		  } 
		 
		  public void setName(String name) { 
		    this.name = name; 
		  } 
		 
		 
		  public String getSessionId() { 
			    return sessionId; 
			  } 
		 
		  public void setSessionId(String sessionId) { 
			    this.sessionId = sessionId; 
			  }   
	} 