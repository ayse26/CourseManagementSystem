import java.sql.*;

public class RegisterRepository {
    private Connection con;
    private Statement st;
    private PreparedStatement prst;

    private LessonRepository lessonRepo=new LessonRepository();

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

    public void createTableRegister(){
        getConnection();
        getStatement();

        String sql = "create table t_course (id serial,name varchar(20),lastname varchar(20),lesson varchar(20),age int)";
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

    public void courseRegister(Register register) {
        getConnection();

        String sql="insert into t_course(name,lastname,lesson,age) values(?,?,?,?)";
        getPreparedStatement(sql);

        try {
            prst.setString(1,register.getName());
            prst.setString(2,register.getLastname());
            prst.setString(3,register.getLesson().getNameOfLesson());
            prst.setInt(4,register.getAge());
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
    public Register findRegisteryId(int id) {
        Register register=null;
        getConnection();
        String sql="select * from t_register where id=?";
        getPreparedStatement(sql);
        try {
            register=new Register();
            prst.setInt(1,id);
            ResultSet result=prst.executeQuery();
            if (result.next()){
                register.setId(result.getInt("id"));
                register.setName(result.getString("name"));
                register.setLastname(result.getString("lastname"));
                register.setAge(result.getInt("age"));
                Lesson lesson=lessonRepo.searchLessonById(result.getInt("id"));
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
        return register;
    }


    public void showRegister(int id) {
        getConnection();
        String sql="select * from t_register where id=?";
        getPreparedStatement(sql);

        try {
            prst.setInt(1,id);
            ResultSet resultset = st.executeQuery(sql);
            System.out.printf("| %-5s | %-20s | %-20s |%-20s | %-5s\n","id","ad","soyad","lesson","yaş");
            while (resultset.next()) {                       //while ile dönüp içeriğini yazdıracağız
                System.out.printf("| %-5d | %-20s | %-20s |%-20s | %-5d\n",
                        resultset.getInt("id"),
                        resultset.getString("name"),
                        resultset.getString("lastname"),
                        resultset.getString("lesson"),
                        resultset.getInt("age")
                );
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
    }


}
