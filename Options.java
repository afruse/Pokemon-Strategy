
import java.util.ArrayList;
public class Options
{
    ArrayList<String[]> waitList;
    /**
     * Constructor for objects of class Guests
     * @param waitList  the ArrayList containing the information from the waitList scanned by scanGuest
     */
    public Options(ArrayList<String[]> waitList)
    {
        this.waitList = waitList;
    }
    /**
     * adds a new reservation to the list
     * 
     * @param firstName takes input of the reservations first name
     * @param lastName  takes unput of reservations last name
     * @param groupSize takes input of the size of reservation party
     * @param request   takes input of any request reservation might have
     */
    public void addToList(String firstName, String lastName, String groupSize, String request){
        String[] adding = {firstName, lastName, groupSize, request};
        System.out.println(firstName + " " + lastName + " added");
        waitList.add(adding);
    }
    /**
     * removes first reservation from list
     */
    public void removeFromList(){
        System.out.println("First Removed");
        waitList.remove(0);
    }
    /**
     * prints and returns first in the list
     * 
     * @return String[] the information fo first on list
     */
    public String[] viewFirst(){
        for (int j = 0; j < waitList.get(1).length; j++)
        {
                System.out.print(waitList.get(1)[j] + ",");
        }
        System.out.println();
        return waitList.get(1);
    }
    /**
     * returns the current changes to the waitlist so Intro can save it to file
     * 
     * @return  ArrayList<String[]> the ArrayList containing all the information
     */
    public ArrayList<String[]> saveChanges(){
        return waitList;
    }
    /**
     * loads a new file
     * 
     * @return boolean  whether or not to load new file
     */
    public boolean loadNewFile(){
        return true;
    }
    /**
     * prints out exit statement
     * 
     * @return boolean  whether or not to exit program
     */
    public boolean exit(){
        System.out.println("Thank you for using the service : )");
        return true;
    }
    /**
     * prints and returns entire reservation list
     * 
     * @return ArrayList<String[]> returns arrayList containing reservation list
     */
    public ArrayList<String[]> viewEntire(){
        /**
         * printing help from Aous
         */
        for (int i = 0; i < waitList.size(); i++)
        {
            for (int j = 0; j < waitList.get(i).length; j++)
            {
                System.out.print(waitList.get(i)[j] + ",");
            }
            System.out.println();
        }
        return waitList;
    }
}
