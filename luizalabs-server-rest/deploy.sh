docker push ensleyribeiro/luizalabs-api:$PROJECT_VERSION

kubectl apply -f k8s

kubectl set image deployment luizalabs-api luizalabs-api=ensleyribeiro/luizalabs-api:$PROJECT_VERSION