#!/usr/bin/env bash

package="giwifi-mix-crypto"
version=$1
if [[ -z "$version" ]]; then
  echo "usage: $0 <version-name>"
  exit 1
fi
package_split=(${package//\// })
package_name=${package_split[-1]}
# freebsd/386
# freebsd/amd64
# freebsd/arm

platforms=(
  "windows/amd64"
  "windows/386"
  "darwin/amd64"
  "android/386"
  "android/amd64"
  "android/arm"
  "android/arm64"
  "darwin/amd64"
  "darwin/arm64"
  "reebsd/arm64"
  "ios/amd64"
  "ios/arm64"
  "linux/386"
  "linux/amd64"
  "linux/arm"
  "linux/arm64"
  "linux/mips"
  "linux/mips64"
  "linux/mips64le"
  "linux/mipsle"
  "linux/ppc64"
  "linux/ppc64le"
  "linux/riscv64"
  "linux/s390x"
)

for platform in "${platforms[@]}"
do
    platform_split=(${platform//\// })
    GOOS=${platform_split[0]}
    GOARCH=${platform_split[1]}
    output_dir=$GOOS'-'$GOARCH
    output_name=$package_name

    release_name=$package_name'-'$version'-'$GOOS'-'$GOARCH

    if [ $GOOS = "windows" ]; then
        output_name+='.exe'
    fi

    if [ ! -d $output_dir ]; then
      mkdir $output_dir
      cp README.md $output_dir
    fi

    env GOOS=$GOOS GOARCH=$GOARCH go build -ldflags "-w -s"  -gcflags '-N -l' -o $package_name

    if [ $GOOS = "windows" ]; then
      zip $release_name'-go'.zip $package_name README.md
    else
      tar zcvf $release_name'-go'.tar.gz $package_name README.md
    fi

    rm -rf $output_dir
    rm -rf $package_name

    if [ $? -ne 0 ]; then
        echo 'An error has occurred! Aborting the script execution...'
        exit 1
    fi
done
