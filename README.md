
Service - компонент андроид приложения, который не имеет пользовательского интерфейса. Позволяет выполнять задачи в фоне.

Различия между Foreground and Background Service:
В первом случае пользователь видит работу Service(обязательно наличие notification), во втором - нет.
Например, мини-плеер при прослушивании музыки.

Различия между Привязанным и Запущенным Service:
В первом случае мы можем получать от Service данные, используя интерфейс IBinder.

В каком потоке работает Service?
Service работает в UI потоке.

Как получать данные от Service?

Если приложение убить, Service будет работать?

Минусы: 
 - Работает в UI потоке.

Предпочтительнее использовать WorkManager.

Документация Service:
https://developer.android.com/guide/components/services

==============

IntentService(deprecated) - наследует класс Service и уже сам создает второй поток. 
Сам себя останавливаем, когда у него заканчивается работа.

Почему IntentService deprecated?
Это связано с ограниченими на background задачи:
https://developer.android.com/about/versions/oreo/background

Почему Service не deprecated? Он же тоже может выполнять задачи background.

Минусы:
 - Was deprecated in API level 30.

Документация IntentService:
https://developer.android.com/reference/android/app/IntentService

==============

JobSchedule - планировщик работы, выставление условий для выполнения задач.
JobService - наследует Service и получает сообщения от JobSchedule. Работает в UI потоке.

Почему IntentService deprecated, а JobSchedule + JobService нет?


Документация JobSchedule:
https://developer.android.com/reference/kotlin/android/app/job/JobScheduler

Medium:
https://medium.com/google-developers/scheduling-jobs-like-a-pro-with-jobscheduler-286ef8510129

==============

JobIntentService - выполняет очередь из job/service.
Наследует Service. Предлагается к использованию вместо IntentService. 

Различия между IntentService и JobIntentService?
 - Удерживает WakeLock с помощью регулирования PowerManager.
 - Регулирует количество заданий, в зависимости от загруженности памяти.

Различия между Job и Service?

Почему IntentService deprecated, а JobIntentService нет?
Добавлена фича - регулирование количества заданий, которое может запустить приложение, 
в завсисимости от загруженности девайса.

Документация JobIntentService:
https://developer.android.com/reference/androidx/core/app/JobIntentService

==============

WorkManager

==============

Планирование задач в Android с использованием JobScheduler и IntentService:
https://habr.com/ru/post/339012/'

Планирование задач в Андроид:
https://habr.com/ru/post/336120/
