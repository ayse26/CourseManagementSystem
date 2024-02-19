import java.sql.*;

public class LessonRepository {

    private Connection con;
    private Statement st;
    private PreparedStatement prst;

    public void getConnection(){
        try {
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public void getStatement(){
        try {
            this.st =con.createStatement();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public void getPreparedStatement(String sql){
        try {
            this.prst=con.prepareStatement(sql);
        }catch (SQLException e){
            e.getMessage();
        }
    }

    public void createTableLesson(){
        getConnection();
        getStatement();

        String sql = "create table t_lesson (id serial,nameOfLesson varchar(20),date varchar(50),hours int)";
        try {
            st.execute(sql);
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            try {
                con.close();
                st.close();
            }catch (SQLException e){
                e.getMessage();
            }
        }
    }



    public void saveLesson(Lesson lesson){
        getConnection();

        String sql="insert into t_lesson(nameOfLesson,date,hours) values(?,?,?)";
        getPreparedStatement(sql);

        try {
            prst.setString(1,lesson.getNameOfLesson());
            prst.setString(2,lesson.getDate());
            prst.setInt(3,lesson.getHours());
            prst.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
        }
        finally {
            try {
                con.close();
                prst.close();
            }catch (SQLException e){
                e.getMessage();
            }
        }

    }

    public void findAllLesson() {
        getConnection();
        getStatement();
        String sql="select * from t_lesson";
        try {
            ResultSet resultset=st.executeQuery(sql);
            while (resultset.next()){
                System.out.printf("| %-5d | %-20s | %-20s | %-5d\n",
                        resultset.getInt("id"),
                        resultset.getString("nameOfLesson"),
                        resultset.getString("date"),
                        resultset.getInt("hours")
                );
            }
        }catch (SQLException e){
            e.getMessage();
        }

        finally {
            try {
                con.close();
                st.close();
            }catch (SQLException e){
                e.getMessage();
            }
        }

    }


    public Lesson searchLessonById(int id) {
        Lesson lesson=null;
        getConnection();
        String sql="select * from t_lesson where id=?";
        getPreparedStatement(sql);

        try {
            prst.setInt(1,id);
            ResultSet result=prst.executeQuery();
            if (result.next()){
                lesson=new Lesson();
                lesson.setId(result.getInt("id"));
                lesson.setNameOfLesson(result.getString("nameOfLesson"));
            }
        }catch (SQLException e){
            e.getMessage();
        }
        finally{
            try {
                prst.close();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return lesson;
    }

    public void createTableCourse() {
    }
}

