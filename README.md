Bu uygulama, Clean Architecture prensiplerine uygun olarak tasarlanmış firestore veritabanı kullanılarak yazılmış bir not defteri uygulamasıdır. Hilt kullanılarak bağımlılık yönetimi gerçekleştirilmiş ve Use Case katmanı aracılığıyla 
Repository ve ViewModel arasındaki bağlantılar sağlanmıştır.

Uygulama,ilk açıldığında kullanıcıyı Register sayfası karşılıyor.Kullanıcı kayıt olduktan sonra , not eklemesi için bekleyen fab buton bulunmaktadır.Kullanıcı bu butona bastığında bir alertDialog açılıyor ve açılan alert dialogda 
notun başlığını ve içeriğini girip kaydedebilir.Kaydettiği notu daha sonra ister silebilir, isterse de güncelleyebilir.Aynı zamanda notları içerisinde arama bile yapabilir.isterse de hesabım sayfasından çıkış yapabilir ve login sayfasından 
tekrar girişini yapabilir

Uygulamanın tasarımı şu şekilde yapılandırılmıştır:

Hilt: Bağımlılıkları yönetmek amacıyla kullanılır. Hilt, DI (Dependency Injection) sağlayarak kodun test edilebilirliğini ve sürdürülebilirliğini artırır.

Clean Architecture: Uygulama, Clean Architecture prensiplerine uygun olarak katmanlara ayrılmıştır. Bu yaklaşım, veri, iş mantığı ve kullanıcı arayüzü bileşenlerinin bağımsız bir şekilde yönetilmesini sağlar.

Use Case: İş mantığını yöneten ve işlevlerin tek bir sorumluluk doğrultusunda düzenlendiği katmandır. Repository ve ViewModel arasındaki bağlantıyı sağlayarak kodun modülerliğini ve bakımını kolaylaştırır.

ViewModel: Kullanıcı arayüzü ile iş mantığı arasındaki köprüyü kurar ve kullanıcı etkileşimlerine yanıt verir.

view:sadece UI kodları bulunmata ve viewModele erişip UI güncelleme işlem yapmaktdır.

Repository: Veritabanı veya uzak veri kaynakları ile etkileşimde bulunur. Firebase Authentication işlemlerini yönetir ve Use Case katmanına veri sağlar. Bağımsız olarak test edilebilir ve iş mantığından ayrıdır.

Bu yapı, kodun okunabilirliğini, bakımını ve test edilebilirliğini artırır. Her bir fonksiyon ve sınıf, belirli bir işlevi yerine getirecek şekilde tasarlanmıştır, bu da uygulamanın daha düzenli ve yönetilebilir olmasını sağlar.

![image alt](https://github.com/dumanYusuf/FiresrorePersonelizedNotebook/blob/master/personelNotebook1.png?raw=true)
![image alt](https://github.com/dumanYusuf/FiresrorePersonelizedNotebook/blob/master/personelNotebook2.png?raw=true)
