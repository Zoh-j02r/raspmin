SUMMARY = "core-raspmin-image"
DESCRIPTION = "A custom image for running openrc on raspberry"
IMAGE_FEATURES += "ssh-server-dropbear debug-tweaks"

RASPMIN_CONNECTIVITY="dhcpcd wpa-supplicant linux-firmware-rpidistro-bcm43455"
RASPMIN_MULTIMEDIA="gstreamer1.0 gstreamer1.0-plugins-base gstreamer1.0-plugins-good"
CORE_IMAGE_EXTRA_INSTALL = "${RASPMIN_CONNECTIVITY} ${RASPMIN_MULTIMEDIA}" 

IMAGE_INSTALL += " \
    openrc \
    packagegroup-core-boot \
    ${CORE_IMAGE_EXTRA_INSTALL} \
"

inherit core-image openrc-image

export IMAGE_BASENAME = "raspmin-image"

LICENSE = "MIT"

OPENRC_STACKED_RUNLEVELS += "logging:default"
OPENRC_SERVICES += " \
    sysinit:udev-trigger \
    default:udev-settle \
    default:dhcpcd \
    logging:busybox-klogd \
    logging:busybox-syslogd \
"

boot_to_logging() {
    sed -i '/^l[345]/s,default,logging,' ${IMAGE_ROOTFS}${sysconfdir}/inittab
}
ROOTFS_POSTPROCESS_COMMAND += "${@bb.utils.contains('DISTRO_FEATURES', 'openrc', 'boot_to_logging; ', '', d)}"
