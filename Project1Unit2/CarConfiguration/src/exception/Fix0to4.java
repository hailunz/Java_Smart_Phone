package exception;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hailun Zhu
 * ID: hailunz
 * Date: 9/19/15
 */
public class Fix0to4 {
 /*
    Error0(0, "Missing price for Automobile in text file!"),
    Error1(1, "Missing OptionSet data (or part of it)."),
    Error2(2, "Missing Option data in text file."),
    Error3(3, "Missing filename or wrong filename."),
    Error4(4, "Missing name for Automobile in text file");

     */

    public float fix0(int errno) throws IOException {
       float price = 0;
       BufferedReader br =
               new BufferedReader(new InputStreamReader(System.in));
       while (price <= 0){
          System.out.println("Error: "+ errno );
          System.out.println("Missing price for Automobile in text file!");
          System.out.println("Please enter a new baseprice:");
          price = Float.parseFloat(br.readLine());
       }
       return price;

    }

    public String fix1(int errno) throws IOException {
       String line = null;
       BufferedReader br =
               new BufferedReader(new InputStreamReader(System.in));
       while (line == null || line.split(",").length<1){
          System.out.println("Error: "+ errno );
          System.out.println("Missing OptionSet data in text file.");
          System.out.println("Please enter a new OptionSet data:");
          line = br.readLine();
       }
       return line;
    }

   /*
    * helper function to check data format
    */
    private boolean checkOptionData(String line){
       String[] args = line.split("@");
       if (args.length!=2)
            return false;
       try{
          Float.parseFloat(args[1]);
       }catch (Exception e){
          return false;
       }

       return true;
    }

    public String fix2(int errno) throws IOException {
       String line = null;
       BufferedReader br =
               new BufferedReader(new InputStreamReader(System.in));
       while (line == null || !checkOptionData(line)){
          System.out.println("Error: "+ errno );
          System.out.println("Missing Option data in text file.");
          System.out.println("Please enter a new Option data:");
          line = br.readLine();
       }
       return line;

    }
    public String fix3(int errno) throws IOException {
         String filename = null;
         BufferedReader br =
               new BufferedReader(new InputStreamReader(System.in));
         while (filename == null || filename.length()<0 || !filename.endsWith(".txt")){
            System.out.println("Error: "+ errno );
            System.out.println("Missing filename or wrong filename.");
            System.out.println("Please enter a new filename:");
            filename = br.readLine();
         }
         return filename;
    }

    public String fix4(int errno) throws IOException {
       String model = null;
       BufferedReader br =
               new BufferedReader(new InputStreamReader(System.in));
       while (model == null || model.length()<1){
          System.out.println("Error: "+ errno );
          System.out.println("Missing name for Automobile in text file");
          System.out.println("Please enter a new name:");
          model = br.readLine();
       }
       return model;

    }

}
