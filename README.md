#Courier Tracking (Kurye Takibi)
===================
Bu projede Java ile  restful web servisi oluşturanız bekleniyor.
Uygulama Kuryelerin çoğrafi konumlarını couriers (time, courier, lat, lng) girdi olarak almakdadır.
Uygulama  ayrıca aşağıdaki kriterleri de karşılamalıdır;

1.Herhangi bir kurye Migros'tan 100 metre yarıçapa girdiğinde kuryeyi kaydedin ve saklayın
  Aynı mağazanın çevresine 1 dakikadan fazla giriş yapılmamalıdır
  
2. Uygulama, kuryenin toplam mesafesini sorgulamak için bir yol sağlamalıdır.
   kurye seyahatleri getTotalTravelDistance (courierId);
   
3.• Seçtiğiniz en az 2 design pattern uygulayın.


Kullandığım teknolojiler ve yapılan işler
-------------
1.Java programlama dili kullanılmıştır.
2.Maven projesi Oluşturuldu.

3.Spring Boot uygulamsı için pox.xml aşağıdaki dependency ler eklendi.
  spring-boot-starter-data-jpa :Spring jpa nın özelliklerini kullanmak için eklendi.
  spring-boot-starter-web 
  modelmapper : RestServislere direk entity vermek yerine Dto lar RequestBody olarak verilmiş.
                Db işlem yaparken dto dan entitye convert işlemi yapılmıştır.
  junit: DataJpaTest , WebMvcTest ve servis testleri yazmak için kullanıldı
  h2database : H2 database kullanılmıştır

4.Kullanılan Desing Pattern
   Singletion : @Autowired kullanılarak sadece bir nesne oluşturulmuştur.
   Factory pattern : spring modelmapper kullanıldığı için factory patterni kullanmış oldum.
   mvc pattern : Mvc patterne uygun yapılmıştır.Model ve controller lar sadece view kısmı yok.
   
5.Proje intellij de   yazılmış , ideye sonarLint plugini eklenip kod kalitesi için sonardan geçirilmiştir.

Projenin Çalıştırılması
------------- 
Projeyi gitden cekip CourierTrackingApplication classını çalıştırınız.
resources> postMan.json --> postman de kullanılacak itemler 
PostMan uygulamasından import > Import File  postMan.json dosyanını import ediniz.
Daha sonra postmanden servislere istek gönderiniz.

Örnek Çalışma
------------- 
1.http://localhost:8080/couriers/1
  1 idli kuryenin  ziyaret ettiği toplam mesafeyi verir.
  Çıktı :889 (metre)
  
2.http://localhost:8080/couriers/courierDistanceStores?speed=30
   Lokasyonu verilen kuryeye  100 metre ve daha yakınındaki magazalar listesi
   (magaza ismi , kuryenin magazaya uzaklığı metre , kuryenin hızına göre magazaya kaç dakika olduğu)
   Çıktı ;
   [
       {
           "storeName": "Ataşehir MMM Migros",
           "courierDistance": "92.5 m",
           "distanceMinute": 2.0
       }
   ]

CouriersController Ne İş Yapar
------------- 
saveCourier ; Yeni bir Kurye  oluşturulur.
getTotalTravelDistance ; id si verilen kuryenin ziyaret ettiği toplam mesafeyi verir.
courierDistanceStores ; Lokasyonu verilen Kuryenin 100 ve daha yakın daki magazaların listesini verir.
                        Kurye magazaya 1 dakikalık mesafedeyse listeye eklenmmeiştir.

StoresController Ne İş Yapar
------------- 
saveCourier ; Yeni bir Mağaza oluşturulur.

LocationController Ne İş Yapar
------------- 
Kurye ve Mağaza nın lokasyonu saveLocation ile  kaydedilir.

Ekstradan Yapılacak İşler
------------- 
1.Backend projeleri docker ile containerized yapılabilirdi. 
2.Daha fazla junit test ve integration test yazılabilirdi.
3.İki lokasyon arasındaki mesafeyi hesaplarken en az hata ve sapmanın olduğu formulü kullanmak için araştırma yapılabilir.
