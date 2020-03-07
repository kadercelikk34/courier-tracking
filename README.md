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

Örnekler
-------------


Kullandığım teknolojiler
-------------
1.Maven projesi Oluşturuldu.
2.Spring Boot uygulamsı için pox.xml aşağıdaki dependency ler eklendi.
  spring-boot-starter-data-jpa :Spring jpa nın özelliklerini kllanmak için eklendi.
  spring-boot-starter-web
  modelmapper : RestServislere direk entity vermek yerine Dto lar RequestBody olarak verilmiş.
                Db işlem yaparken dto dan entitye convert işlemi yapılmıştır.
  junit: DataJpaTest , WebMvcTest ve servis testleri yazmak için kullanıldı
  h2database : H2 database kullanılmıştır

3.Kullanılan Desing Pattern
   Singletion : @Autowired kullanılarak sadece bir nesne oluşturulmuştur.

Uygulamanın Çalıştırılması ve Ekranlar
------------- 

CouriersController Ne İş Yapar
------------- 
saveCourier ; Yeni bir Kurye  oluşturulur.
getTotalTravelDistance ; id si verilen kuryenin ziyaret ettiği toplam mesafeyi verir.
courierDistanceStores ; Lokasyonu verilen Kuryenin 100 ve daha yakın daki magazaların listesini verir.

StoresController Ne İş Yapar
------------- 
saveCourier ; Yeni bir Mağaza oluşturulur.

LocationController Ne İş Yapar
------------- 
Kurye ve Mağaza nın lokasyonu saveLocation ile  kaydedilir.




Ekstradan Yapılacak İşler
------------- 

