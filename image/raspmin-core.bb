SUMMARY = "core-raspmin-image"
DESCRIPTION = "A custom image for running openrc on raspberry on target device"
IMAGE_FEATURES += "ssh-server-dropbear"

IMAGE_INSTALL += " \
    openrc \
    packagegroup-core-boot \
"

export IMAGE_BASENAME = "raspmin-image"

LICENSE = "MIT"

inherit core-image openrc-image

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
