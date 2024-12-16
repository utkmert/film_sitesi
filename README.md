Proje Adı: Film Yönetim Sistemi

Projenin Amacı:
Bu proje, kullanıcıların filmleri ekleyip, düzenleyip, silebileceği ve arayabileceği bir film yönetim sistemidir. Kullanıcılar filmleri tür, yıl ve rating gibi kriterlere göre sıralayabilir veya arayabilirler.

Çalışma Prensibi:
Kullanıcı, filme ait bilgileri ekleyebilir, güncelleyebilir ve silebilir. Sistem, veritabanında bu bilgileri saklar ve gerektiğinde kullanıcının isteği üzerine ilgili filmleri sorgular.

Kullanılan Teknolojiler:
- Java: Programın ana dilidir ve veritabanı işlemleri için JDBC kullanılmıştır.
- MySQL: Filmlerin veritabanında saklanması için kullanılan veritabanı yönetim sistemidir.
- API : Filmlerin bilgilerinin alınması için The Open Movie Database (OMDb API) kullanılmıştır.
- GUI : Film bilgierinin ekrandaki grafiksel gösterimi için Swing isimli kütüphane kullanılmıştır.

Gereksinimler:
- Java Development Kit (JDK) 8 veya daha yeni bir sürüm.
- JDBC ile MySQL bağlantısı için MySQL JDBC Driver (mysql-connector-java).
- filmsdb adında bir veritabanı oluşturun.

Uygulamanın Çalıştırılması:
 1) Kodun Derlenmesi ve Çalıştırılması:
   - Kodunuzu bir Java IDE'sinde (örneğin IntelliJ IDEA, Eclipse veya NetBeans) açın.
   -  FilmGUI sınıfını çalıştırarak uygulamayı başlatın.
 2) Bağlantı Bilgileri:
   - FilmCRUDOperations.java dosyasındaki veritabanı bağlantı bilgilerini kontrol edin ve uygun şekilde düzenleyin.
 3) API anahtarını ayarlama:
   - FilmGUI sınıfında OMDb API anahtarınızı uygun yere yerleştirin.
 4) Uygulama Kullanımı:
   - Film Ekleme
     + Film Adı Girin:
     * Ana ekranda "Title" alanına eklemek istediğiniz filmin adını yazın.
     + OMDb API ile Bilgileri Çekin:
     * "Insert" butonuna bastığınızda, API'den film bilgileri (tür, yıl, puan) otomatik olarak alınacak ve veritabanına kaydedilecektir.
   - Film Listeleme
     + Show Butonuna Tıklayın:
     * "Show" butonuna tıkladığınızda, veritabanındaki tüm filmler bir tablo şeklinde gösterilir.
   - Film Güncelleme
     + Güncellemek İstediğiniz Filmin Adını Girin:
     * "Title" alanına güncellemek istediğiniz filmin adını yazın.
     + Yeni Bilgileri Girin:
     * "Genre", "Year" ve "Rating" alanlarına yeni bilgileri girin.
     + Update Butonuna Tıklayın:
     * "Update" butonuna bastığınızda, film bilgileri güncellenir.
   - Film Silme
     * Silmek İstediğiniz Filmin Adını Girin:
     + "Title" alanına silmek istediğiniz filmin adını yazın.
     * Delete Butonuna Tıklayın:
     + "Delete" butonuna bastığınızda, film veritabanından silinir.

       
