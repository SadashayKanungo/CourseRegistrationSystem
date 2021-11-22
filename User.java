public class User{
  private String id;
  private String password;
  private String name;
//   private static User[] userList = new User[1000];
//   private static int counter = 0;
  User(String id, String password, String name){
	  this.id = id;
	  this.password = password;
	  this.name = name;
  }


  protected boolean verify(String pwd_input) {
	//   for(User element : userList) {
	// 	  if(element == null)
	// 		  return false;
	// 	  if(this.id.equals(element.id)) {
	if(this.password.equals(pwd_input)) {
		return true;
	}
	else{
		return false;
	}
  }
  public String getID() {
	//System.out.println(this.id);
	return this.id;
  }
  public String getName() {
	//System.out.println(this.name);
	return this.name;
  }
//  public void add(User obj) {
// 	userList[counter] = obj;
// 	counter++;
//   }
}

