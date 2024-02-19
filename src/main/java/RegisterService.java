import java.util.Scanner;

public class RegisterService {
    RegisterRepository repository=new RegisterRepository();
    LessonService lessonService=new LessonService();
    Scanner inp=new Scanner(System.in);

    public void createTableRegister(){
        repository.createTableRegister();
    }



    public void saveCourse() {
        System.out.println("***Ders Kayıt Sistemi***");
        lessonService.showLesson();
        boolean istrue=false;
        while(!istrue) {
            System.out.println("Seçmek istediğiniz dersin numarasını yazınız: ");
            int id=inp.nextInt();
            inp.nextLine();
            Lesson lesson=lessonService.searchLessonById(id);
            String nameOfLesson=lesson.getNameOfLesson();
            if (lesson!=null){
                System.out.println("Ad:");
                String name = inp.nextLine();
                System.out.println("Soyad:");
                String lastname=inp.nextLine();
                System.out.println("Yaş:");
                int age=inp.nextInt();
                inp.nextLine();
                Register register=new Register(name,lastname,lesson,age);
                repository.courseRegister(register);
                istrue=true;
            }else {
                System.out.println("Yanlış ders ismi girdiniz,tekrar deneyiniz");
                istrue=false;
            }

        }

    }
    public void findRegisterById(int id){
        repository.findRegisteryId(id);
    }

    public void showRegister(int id){
      repository.showRegister(id);
    }
}
