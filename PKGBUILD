# Maintainer: Tea <icepie.dev@gmail.com>
pkgname=giwifi-gear.sh
pkgver=1.3.1
pkgrel=1
pkgdesc="A UNIX shell command line interpreter tool for giwifi"
arch=('any')
url=""
license=('GPL')
groups=()
depends=(
  'curl'
  'bash'
  'openssl'
)
makedepends=()
optdepends=()
provides=()
conflicts=()
replaces=()
backup=()
options=()
install=
changelog=
source=($pkgname)
noextract=()
sha256sums=('13bfbd6d93f74fab3794bba53669e4354eb9b7804679f777537288924d0f6c41')

build() {
    ls -al
}

package() {
    mkdir -p $pkgdir/usr/bin
    cp $pkgname $pkgdir/usr/bin
    chmod +x $pkgdir/usr/bin/$pkgname
}
