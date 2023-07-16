helper() {
  echo ""
  echo "Usage: $0 -e env"
  # shellcheck disable=SC2028
  echo "\t-e Specified environment for build process"
  exit 1 # Exit script after printing help
}

while getopts "e:" opt; do
  case $opt in
  e)
    echo "env: $OPTARG"
    env=$OPTARG
    ;;
  ?)
    helper
    ;;
  esac
done

if [ -z "$env" ]; then
  helper
fi

mvn clean install -DskipTests -P "$env"

docker build -t kelvn/standard-springboot-api:"$env"-latest -f Dockerfile .

docker push kelvn/standard-springboot-api:"$env"-latest
