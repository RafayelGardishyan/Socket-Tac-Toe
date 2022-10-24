# Socket-Tac-Toe
No installation needed.
To run:
1. Clone the source to your local machine
2. Compile the source
3. Run `Server.java` on the server
4. Run `Client.java` on the client machines

Please note that the `HOST` and `CONFIG` parameters are in `Server.class`
If you want to play over the internet you should edit those and enable port forwarding on your machine.

### Players Guide
When you start the client application and there is a server running you will get a menu.
The options are:
* `n`: Start a new game
* `j`: Join a game another player has already created
* `e`: Exit the client

After you've chosen the option you will be connected to a game.
The game is simple, as is normal Tic-Tac-Toe:
You have a board looking something like this:
```
  | A | B | C |
--+---+---+---+-
1 |   |   |   |  
--+---+---+---+-
2 |   |   |   | 
--+---+---+---+-
3 |   |   |   | 
```
Every time it's your turn you enter the coordinates of your move in the format `A1` where the letter is the column and the number is the row.
When the game is over you have to start the client again.