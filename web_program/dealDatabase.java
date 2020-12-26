import java.sql.*;

public class dealDatabase {
    static final String JDBC_Driver = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/web_data?serverTimezone=UTC&useSSL=false";
    static final String USER = "root";
    static final String PASS = "shana20010731";
    public static void main(String[] args){
        /*Connection conn = null;
        try{
            Class.forName(JDBC_Driver);
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql = "ALTER TABLE graph ADD COLUMN isLast TEXT NOT NULL;";
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(conn);
        try{
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }*/
        int a = 1;
        String haha = null;
        int[] b = {1,2,3,4,5};
        int[] c = {2,3,4,5};
        dealDatabase test = new dealDatabase();

        haha = test.getOneStep(1);
        System.out.println(haha);
    }
    public void addOnestep(int step,int[] matrix ,int[] numberOfNote)  {
        Connection conn = null;
        String query = "insert into graph(id,matrix,note) " + "values(?,?,?)";
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement preparedstmt = conn.prepareStatement(query);
            preparedstmt.setInt(1,step);
            preparedstmt.setString(2,toStringMethod(matrix));
            preparedstmt.setString(3,toStringMethod(numberOfNote));
            preparedstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (conn == null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeOneStep(int step,int[] matrix,int[] numberOfNote){
        Connection conn = null;
        String query = "update graph set matrix = ?,note = ? where id = ?";
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setString(1,toStringMethod(matrix));
            prep.setString(2,toStringMethod(numberOfNote));
            prep.setInt(3,step);
            prep.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (conn == null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public String getOneStep(int step)  {
        Connection conn = null;
        String query = "select matrix from graph where id = ?";
        String matrix = null;
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement prep = conn.prepareStatement(query);
            ResultSet rs = null;
            prep.setInt(1,step);
            rs = prep.executeQuery();
            while (rs.next()) {
                matrix = rs.getString("matrix");
            }
            rs.close();
            prep.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return matrix;
    }
    public void setOneStepProperty(){

    }
    public void getOneStepProperty(){

    }
    public void deleteOneMenmoty(int step){
        Connection conn = null;
        String query = "delete from graph where id = ?";
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            PreparedStatement prep = conn.prepareStatement(query);
            prep.setInt(1,step);
            prep.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (conn == null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void clearDatabase(){

    }
    public String toStringMethod(int[] arr ){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<arr.length;i++)
        {
            if(i!=arr.length-1)
                sb.append(arr[i]+" ,");
            else {
                sb.append(arr[i]);
            }
        }
        String out = "" + sb;
        return out;
    }
    public int[] toArrayMethod(String str){
        int[] out = null;
        return out;
    }

}
