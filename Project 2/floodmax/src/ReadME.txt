/**
* @author Subhasis Dutta, Ram Hariesh,Vibin Daniel
* @email-id sxd150830@utdallas.edu,rxc142330@utdallas.edu,vxd141730@utdallas.edu,
* @version 1.0
* 
* Program to simulate Floodmax algorithm for leader election in Asynchronous general networks
*/

Untar the floodmax folder
tar -xvf floodmax.tar
cd src
Inside src

The code was tested on csgrads1.utdallas.edu

To Compile the Program
javac *.java

To Run
java Main

Sample input present in input.dat and biginput.dat 
Corresponding output are output.dat and bigoutput.dat

Files:
Main.java -> Execution starts from this. This spans the different nodes and controls the rounds.
Message.java -> POJO to define the object for Message transfer.
NodeData.java -> Java Object to define the object for each Nodes.
ProcessNode.java -> Class for each node. This has the implementation of the HS Algorithm.
SharedData.java -> Utility Class to help pass information from one process to another.
