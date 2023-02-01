### How to use
Inside the raspmin create a directory with any name and change its ownership based on the container user uid (builder)
```
$ mkdir path/to/dir && chown -R 5000:5000 path/to/dir
```
Build the image
```
$ docker build -t raspmin .
```
After building, enter the container using these arguments:
```
$ docker run --rm -it -v absolute/path/to/dir:/home/build/share raspymin bash
```
Go to the tmp/deploy directory and search and descompress openrc-raspberry*.wic.bz2 using
```
$ bzip2 -d -f openrc-raspberrypi4-64.wic.bz2 
```
And burn the .wic file, currently not ip is given to the board so use the serial approach via [tio] or [screen]
###### The tmp,deploy outputs will be stored on the directory you specified, this way it wont be necessary build every recipe every host boot.
