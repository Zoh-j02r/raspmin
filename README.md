### How to use
Inside the raspmin dir create a directory with any name and change its ownership based on the container user uid (builder)
```
$ mkdir path/to/share/dir && chown -R 5000:5000 path/to/share/dir
```
###### This directory will be used for sharing files between the host and container, the compiled binaries and builded images will be stored at the directory you specified, this way it wont be necessary build to recipes every host boot.
Build the container inside image directory
```
$ cd image
$ docker build -t raspmin .
```
After building, enter the container using the `init` script and source poky directory
```
$ . oe-init-build-env
$ bitbake core-raspmin
```
Outside the container, go to the image location and descompress it using bzip2
```
$ cd share/tmp/deploy/images/raspberry4-64/
$ bzip2 -d -f raspmin-core-raspberrypi4-64.wic.bz2 
```
Burn the .wic file, inside the board, for wifi capabilities the wpa_supplicant must be initilized as service using openrc, pass the initialization args to `/etc/conf.d/wpa_supplicant` file and set it as a service using
```
rc-update add wpa_supplicant default
rc-service wpa_supplicant start
```
currently a manual resize in root partition is needed (it only uses 400M), but a way for extending root partition using wic is on the way.
