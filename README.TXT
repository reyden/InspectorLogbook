InspectorLogbook is a java application with Embedded SQlite Database.

========================
BUILD OUTPUT DESCRIPTION
========================

When you build an Java application project that has a main class, the IDE
automatically copies all of the JAR
files on the projects classpath to your projects dist/lib folder. The IDE
also adds each of the JAR files to the Class-Path element in the application
JAR files manifest file (MANIFEST.MF).

To distribute this project, zip up the dist folder (including the lib folder)
and distribute the ZIP file.

You can click the link below to download the zip file(PLB.rar).

https://www.dropbox.com/scl/fi/p5hnkmq3yulas39avq8fc/PLB.rar?rlkey=aes4gtkoed9g9nl0vaqvk0p9b&dl=0


To run the project from the command line, unzip the downloaded zip file,
copy and paste it to drive C, go to the dist folder and
type the following:

java -jar "InspectorLogbook.jar" 

If for compatibility issue, you can download and double click to install
the jre installer that I also provided in the link below

https://www.dropbox.com/scl/fi/vfzop31qrkfij414ythbx/jre-8u92-windows-i586.exe?rlkey=7w6qstw4simk628lgtv5i7knd&dl=0

*on the dropbox repo in the above link just click on the DOWNLOAD button since the repository do not provide preview for .exe files.













Notes:

* If two JAR files on the project classpath have the same name, only the first
JAR file is copied to the lib folder.
* Only JAR files are copied to the lib folder.
If the classpath contains other types of files or folders, these files (folders)
are not copied.
* If a library on the projects classpath also has a Class-Path element
specified in the manifest,the content of the Class-Path element has to be on
the projects runtime path.
* To set a main class in a standard Java project, right-click the project node
in the Projects window and choose Properties. Then click Run and enter the
class name in the Main Class field. Alternatively, you can manually type the
class name in the manifest Main-Class element.
