public class RowProcessor {

	public static Person process(String row){
		
		String[] cells = cells(row);
		return new Person(cells[0], cells[1], cells[2]);
	}
	
	private static String[] cells(String row){
	
		String[] cells = new String[3]; 
		cells[0] =	removeSpace(clean(row.split(":")[0]));
		String secondPart = clean(row.split(":")[1]);
		cells[1] = tel(secondPart);
 		cells[2] = mail(secondPart);
		return cells;
	}
	
	public static String clean(String row){
	
		return row.replace("\"", "");
	}

	public static String tel(String secondPart){

		String tel = secondPart.split(",")[0];
		tel = removeSpace(tel);
		if(tel.length() == 0){
			return "EMPTY";
		} else {
			return tel;	
		}
	}
	
	public static String mail(String secondPart){
		
		String mail = new String();
		if(secondPart.split(",").length > 1){
			mail = secondPart.split(",")[1];
			//Remove first space
			mail = removeSpace(mail);
			mail = validateMail(mail);
		} else {
			mail = "EMPTY"; 
		}
		return mail;
	}
	
	public static String removeSpace(String str){
	
		str = removeStartSpace(str);
		return removeEndSpace(str);
	}
	
	public static String removeEndSpace(String str){
		
		if(str.indexOf(" ") == str.length() - 1 && str.length() != 0){
			return str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	public static String removeStartSpace(String str){
		
		int cnt = 0;
		while(true){
			if((str.substring(cnt)).indexOf(" ") == 0){
				cnt++; 
			} else {
				break;
			}
			if(cnt == str.length()) break;
		}
		return str.substring(cnt);
	}

	private static String validateMail(String str){
		
		if(str.indexOf(" ") > -1 || str.indexOf("@") == -1){
			str = "INVALID";
		} 
		return str;
	}
}
