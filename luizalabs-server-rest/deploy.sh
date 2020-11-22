docker build -t ensleyribeiro/luizalabs:$SHA -f ./Dockerfile .
docker push ensleyribeiro/luizalabs:latest
docker push ensleyribeiro/luizalabs:$SHA
kubectl apply -f k8s
kubectl set image deploymets/server-deploymet server=ensleyribeiro/luizalabs