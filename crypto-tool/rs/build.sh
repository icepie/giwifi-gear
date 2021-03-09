#!/usr/bin/env bash

package="giwifi-mix-crypto"
version=$1
if [[ -z "$version" ]]; then
  echo "usage: $0 <version-name>"
  exit 1
fi
package_split=(${package//\// })
package_name=${package_split[-1]}

platforms=(
    "arm-linux-androideabi"
    "aarch64-linux-android"
    "i686-linux-android"
    "x86_64-linux-android"
    #"i686-pc-windows-gnu"
    "x86_64-pc-windows-gnu"
    "aarch64-unknown-linux-musl"
    "aarch64-unknown-linux-musl"
    "i586-unknown-linux-musl"
    "i686-unknown-linux-musl"
    "x86_64-unknown-linux-musl"
    "mips-unknown-linux-musl"
    "mipsel-unknown-linux-musl"
    #"i686-unknown-freebsd"
    #"x86_64-unknown-freebsd"
)

for platform in "${platforms[@]}"
do
    platform_split=(${platform//\-/ })

    RSOS=${platform_split[-2]}
    if [ ! ${platform_split[3]} ] && [ ${platform_split[1]} != "pc" ]; then
        RSOS=${platform_split[-1]}
    fi

    RSARCH=${platform_split[0]}

    output_name=target/$platform/release/$package
    release_name=$package_name'-'$version'-'$RSOS'-'$RSARCH'-rs'

    echo $release_name

    cross build --release --target $platform
    if [ $? -ne 0 ]; then
        echo 'An error has occurred! Aborting the script execution...'
        exit 1
    fi

    if [ $RSOS = "windows" ]; then
        mv $output_name.exe ./
        zip $release_name.zip $package.exe ../README.md
        rm $package.exe
    else
        mv $output_name ./
        tar zcvf $release_name.tar.gz $package ../README.md
        rm $package
    fi

done

cargo clean
