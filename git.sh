git add src/*/tastytungsten/*.java  src/checks.xml src/header.txt build.xml git.sh
git commit
git push

ALPHA=`pwd`
BETA=`mktemp -d`
cd ${BETA}
git clone git@github.com:KHoW8Day/tastytungsten.git

diff -qrs ${ALPHA} ${BETA}/tastytungsten
