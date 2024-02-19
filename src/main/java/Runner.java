/*
Proje:Course Management System
     -1-Herhangi bir eğitim kurumu için ders yönetim uygulaması geliştiriniz.
     -2-Kullanıcı
               -C:create:ders kayıt
               -R:read:ders veya dersleri görüntüleme
               -U:update: id ile ders güncelleme
               -D:delete: id ile ders silme
               -R: kelime ile ders filtreleme                  //Runner class olusturuyoruz bunun için
       işlemlerini yapabilmeli.
     -3-ders:id,name,day,hours özelliklerine sahiptir.   //Course class olusturuyoruz bunun için

 */

import java.sql.SQLOutput;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        RegisterService registerService = new RegisterService();
        registerService.createTableRegister();
        LessonService lessonService=new LessonService();
        lessonService.createTableLesson();

        start(registerService);
    }
    public static void start(RegisterService registerService){

        Scanner inp=new Scanner(System.in);
        int select=-1;
        while(select!=0) {
            System.out.println("----- Ders yönetim sistemine hoşgeldiniz -----");
            System.out.println("1-Kayıt");
            System.out.println("2-Kaydı görüntüle");
            System.out.println("3-Ders güncelle");
            System.out.println("4-Ders sil");
            System.out.println("5-Ders bul(id ile)");
            System.out.println("6-Dersleri filtrele(isim ile)");
            System.out.println("0-Çıkış");
            System.out.print("Seçiminiz:");
            select= inp.nextInt();
            inp.nextLine();

            switch (select){
                case 1:
                    //kayıt
                    registerService.saveCourse();
                    break;
                case 2:
                    //listele
                    break;
                case 3:
                    //güncelle
                    break;
                case 4:
                    //sil
                    break;
                case 5:
                    //bul
                    break;
                case 6:
                    //filtrele
                    break;
                case 0:
                    System.out.println("İyi günler...");
                    break;
                default:
                    System.out.println("Hatalı giriş,tekrar deneyin.");
                    break;
            }
        }

    }
}
