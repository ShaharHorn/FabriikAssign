
Hey,


Some notes :

customer-score-generator  - will produce customers id and score to kafka server.

customer-web-service - will consume messages from kafka, save it on a hashmap and calculate the average score.

- I used kafka.retention time = 24 hours for deleting messages after 24 hours,
  i couldnt consume the delete messages, so i used a thread that will execute evey second to check if there are expired customers in the hashmap.


 -I tried to run the project in a self-contained environment, but i had some errors that i didnt has the time to solve,
  you can look at the directory - 'docker-compose' - there is a docker compose file with the dockerfiles of the projects.

  - Run the project :
    docker-compose up -d        // will run zookeeper and a kafka server on a docker.
    run customer-web-service locally
    run customer-score-generator.


    Thanks a lot,
    feel free to contact me.
    Shahar.