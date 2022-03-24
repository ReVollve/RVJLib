## RVJLib
This project contains some of my java files, which I use sometimes. 

## Projects
* [RVArgs.java](#RVArgs.java)
* [Translation.java](#Translation.java)

### [RVArgs.java](https://github.com/ReVollve/RVJLib/blob/master/src/main/java/de/revollve/lib/config/RVArgs.java)

Brings greater usage to the args from main function

### [Translation.java](https://github.com/ReVollve/RVJLib/blob/master/src/main/java/de/revollve/lib/config/Translation.java)

Translation config for strings\
An object of the translation class is a single string to be translated. To retrieve the translation, use the #get() method.
The constructor takes the identifier or optionally a path to an external config file.
When speaking of config files, all actual translations have to be stored in a config file. Default one is located in resources/lang.ini. 
Different languages are seperated by sectionnames. Example: 
```
[english]
message1=Hello, this is in english
message2=This is also in english
[german]
message1=Das ist deutsch
message2=Hagebuddne
...
```
If you want to change the language, use the #setLanguage(str lang) method.\
If you want to update the config file, use the #update() method.\

Static settings:
* bool log_err = false
  * Enables error logging
* string language = "english"
  * Alternative way to change the language
* string msg_missing_file = "[missing lang file]"
  * Translation message when config file is missing
* string msg_missing_translation = "[missing translation]"
  * Translation message when translation is missing

Depends on [Ini4j](https://mvnrepository.com/artifact/org.ini4j/ini4j)
