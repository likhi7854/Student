import java.sql.*;
import java.util.Scanner;

class Main{
    private final static String url = "jdbc:mysql://localhost:3306/stud";
    private final static String user = "root";
    private final static String password = "Ammu@nani03";
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            Connection con  = DriverManager.getConnection(url, user, password);
            String query ="insert into students(name,branch,rollNo,marks) values(?,?,?,?)";
            PreparedStatement pr = con.prepareStatement(query);
            Scanner sc = new Scanner(System.in);
            while(true){
                System.out.println("enter name: ");
                String name = sc.next();
                System.out.println("enter branch: ");
                String branch = sc.next();
                System.out.println("enter rollNo: ");
                int rollNo = sc.nextInt();
                System.out.println("enter marks: ");
                int marks = sc.nextInt();
                pr.setString(1,name);
                pr.setString(2,branch);
                pr.setInt(3,rollNo);
                pr.setInt(4,marks);
                pr.addBatch();
                System.out.println("enter more data(YES/NO)");
                String choice = sc.next();
                if(choice.toUpperCase().equals("NO")){
                    break;
                }


            }
            int[] arr = pr.executeBatch();
            for(int i=0;i<arr.length;i++){
                if(arr[i]==0){
                    System.out.println("query didn't updated sucessfully");
                }
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}