git add src/*/tastytungsten/*.java  src/*.xml src/header.txt build.xml git.sh src/logging-junit.properties
git commit
git push

ALPHA=`pwd`
BETA=`mktemp -d`
cd ${BETA}
git clone git@github.com:KHoW8Day/tastytungsten.git

diff -qrs ${ALPHA} ${BETA}/tastytungsten | grep differ
diff -qrs ${ALPHA} ${BETA}/tastytungsten | grep only
