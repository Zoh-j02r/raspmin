SUMMARY = "A custom image for running openrc on raspberry on target device"

IMAGE_FEATURES += "splash ssh-server-dropbear"

IMAGE_INSTALL += " \
    openrc \
    packagegroup-core-boot \
"

IMAGE_BASENAME := "openrc"

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
