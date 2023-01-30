### How to use

Inside the raspmin create a directory with any name and change its ownership based on the container user name (builder)
```
$ mkdir path/to/dir && chown -R builder:builder path/to/dir
```
Build the image
```
$ docker build -t raspmin .
```
After building, enter the container using these arguments:
```
$ docker run --rm -it -v absolute/path/to/dir:/home/build/share raspymin bash
```
Find the poky directory and build the image, the tmp,deploy outputs will be stored on the directory you specified, this way it wont be necessary build every recipe every host boot.

