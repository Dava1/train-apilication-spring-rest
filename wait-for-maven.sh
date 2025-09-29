while '[ !-f ..\application\target\myapp.jar]';
do echo "Waiting for maven build to complete..."
  sleep 5
done
java -jar \application\target\myapp.jar