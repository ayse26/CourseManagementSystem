public class Lesson {
    private int id;
    private String nameOfLesson;
    private String date;
    private int hours;

    public Lesson(){
    }

    public Lesson(String nameOfLesson,int hours) {
        this.nameOfLesson = nameOfLesson;
        this.date=courseDay(nameOfLesson);
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public String getNameOfLesson() {
        return nameOfLesson;
    }

    public String getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameOfLesson(String nameOfLesson) {
        this.nameOfLesson = nameOfLesson;
    }

    public String courseDay(String nameOfLesson){
        switch (nameOfLesson){
            case "Matematik":
                return "Ders günleri: Pazartesi, Salı, Cuma";
            case "Türkçe":
                return "Ders günleri: Pazartesi, Çarşamba, Perşembe, Cuma";
            case "Tarih":
                return "Ders günleri: Salı, Perşembe";
            case "Coğrafya":
                return "Ders günleri: Çarşamba";
            default:
                return "Hatalı isim girdiniz";
        }
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", nameOfLesson='" + nameOfLesson + '\'' +
                ", date='" + date + '\'' +
                ", hours=" + hours +
                '}';
    }
}
