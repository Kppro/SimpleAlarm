# Test AlarmManager

Opened Android Studio issue : https://issuetracker.google.com/issues/67489700


Steps to reproduce the bug :

1) open the project with AS and launch the app on a device
2) On the app hit the "Set alarm" button, you can update the status to be sure it is setted.

You can double check on the command line with : 
`adb shell dumpsys alarm > dump.txt`
`vim dump.txt` and search for the occurence of "AlarmReceiver"

3) Now, if the alarm is not triggered yet, and you hit the stop button of AS,
if you check again the content of the system alarms list using adb, you'll see that "AlarmReceiver" was removed and so will not be triggered.

So each time you are testing the alarms of your app using AS to launch and stop it, this strange behavior will make you believe that your app had a bug and that is the reason why the alarms did not go off.

Of course, people not testing their code will not be affected by this bug :)
