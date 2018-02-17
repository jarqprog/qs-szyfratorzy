package dao;

import exceptions.DatabaseFailure;

public interface DatabaseDAO {

    void prepareDatabase();
    void closeConnection();
    void updateDatabase(String sqlScriptPath) throws DatabaseFailure;
    
//     private void executeSqlScript(File inputFile) {
//         String s;
//         StringBuffer sb = new StringBuffer();
//         try {
//             FileReader fr = new FileReader(inputFile);
//             BufferedReader br = new BufferedReader(fr);

//             while((s = br.readLine()) != null) {
//                 sb.append(s);
//             }
//             br.close();
//             String[] instructions = sb.toString().split(";");
//             openConnection();
//             Statement st = connection.createStatement();

//             for(String instruction : instructions) {
//                 if(!instruction.trim().equals("")) {
//                     st.executeUpdate(instruction);
//                 }
//             }
//         } catch(Exception e) {
//             System.out.println("*** Error : "+e.toString());
//             System.out.println("*** ");
//             System.out.println("*** Error : ");
//             e.printStackTrace();
//             System.out.println("################################################");
//             System.out.println(sb.toString());
//         } finally {
//             closeConnection();
//         }
//     }
}
