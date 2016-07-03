# Cards Against Humanity Simulator
You are playing Cards Against Humanity with your friends. Some people already have 3 cards, but it starting to get late. In the
back of your mind you are wondering how long it will take until a winner is declared. Should you try to finish the game, or
should you call it a night?

## Enter the Cards Against Humanity Simulator.

This program simulates playing the game Cards Against Humanity & generates a relative frequency histogram of the number of rounds
played before a winner is declared. My underlying assumptions are that each round a serf (a player who is not the card Czar) is
chosen randomly from a uniform distribution and elected as the new card Czar.

The histogram is generated by replaying the game 100,00. The default values are 8 players with 5 points needed to win.

The probability density function is very similar to the [`uniform sum distribution`](https://en.wikipedia.org/wiki/Irwin-Hall_distribution) with the exception that each round a random
player is removed from the selection process.

## Example of program output
![alt text](https://github.com/carlcorder/cards.against.humanity/blob/master/src/img/cards-against-humanity-histogram.png)

#### Average number of rounds played as a function of the number of players and points needed to win

| Points  	| 1    	| 2    	| 3     	| 4     	| 5     	|
|---------	|------	|------	|-------	|-------	|-------	|
| Players 	|      	|      	|       	|       	|       	|
| 1       	| /    	| /    	| /     	| /     	| /     	|
| 2       	| 1.00 	| 3.00 	| 5.00  	| 7.00  	| 9.00  	|
| 3       	| 1.00 	| 3.50 	| 6.00  	| 8.66  	| 11.33 	|
| 4       	| 1.00 	| 3.88 	| 6.96  	| 10.18 	| 13.48 	|
| 5       	| 1.00 	| 4.22 	| 7.77  	| 11.54 	| 15.47 	|
| 6       	| 1.00 	| 4.51 	| 8.51  	| 12.82 	| 17.33 	|
| 7       	| 1.00 	| 4.77 	| 9.20  	| 14.01 	| 19.10 	|
| 8       	| 1.00 	| 5.02 	| 9.84  	| 15.17 	| 20.80 	|
| 9       	| 1.00 	| 5.25 	| 10.46 	| 16.25 	| 22.44 	|
| 10      	| 1.00 	| 5.46 	| 11.04 	| 17.31 	| 24.03 	|


If we Let x be the number of players and y be the number of points to win. Then, using a quadratic fit to the table above yields the
equation:

> F(x,y) = 0.435498 - 0.234449 x - 0.0282792 x^2 + 0.154057 y + 0.469967 x y + 0.173413 y^2

For example, for 13 players and 5 rounds to win we have

> F(13,5) = 28.26

Which is close to the simulated value of 28.54

### Requirements
* Java 8
* Maven

### Running
* Run the main class in your favorite IDE

#### TODO
- [ ] Package as jar and allow user to run with command line parameters
