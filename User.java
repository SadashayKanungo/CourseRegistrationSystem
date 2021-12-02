public class User{
  private String id;
  private String password;
  private String name;
  User(String id, String password, String name){
	  this.id = id;
	  this.password = password;
	  this.name = name;
  }

  protected boolean verify(String pwd_input) {
	if(this.password.equals(pwd_input)) {
		return true;
	}
	else{
		return false;
	}
  }
  public String getID() {
	return this.id;
  }
  public String getName() {
	return this.name;
  }
}

