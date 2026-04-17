@ECHO OFF
SET DIR=%~dp0
IF "%DIR%"=="" SET DIR=.
SET APP_BASE_NAME=%~n0
SET APP_HOME=%DIR%
SET CLASSPATH=%APP_HOME%\gradle\wrapper\gradle-wrapper.jar
"%JAVA_HOME%\bin\java.exe" -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*
