:loop
echo Waiting for MySQL to start...
timeout /t 5 > NUL
mysqladmin ping -hmysql -uroot -prootPWD > NUL 2>&1
if %errorlevel% == 0 goto exit_loop
goto loop

:exit_loop
echo MySQL is up and running.
