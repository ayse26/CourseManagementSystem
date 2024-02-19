public class LessonService {

    LessonRepository repository = new LessonRepository();

    public LessonService(){
        Lesson lesson1=new Lesson("Matematik",9);
        Lesson lesson2=new Lesson("Türkçe",12);
        Lesson lesson3=new Lesson("Tarih",6);
        Lesson lesson4=new Lesson("Coğrafya",3);



        repository.saveLesson(lesson1);
        repository.saveLesson(lesson2);
        repository.saveLesson(lesson3);
        repository.saveLesson(lesson4);



    }
    public void createTableLesson(){
        repository.createTableLesson();
    }


    public void showLesson() {
        System.out.println("-------Kurslarımız--------");
        repository.findAllLesson();
    }

    public Lesson searchLessonById(int id) {
        Lesson lesson=repository.searchLessonById(id);
        return lesson;
    }
}
