### How to use
Inside the raspmin directory create a share directory and change its ownership based on the container user uid (builder)
```
$ mkdir share && chown -R 5000:5000 share
```
###### This directory will be used for sharing files between the host and container, the compiled binaries and builded images will be stored at the directory you specified, this way it wont be necessary build to recipes every host boot.
Build the container inside image directory
```
$ cd image
$ docker build -t raspmin .
```
After building the image, enter the container using the `init` script and source poky directory
```
$ . oe-init-build-env
$ bitbake core-raspmin
```
The project image will be found at `share/tmp/deploy/images/raspberry4-64/*.rpi-sdimg` 
```
For wifi capabilities the wpa_supplicant must be initilized as service using openrc, pass the initialization args to `/etc/conf.d/wpa_supplicant` file and set it as a service using
```
rc-update add wpa_supplicant default
rc-service wpa_supplicant start
```
dont forget to add your network at `/etc/wpa_supplicant.conf`.
