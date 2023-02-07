### How to use
Inside the raspmin create a directory with any name and change its ownership based on the container user uid (builder)
```
$ mkdir path/to/dir && chown -R 5000:5000 path/to/dir
```
Build the image inside image dir
```
$ cd image
$ docker build -t raspmin .
```
After building, enter the container using these arguments
```
$ docker run --rm -it -v absolute/path/to/share/dir:/home/build/share raspymin bash
```
Inside the container go to poky dir
```
$ . oe-init-build-env
$ bitbake raspmin-core
```
Outside the container, go to the image location and descompress it using bzip2
```
$ cd share/tmp/deploy/images/raspberry4-64/
$ bzip2 -d -f openrc-raspberrypi4-64.wic.bz2 
```
And burn the .wic file, currently not ip is given to the board, so use the serial approach, UART is enabled by default. 
###### The compiled binaries and builded images will be stored at the directory you specified, this way it wont be necessary build to recipes every host boot.
