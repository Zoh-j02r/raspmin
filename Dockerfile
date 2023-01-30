FROM ubuntu:focal
ENV DEBIAN_FRONTEND noninteractive
RUN apt-get update && apt-get -y install sudo curl git build-essential vim \
	python3-pip python3-pexpect python3-subunit python3-git wget unzip \
	xz-utils chrpath cpio diffstat gawk socat liblz4-tool zstd 
RUN apt-get install locales
RUN locale-gen en_US.UTF-8
RUN useradd -u 5000 --create-home --home-dir /home/build builder && \
	echo "builder ALL=(ALL) NOPASSWD: ALL" | tee -a /etc/sudoers
USER builder
RUN cd /home/build && \
	git clone https://git.yoctoproject.org/poky -b kirkstone && cd poky && \
	git clone https://git.openembedded.org/meta-openembedded -b kirkstone && \
	git clone https://github.com/agherzan/meta-raspberrypi -b kirkstone && \
	git clone https://github.com/jsbronder/meta-openrc -b kirkstone && \
	./scripts/install-buildtools && \
	. /home/build/poky/buildtools/environment-setup-x86_64-pokysdk-linux && \
	. /home/build/poky/oe-init-build-env
ADD conf /home/build/poky/build/conf
RUN mkdir /home/build/share
