Service - This runs on the same main thread which invokes this service and performs some background operation. 
For any long running operation happening on the main thread it is recommended 
to create a new thread and do the job (eg; Handler) by not impacting the main thread's performance.

Drawback : Runs on main thread

IntentService - Intent service also helps in doing some long running (indefinite) background task. 
The only difference is that it creates a new thread to perform this task and does not run on the main thread. 
Does the given job on it's onHandleIntent.

Drawback: The job given to the IntentService would get lost when the application is killed

JobIntentService - Job intent service is very similar to IntentService but with few benefits like the application 
can kill this job at any time and it can start the job from the beginning once the application gets recreated/up.
But from Oreo, if the app is running in background it's not allowed to start the service in background. 
Android asks us to start the service explicitly by context.startForegroundService instead of context.startService 
and when the service is started within 5 seconds it must be tied to the notification to have a UI element associated with it.
