#!/bin/sh

# wait-for-it.sh
# Wait until a TCP host:port is available

HOST="$1"
PORT=$(echo $1 | cut -d':' -f2)
TIMEOUT="$3"

echo "⏳ Waiting for $HOST to be available..."

for i in $(seq 1 60); do
  nc -z $(echo $HOST | cut -d':' -f1) $PORT > /dev/null 2>&1
  if [ $? -eq 0 ]; then
    echo "✅ $HOST is available!"
    break
  fi
  sleep 1
done

# If you passed a command, execute it
shift 3
exec "$@"
