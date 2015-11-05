import java.sql.*;

public class P2 {

   public static void main (String [] args) {

      try {   
         Class.forName ("com.mysql.jdbc.Driver");
         } 
      catch (Exception E) {
            System.err.println ("Unable to load driver.");
            E.printStackTrace ();
      } 

      try { 

         Connection conn1;
         String dbUrl = "jdbc:mysql://csdb.cs.iastate.edu:3306/acdanaDB";
         String user = "acdana";
         String password = "acdana-64";
         conn1 = DriverManager.getConnection (dbUrl, user, password);
         System.out.println ("*** Connected to the database ***"); 



		 //////////////////////////////////////////////////
		 
		 Statement allStudentsStatement = conn1.createStatement();
		 ResultSet allStudents = allStudentsStatement.executeQuery("SELECT S.StudentID FROM Student S");
			
		 PreparedStatement updateInfo = conn1.prepareStatement("update Student"  + " " + 
					 "set Classification=? , GPA=? , CreditHours=?" + " " + 
					 "where StudentID = ?" ); 
		 
		 PreparedStatement creditHoursGPA = conn1.prepareStatement("SELECT S.CreditHours, S.GPA, E.Grade FROM Student S, Enrollment E " +
		 "WHERE S.StudentID = E.StudentID AND S.StudentID = ?");
		 
		 while(allStudents.next()) {
			 
			String studentID = allStudents.getString(1);
			String classification = "";
			int creditHours = 0;
			double GPA = 0.0;
			
			updateInfo.setString(4, studentID);
			creditHoursGPA.setString(1, studentID);
			ResultSet creditsGPA = creditHoursGPA.executeQuery();		
			
			creditsGPA.first();
			GPA = creditsGPA.getDouble("GPA")*creditsGPA.getInt("CreditHours");
			creditHours = creditsGPA.getInt("CreditHours");
			creditsGPA.beforeFirst();

			while(creditsGPA.next()) {
				
				String grade = creditsGPA.getString("Grade").trim();
				if(!grade.equals("F")) {
					creditHours += 3;
				}
				
				if(grade.equals("A")) {
					GPA += 3*4.00;
				}
				else if(grade.equals("A-")) {
					GPA += 3*3.66;
				}
				else if(grade.equals("B+")) {
					GPA += 3*3.33;
				}
				else if(grade.equals("B")) {
					GPA += 3*3.00;
				}
				else if(grade.equals("B-")) {
					GPA += 3*2.66;
				}
				else if(grade.equals("C+")) {
					GPA += 3*2.33;
				}
				else if(grade.equals("C")) {
					GPA += 3*2.00;
				}
				else if(grade.equals("C-")) {
					GPA += 3*1.66;
				}
				else if(grade.equals("D+")) {
					GPA += 3*1.33;
				}
				else if(grade.equals("D")) {
					GPA += 3*1.00;
				}
				else {

				}

			}
			
			
			GPA = GPA/creditHours;
			
			if(creditHours <= 29) {
				classification = "Freshman";
			}
			else if(creditHours <= 59) {
				classification = "Sophomore";
			}
			else if(creditHours <= 89) {
				classification = "Junior";
			}
			else {
				classification = "Senior";
			}
			updateInfo.setString(1, classification); //Update classification
		
			updateInfo.setDouble(2, GPA); //Update GPA
			updateInfo.setInt(3, creditHours); //Update credit hours
		
			updateInfo.executeUpdate();	


		}
		 


										  	                          

										  	                          
		 allStudentsStatement.close();
         updateInfo.close (); 
		 
		 ////////////////////////////////////////////////////////
		 
		 

		 
		 ///////////////////////////////////////////////////////////
		 
		 Statement seniorClassStatement = conn1.createStatement ();

         ResultSet seniorResultSet = seniorClassStatement.executeQuery ("SELECT Ps.Name, Pm.Name, S.GPA FROM "
         													+ "Person Ps, Person Pm, Student S "
         													+ "WHERE S.StudentID = Ps.ID AND "
         													+ "S.MentorID = Pm.ID AND "
         													+ "S.Classification = 'Senior' ORDER BY S.GPA DESC"); 


         
         System.out.println ( );		
         System.out.println ("Student		         Mentor	    	          GPA");		
         System.out.println ("---------    		----------		----------");		




         String studentName;
         String mentorName;
         double GPA;
         int numberOfStudents = 0;
         double prevGPA = 0.0;;
         
         while(seniorResultSet.next()) {
            studentName = seniorResultSet.getString(1);
            mentorName = seniorResultSet.getString(2);
            GPA = Math.round(seniorResultSet.getDouble(3)*100)/100.0;

            if(numberOfStudents != 5) {
            	System.out.println (studentName + "             " + mentorName + "               " + GPA);
            	numberOfStudents++;
            	prevGPA = GPA;
            }
            else if(prevGPA == GPA) {
            	System.out.println (studentName + "             " + mentorName + "               " + GPA);
            	prevGPA = GPA;
            }
            else {
            	break;
            }
 
         }			

         
         seniorClassStatement.close (); 
		 
		////////////////////////////////////////////// 
		 
		 
         
		 
		 
		 
		 
         conn1.close (); 

      } // End of try

      catch (SQLException E) {
         System.out.println ("SQLException: " + E.getMessage());
         System.out.println ("SQLState: " + E.getSQLState());
         System.out.println ("VendorError: " + E.getErrorCode());

      } // End of catch

   } // end of main

} //end of class
