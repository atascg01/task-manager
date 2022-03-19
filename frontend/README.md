## Running app with docker compose
Execute following command:
`$ docker-compose up -d --build`

## Building docker image

Run following command to build the docker image:
`$ docker build -t frontend:dev .`

## Running docker image
Once the build is done, run it with:
`$ docker run \
    -it \
    --rm \
    -v ${PWD}:/app \
    -v /app/node_modules \
    -p 3001:3000 \
    -e CHOKIDAR_USEPOLLING=true \
    frontend:dev`