import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


public class TestTree 
{
	
	Scanner comp;
	PrintStream me;
	boolean show = true; //if set to true, will print out respones to and from the game.  Set to false to make it faster.
	
    
    //initializes the Testing environment with piped streams (Queues)
	public TestTree() throws IOException
	{
		PipedOutputStream inputHandle = new PipedOutputStream();
		PipedInputStream input= new PipedInputStream(inputHandle);
		
		PipedOutputStream output = new PipedOutputStream();
		PipedInputStream outputHandle= new PipedInputStream(output);
		
		QTree game = new QTree(input,new PrintStream(output));

		Thread t = new Thread(()->{game.playGame();});
		t.start();

        comp = new Scanner(outputHandle);
		me = new PrintStream(inputHandle);

	
	}

    /*
        Helper methods for IO and testing
    
        These methods are beefed up versions of assert.  
    
    */
    
    //Use this to "check" if the string from the program is correct.
	public void check(String s)
	{
		String observed = comp.nextLine();
		if(show) {System.out.println("observed:"+observed);}
		//will not work with simple assert statements due to multithreading	
		if( ! observed.equals(s))
		{
			System.out.println("expected "+s+" but got "+observed);
			System.exit(1);
		}
	}
	
	public void say(String s)
	{
		me.println(s);
		me.flush(); //greatly increases speed of program, lets other side know there is new data.
		if(show) {System.out.println("said:"+s);}
	}
	
	
	public void run()
	{
		//now what? Think of all the input and outputs here...
		check(Strings.IS_IT_ALIVE);
        say("Y");
        
        check("Is it a Duck?");
        say("Y");
        
        check(Strings.I_WIN);
        
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);
        say("N");
        
        check("Is it a Rock?");
        say("N");
        
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Chair");
        
        check("Please provide me a question that will differentiate between a Rock and a Chair");
        say("Does it have four legs?");
        
        check("Answering yes to Does it have four legs? means Chair?");
        say("Y");
        
        check(Strings.THANKS);
        
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);
        say("Y");
        
        check("Is it a Duck?");
        say("N");
        
        check(Strings.WHAT_IS_THE_ANSWER);
        say("LadyBug");
        
        check("Please provide me a question that will differentiate between a Duck and a LadyBug");
        say("Does it have Black spots?");
        
        check("Answering yes to Does it have Black spots? means LadyBug?");
        say("Y");
        
        check(Strings.THANKS);
        
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);
        say("Y");
        
        check("Does it have Black spots?");
        say("N");
        
        check(Strings.IS_IT_A + Strings.DUCK);
        say("Y");
        
        check(Strings.I_WIN);
        
        check(Strings.PLAY_AGAIN);
        say("Y");
        
        check(Strings.IS_IT_ALIVE);
        say("Y");
        
        check("Does it have Black spots?");
        say("N");
        
        check("Is it a Duck?");
        say("N");
        
        check(Strings.WHAT_IS_THE_ANSWER);
        say("Dog");
        
        check("Please provide me a question that will differentiate between a Duck and a Dog");
        say("Is it a Mans Bestfriend?");
        
        check("Answering yes to is it a Mans Bestfriend? means Dog?");
        say("Y");
        
        check(Strings.PLAY_AGAIN);
        say("N");
        
        
      
		

        
        
        //close the streams at the end to enrue good behavior.
		comp.close();
		me.close();
	}





	public static void main(String[] args) 
	{
		System.out.print("Test is running");
		try
		{
			TestTree test = new TestTree();
			test.run();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	
		System.out.print("you there halt");
		
	}
	
	
}
