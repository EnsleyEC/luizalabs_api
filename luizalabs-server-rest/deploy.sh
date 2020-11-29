docker push ensleyribeiro/luizalabs-api:latest

kubectl apply -f k8s

kubectl set image deployment luizalabs-api luizalabs-api=ensleyribeiro/luizalabs-api:latest