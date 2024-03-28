all:
	find * -name "*.java" > sources.txt
	javac -d bin @sources.txt

fclean: clean

clean:
	rm sources.txt
	rm -rf bin

run:
	java -cp bin simulation.Simulation scenario2.txt