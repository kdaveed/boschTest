import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class MainApp implements Runnable {

	static String command;
	String input;
	static boolean newCommand = false;
	public Map<String, Person> people;
	Scanner scanner;
	int cnt = 0;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		scanner = new Scanner(System.in);
		people = new HashMap<String, Person>();
		System.out.println("Reading the input file ...\n");

		String[] rows = Reader.readFile("input.txt").split("\n");	
		for(int i = 1; i < rows.length; i++){
			Person person = RowProcessor.process(rows[i]);
			this.people.put(person.name, person);
			printPerson(i, person);
		}
		
		while (true) {
			sleep();
			print("\nPlease type a command! [add, edit, delete, list, exit] ");
			command = scanner.nextLine();
			switch (command) {

			case "exit":
				listPeople();
				sleep();
				System.exit(0);
				break;
			
			case "add":
				newPersonHandler();
				break;
			
			case "delete":
				deleteHandler();
				break;
			
			case "edit" :
				editHandler();
				break;
			
			case "list":
				listPeople();
				break;
			default:
				print("Invalid command");
			}
		}
	}

	public void deleteHandler(){
		
		if(this.people.keySet().size() == 0){
			print("There is no person stored in the system!");
		} else {
			print("Enter the name of person you would like to delete :");
			String name = scanner.nextLine();
			if(this.people.keySet().contains(name)){
				this.people.remove(name);
				print("\n\"" + name + "\" has been successfully deleted!" + "\n");
			} else {
				print("\nThere is no person with such name!\n");
			}
		}
	}

	public void listPeople() {

		print("\n");
		if(this.people.keySet().size() == 0){
			print("There is no person stored in the system!");
		} else {
			for (String key : this.people.keySet()) {
				cnt++;
				Person person = this.people.get(key);
				printPerson(cnt, person);
			}
			cnt = 0;
		}
	}

	public void editHandler(){
			
		print("Please enter name of the person you want to edit");
		input = scanner.nextLine();
		if(nameExists()){
			String name = input;
			while(true){
				print("Please enter the property you want to edit! [name, number, email] (done to exit)");
				input = scanner.nextLine();
				boolean done = false;
				switch (input) {
				case "name" :
					while(true){
						print("Type the new name!");
						input = scanner.nextLine();
						if(nameExists()){
							print("The name already exists");
						} else {
							this.people.get(name).name = input;
							this.people.put(input, this.people.get(name));
							this.people.remove(name);
							name = input;
							print("Name has been modified successfully!");
							break;
						}
					}
					break;
				case "number" :
					
					print("Type the new number!");
					input = scanner.nextLine();
					this.people.get(name).tel = (input.length() > 0 ) ? input : "EMPTY"; 
					print("Number has been modified successfully!");
					break;
				
				case "email" : 
					print("Type the new email!");
					input = scanner.nextLine();
					this.people.get(name).tel = (input.length() > 0 ) ? input : "EMPTY"; 
					print("Number has been modified successfully!");
					break;

				case "done" :
					done = true;
					break;
				default : 
					print("Invalid command");
					break;
				}
				if(done) break;
			}
		} else {
			print("The name \"" + input + "\" does not exist!");
		}	
	}
	
	public boolean nameExists(){
		return this.people.keySet().contains(input);
	}
	
	public void newPersonHandler() {

		String data[] = new String[3];
		String input;
		while (true) {
			switch (cnt) {
			case 0:
				print("Please enter the name!");
				break;
			case 1:
				print("Please enter the phone number! ");
				break;
			case 2:
				print("Please enter the e-mail address! ");
				break;
			}
			input = scanner.nextLine();
			switch (cnt) {
			case 0:
				nameHandler(data, input);
				break;
			case 1:
				telHandler(data, input);
				break;
			case 2:
				mailHander(data, input);
				break;
			}
			if (cnt == 3) {
				Person person = new Person(data[0], data[1], data[2]);
				this.people.put(data[0], person);
				print("Person has been added!\n\n");
				printPerson(this.people.keySet().size(), person);
				cnt = 0;
				break;
			}
		}
	}
	
	public void print(String msg) {
		System.out.println(msg);
	}

	public void printPerson(int cnt, Person person){
		
		print(Integer.toString(cnt) + ".\tName : " + person.name + " \n\tNumber : "
				+ person.tel + "\n\tEmail : " + person.eMail + "\n");
		
	}
	public void nameHandler(String data[], String input) {

		if (input.length() == 0) {
			print("Name cannot be empty");
		} else if (this.people.keySet().contains(input)){
			print("The name \"" + input + "\" already exists. Please enter an other name!");
		} else {
			data[0] = input;
			cnt++;
		}
	}

	public void telHandler(String data[], String input) {

		if (input.length() == 0) {
			data[1] = "EMPTY";
		} else {
			data[1] = input;
		}
		cnt++;
	}

	public void mailHander(String data[], String input) {

		if (input.length() == 0) {
			data[2] = "EMPTY";
		} else {
			data[2] = input;
		}
		cnt++;
	}
	
	public void sleep(){
		try{
			Thread.sleep(1300);
		} catch (InterruptedException ex) {
			print("Sleep exception");
		}
	}

}
