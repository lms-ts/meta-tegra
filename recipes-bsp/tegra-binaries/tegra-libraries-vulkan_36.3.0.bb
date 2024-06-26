L4T_DEB_COPYRIGHT_MD5 = "d3617777039321a257aef01439341b02"
DEPENDS = "tegra-libraries-core tegra-libraries-eglcore virtual/egl egl-wayland"

L4T_DEB_TRANSLATED_BPN = "nvidia-l4t-3d-core"

require tegra-debian-libraries-common.inc

MAINSUM = "a202ea5f467836eed8466faa7541dec3521c4d1668ca097cea3eee0dfc52cab6"

inherit features_check

REQUIRED_DISTRO_FEATURES = "vulkan opengl"

do_install() {
    install -d ${D}/usr/lib/aarch64-linux-gnu/nvidia
    if ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'true', 'false', d)}; then
        install -m644 ${S}/usr/lib/aarch64-linux-gnu/nvidia/nvidia_icd.json ${D}/usr/lib/aarch64-linux-gnu/nvidia/
        install -d ${D}${sysconfdir}/vulkan/icd.d
        ln -sf /usr/lib/aarch64-linux-gnu/nvidia/nvidia_icd.json ${D}${sysconfdir}/vulkan/icd.d/
    fi
}

FILES_SOLIBSDEV = ""
SOLIBS = ".so*"
FILES:${PN} += "/usr/lib/aarch64-linux-gnu"
